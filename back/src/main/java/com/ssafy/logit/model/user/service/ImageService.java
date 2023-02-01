package com.ssafy.logit.model.user.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    @Value("${s3.bucket}")
    private String bucket;

    @Value("${s3.url}")
    private String bucketUrl;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private UserRepository userRepo;

    // 이미지 업로드
    @Transactional
    public String uploadImage(MultipartFile multipartFile, UserDto userDto) throws IOException {
        String origName = multipartFile.getOriginalFilename();
        String url;
        try {
            // 확장자 찾고, 파일 이름 암호화
            final String ext = origName.substring(origName.lastIndexOf('.'));
            final String saveFileName = getUuid() + ext;

            // 파일 객체 생성 및 multipartFile 파일 변환
            // System.getProperty => 시스템 환경에 관한 정보 얻음 (user.dir = 현재 작업 디렉토리를 의미)
            File file = new File(System.getProperty("user.dir") + saveFileName);
            multipartFile.transferTo(file);

            // S3 파일 업로드 및 주소 할당
            uploadOnS3(saveFileName, file);
            url = bucketUrl + "/" + saveFileName;

            // 파일 삭제
            file.delete();

            // db에 저장
            if(userDto.getImage() != null) {
                // 원래 이미지 삭제하는 동작
            }
            userDto.setImage(saveFileName);
            userRepo.save(userDto.toEntity());
        } catch (StringIndexOutOfBoundsException e) {
            url = "fail";
        }
        return url;
    }

    // uuid 생성
    private static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // S3에 파일 업로드
    private void uploadOnS3(final String findName, final File file) {
        // AWS S3 전송 객체 생성
        final TransferManager transferManager = new TransferManager(this.amazonS3);
        // 요청 객체 생성
        final PutObjectRequest request = new PutObjectRequest(bucket, findName, file);
        // 업로드 시도
        final Upload upload =  transferManager.upload(request);

        try {
            upload.waitForCompletion();
        } catch (AmazonClientException | InterruptedException amazonClientException) {
            log.error(amazonClientException.getMessage());
        }
    }
}

// 이제 디비에 저장된게 파일 이름이니까..!! 바로 삭제하면 됨!!
//    public Boolean dropImage(String fileUrl, Long id) throws Exception {
//        // 키 구하기
//        String fileKey = fileUrl.substring(49); // 폴더/파일.확장자
//        System.out.println(">>>>>>>>>>>>> fileKey : " + fileKey);
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(region).build();
//
//        // fileKey 속성 null로 변환 및 s3에서 기존 파일(프로필 사진) 삭제
//        UserDto userDto;
//        if(userRepo.findById(id).isPresent()) {
//            userDto = userRepo.findById(id).get().toDto();
//            userDto.setImage(null);
//            userRepo.save(userDto.toEntity());
//            s3.deleteObject(bucket, fileKey);
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
