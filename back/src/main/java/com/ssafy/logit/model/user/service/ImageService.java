package com.ssafy.logit.model.user.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    @Value("${s3.bucket}")
    private String bucket;

    @Value("${s3.region}")
    private String region;

    private final AmazonS3 amazonS3;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public String uploadImage(MultipartFile multipartFile, UserDto userDto) throws Exception {
        // 파일 이름 중복 방지 위해서 UUID로 생성한 랜덤 값을 파일 이름에 연결
        String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();

        // S3에 파일 업로드 시 파일 사이즈를 ContentLength로 알려주기 위해서 ObjectMetadata 사용
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        // 파일 Stream을 열어서 S3에 업로드
        amazonS3.putObject(bucket, fileName, multipartFile.getInputStream(), objMeta);

        // S3에 업로드된 파일 URL 가져와 저장, 리턴
        String fileUrl = amazonS3.getUrl(bucket, fileName).toString();
        String nowImageUrl;
        if(userDto.getImage() != null) { // 기존 프로필 이미지가 있을 경우
            // 원래 파일 삭제
            nowImageUrl = userDto.getImage();
            boolean dropResult = dropImage(nowImageUrl, userDto.getId());
            if(!dropResult) { // 삭제 실패
                return "fail";
            }
        }
        userDto.setImage(fileUrl);
        userRepo.save(userDto.toEntity());
        return fileUrl;
    }

    public Boolean dropImage(String fileUrl, Long id) throws Exception {
        // 키 구하기
        String fileKey = fileUrl.substring(58); // 폴더/파일.확장자
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(region).build();

        // fileKey 속성 null로 변환 및 s3에서 기존 파일(프로필 사진) 삭제
        UserDto userDto;
        if(userRepo.findById(id).isPresent()) {
            userDto = userRepo.findById(id).get().toDto();
            userDto.setImage(null);
            userRepo.save(userDto.toEntity());
            s3.deleteObject(bucket, fileKey);
            return true;
        } else {
            return false;
        }
    }
}
