package com.ssafy.logit.model.growth.service;

import com.ssafy.logit.model.common.EventDate;
import com.ssafy.logit.model.growth.dto.*;
import com.ssafy.logit.model.growth.entity.Growth;
import com.ssafy.logit.model.growth.entity.GrowthUser;
import com.ssafy.logit.model.growth.entity.Progress;
import com.ssafy.logit.model.growth.repository.GrowthRepository;
import com.ssafy.logit.model.growth.repository.GrowthUserRepository;
import com.ssafy.logit.model.growth.repository.LikeRepository;
import com.ssafy.logit.model.growth.repository.ProgressRepository;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GrowthService {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String NONE = "사용자 없음";
    private static final String NONE_EVENT = "성장 이벤트 없음";

    @Autowired
    private GrowthRepository growthRepo;

    @Autowired
    private GrowthUserRepository growthUserRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProgressRepository progressRepo;

    @Autowired
    private LikeRepository likeRepo;

    // 이벤트 등록
    @Transactional
    public long registEvent(String email, GrowthDto growthDto) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            growthDto.setUser(user.get());
            Growth growth = growthRepo.save(growthDto.toEntity());
            return growth.getGrowthId();
        } else {
            return -1;
        }
    }

    // 한 개의 성장 이벤트 반환
    public GrowthDto getOneEvent(long growthId) {
        Optional<Growth> growth = growthRepo.findById(growthId);
        if(growth.isPresent()) {
            return growth.get().toDto();
        }
        return null;
    }

    // 해당 성장 이벤트에 참여하는 사용자 목록 반환
    public List<UserDto> getAllThisUser(long growthId) {
        return userRepo.findAllUser(growthId).stream().map(UserDto::new).collect(Collectors.toList());
    }

    // 해당 이벤트에 참여하지 않는 사용자 반환
    public List<UserDto> getAllUser(long growthId, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            long userId = user.get().getId();
            List<User> userList = userRepo.findInviteUser(growthId, userId);
            return userList.stream().map(UserDto::new).collect(Collectors.toList());
        }
        return null;
    }

    // 해당 이벤트에 참여하지 않는 사용자 중 이름으로 like 검색한 결과 반환
    public List<UserDto> searchUser(long growthId, String email, String userName) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().getId();
            List<User> userList = userRepo.searchInviteUser(growthId, userId, userName);
            return userList.stream().map(UserDto::new).collect(Collectors.toList());
        }
        return null;
    }

    // 성장 이벤트 공유
    // (해당 이벤트에 참여중이지 않은 사용자만 받아올 수 있으므로, 그 부분은 따로 검사하지 않음)
    @Transactional
    public String inviteUser(long growthId, long userId) {
        Optional<Growth> growth = growthRepo.findById(growthId);
        if(growth.isPresent()) {
            GrowthUser growthUser = new GrowthUser();
            growthUser.setGrowth(growth.get());
            Optional<User> user = userRepo.findById(userId);
            if(user.isPresent()) {
                growthUser.setUser(user.get());
                growthUser.setType(false);
                growthUserRepo.save(growthUser);
                return SUCCESS;
            } else {
                return NONE;
            }
        } else {
            return NONE_EVENT;
        }
    }

    // 내 모든 이벤트 반환 (참여 + 작성)
    public List<GrowthDto> getMyAllEvent(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().getId();

            // 내가 작성한 이벤트 조회
            List<Growth> growthList = growthRepo.findByUserId(userId);
            List<GrowthDto> growthDtoList = growthList.stream().map(GrowthDto::new).collect(Collectors.toList());

            // 내가 참여하는 이벤트 조회
            List<GrowthUser> growthUserList= growthUserRepo.findMyEvent(userId, true);
            List<GrowthUserDto> growthUserDtoList = growthUserList.stream().map(GrowthUserDto::new).collect(Collectors.toList());
            for(int i = 0; i < growthUserDtoList.size(); i++) {
                Optional<Growth> tmp = growthRepo.findById(growthUserDtoList.get(i).getGrowth().getGrowthId());
                if(tmp.isPresent()) {
                    growthDtoList.add(tmp.get().toDto());
                }
            }
            return growthDtoList;
        }
        return null;
    }

    // 내가 받은 초대에 대한 이벤트 객체들을 반환
    public List<GrowthDto> getInvitation(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            // 아직 수락하지 않은 초대 모두 조회
            long userId = user.get().toDto().getId();
            List<GrowthUser> growthUserList = growthUserRepo.findMyEvent(userId, false);
            List<GrowthUserDto> growthUserDtoList = growthUserList.stream().map(GrowthUserDto::new).collect(Collectors.toList());

            // 아직 수락하지 않은 초대의 이벤트 객체를 List로 만들어 반환
            List<GrowthDto> growthDtoList  = new ArrayList<>();
            for(int i = 0; i < growthUserList.size(); i++) {
                Optional<Growth> growth = growthRepo.findById(growthUserList.get(i).toDto().getGrowth().getGrowthId());
                if(growth.isPresent()) {
                    growthDtoList.add(growth.get().toDto());
                }
            }
            return growthDtoList;
        }
        return null;
    }

    // 초대 수락 (type을 true로 변경) or 거절 (db에서 삭제)
    @Transactional
    public String acceptInvitation(long growthId, boolean accept, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().toDto().getId();
            long growthUserId = growthUserRepo.findEvent(userId, growthId);
            Optional<GrowthUser> growthUser = growthUserRepo.findById(growthUserId);
            if(growthUser.isPresent()) {
                GrowthUserDto growthUserDto = growthUser.get().toDto();
                if(accept) {
                    growthUserDto.setType(true);
                    growthUserRepo.save(growthUserDto.toEntity());
                } else {
                    growthUserRepo.delete(growthUser.get());
                }
                return SUCCESS;
            } else {
                return FAIL;
            }
        }
        return FAIL;
    }

    // 성장 과정 등록
    @Transactional
    public String registProgress(ProgressDto progressDto, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            progressDto.setUser(user.get());
            Optional<Growth> growth = growthRepo.findById(progressDto.getGrowthId());
            if(growth.isPresent()) {
                progressDto.setGrowth(growth.get());
                progressRepo.save(progressDto.toEntity());
                return SUCCESS;
            } else {
                return NONE_EVENT;
            }
        }
        return NONE;
    }

    // 한 개의 성장 과정 반환
    @Transactional
    public ProgressDto getProgress(long progressId) {
        return progressRepo.findById(progressId).get().toDto();
    }

    // 하루에 대한 모든 progress를 우선순위 반영하여 반환
    public List<ProgressDto> getDateProgress(long growthId, String date, String email) {
        List<ProgressDto> progressDtoList = new ArrayList<>();

        long userId = userRepo.findByEmail(email).get().getId();
        Optional<Progress> myProgress = progressRepo.getMine(growthId, date, userId);
        if(myProgress.isPresent()) {
            progressDtoList.add(myProgress.get().toDto());
        }
        Optional<List<Progress>> otherProgress = progressRepo.getDateProgress(growthId, date, userId);
        progressDtoList.addAll(otherProgress.get().stream().map(ProgressDto::new).collect(Collectors.toList()));
        return progressDtoList;
    }

    // 해당 이벤트의 모든 progress에 대한 정보를 가공하여 반환
    public List<AllProgress> getAllProgress(long growthId) {
        Optional<List<String>> dateList = progressRepo.dateList(growthId); // 날짜별로 progress 구분
        if(dateList.isPresent()) {
            List<AllProgress> allProgressList = new ArrayList<>();
            for(int i = 0; i < dateList.get().size(); i++) { // 날짜별 탐색
                AllProgress allProgress = new AllProgress();
                String date = dateList.get().get(i);
                allProgress.setDate(date);
                Optional<List<Progress>> findByDate = progressRepo.findByDate(date);
                if(findByDate.isPresent()) {
                    List<Content> contentList = new ArrayList<>();
                    for(int j = 0; j < findByDate.get().size(); j++) { // 한 날짜에 속한 progress 탐색
                        Content content = new Content();
                        Progress progress = findByDate.get().get(j);
                        content.setProgressId(progress.getProgressId());
                        content.setWriterEmail(progress.getUser().getEmail());
                        content.setWriterName(progress.getUser().getName());
                        content.setWriterImage(progress.getUser().getImage());
                        content.setContent(progress.getContent());
                        contentList.add(content);
                    }
                    allProgress.setContentList(contentList);
                }
                allProgressList.add(allProgress);
            }
            return allProgressList;
        }
        return null;
    }

    // 한 날짜의 대표 progress 선정 후 반환
    // 우선순위 : 내가 쓴 progress -> 좋아요 높은 progress -> 빨리 쓴 progress
    public List<FirstProgress> getFirstProgress(long growthId, String email) {
        List<FirstProgress> firstProgressList = new ArrayList<>();
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().toDto().getId();

            Optional<List<String>> dateList = progressRepo.dateList(growthId);
            if(dateList.isPresent()) {

                for(int i = 0; i < dateList.get().size(); i++) {
                    String nowDate = dateList.get().get(i);

                    FirstProgress firstProgress = new FirstProgress();
                    firstProgress.setDate(nowDate);

                    Optional<Progress> myProgress = progressRepo.getMine(growthId, nowDate, userId);
                    if(myProgress.isPresent()) {
                        firstProgress.setProgressDto(myProgress.get().toDto());
                    } else {
                        Optional<Progress> otherProgress = progressRepo.getFirst(growthId, nowDate);
                        if(otherProgress.isPresent()) {
                            firstProgress.setProgressDto(otherProgress.get().toDto());
                        }
                    }
                    firstProgressList.add(firstProgress);
                }
            }
        }
        return firstProgressList;
    }

    // 좋아요
    @Transactional
    public boolean like(long processId, String email) {
        boolean result = true;
        Optional<User> user = userRepo.findByEmail(email);
        Optional<Progress> progress = progressRepo.findById(processId);
        if(user.isPresent() && progress.isPresent()) {
            long userId = user.get().toDto().getId();
            ProgressDto progressDto = progress.get().toDto();

            int myLike = likeRepo.cntMyLike(userId, processId);
            if(myLike == 0) { // 좋아요
                LikeProgressDto likeDto = new LikeProgressDto();
                likeDto.setProgress(progressRepo.findById(processId).get());
                likeDto.setUser(userRepo.findById(userId).get());
                likeRepo.save(likeDto.toEnity());
                progressDto.setLikeCnt(progressDto.getLikeCnt() + 1);
            } else { // 좋아요 취소
                likeRepo.delete(likeRepo.findMyLike(userId, processId).get());
                progressDto.setLikeCnt(progressDto.getLikeCnt() - 1);
                result = false;
            }
            progressRepo.save(progressDto.toEntity());
        }
        return result;
    }

    // 한 이벤트의 내가 좋아요한 성장 과정 리스트 조회
    public List<Long> getLikeProgress(long growthId, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            long userId = user.get().toDto().getId();
            Optional<List<Long>> likeProgressList = likeRepo.getLikeProgress(growthId, userId);
            if(likeProgressList.isPresent()) {
                return likeProgressList.get();
            }
        }
        return null;
    }

    // 로그 만들기
    public List<Log> makeLog(long growthId) throws ParseException {
        List<Log> logList = new ArrayList<>();

        Optional<Growth> growth = growthRepo.findById(growthId);
        if(growth.isPresent()) {
            LocalDate startDate = growth.get().getEventDate().getStartDate();
            LocalDate endDate = growth.get().getEventDate().getEndDate();

            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.toString());
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate.toString());

            long diffDays = (end.getTime() - start.getTime()) / 1000 / (24 * 60 * 60); // 총 일 수

            Log log = new Log();
            log.setIdx(0);
            log.setDate(startDate.toString());
            log.setWritten(false);
            logList.add(log);

            for(int i = 1; i < diffDays + 1; i++) {
                log = new Log();
                log.setIdx(i);
                startDate = startDate.plusDays(1);
                log.setDate(startDate.toString());
                log.setWritten(false);
                logList.add(log);
            }

            Optional<List<String>> dateList = progressRepo.dateList(growthId);
            if(dateList.isPresent()) {
                for(int i = 0; i < dateList.get().size(); i++) {
                    String nowDate = dateList.get().get(i);
                    for(int j = 0; j < logList.size(); j++) {
                        if(logList.get(j).getDate().equals(nowDate)) {
                            logList.get(j).setWritten(true);
                        }
                    }
                }
            }
        }
        Collections.sort(logList);
        return logList;
    }
}