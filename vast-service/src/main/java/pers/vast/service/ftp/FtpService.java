package pers.vast.service.ftp;

import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * 文件服务
 * Created by sengzin on 2018/4/6.
 */
@Service
public class FtpService {

    private static final String UPLOAD_FILE_PATH = "/usr/local/nginx/html/ftp/";
    // private static final String TEST_UPLOAD_FILE_PATH = "C:\\Users\\11353\\Pictures\\";

    /**
     * 上传
     */
    public String upload(byte[] bytes) throws Exception {
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        File uploadFile = new File(UPLOAD_FILE_PATH + fileName);
        uploadFile.createNewFile();
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(uploadFile))) {
            out.write(bytes);
            out.flush();
        }
        return "http://47.98.112.70:9999/ftp/" + fileName;
    }

}
