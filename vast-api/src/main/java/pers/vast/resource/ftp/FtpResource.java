package pers.vast.resource.ftp;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.vast.resource.BaseResource;
import pers.vast.service.ftp.FtpService;

import java.util.Map;

/**
 * Created by sengzin on 2018/4/6.
 */
@RestController
@RequestMapping("/ftp")
public class FtpResource extends BaseResource {

    @Autowired
    private FtpService ftpService;

    /**
     * 上传文件
     */
    @RequestMapping(method = RequestMethod.POST)
    public Map<String, String> upload(@RequestParam("file") MultipartFile fileStream) throws Exception {
        if (fileStream.isEmpty()) {
            response.setStatus(422);
            return ImmutableMap.of("message", "上传失败，文件为空");
        }
        return ImmutableMap.of("url", ftpService.upload(fileStream.getBytes()));
    }
}
