package cloud.hexiaolei.webaiproject.controller;

import cloud.hexiaolei.webaiproject.pojo.Result;
import cloud.hexiaolei.webaiproject.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    private AliyunOSSOperator aliyunOSSOperator;
    @Autowired
    public UploadController(AliyunOSSOperator aliyunOSSOperator){
        this.aliyunOSSOperator = aliyunOSSOperator;
    }
//    /**
//     * 存储本地磁盘方式
//     * @param name
//     * @param age
//     * @param multipartFile
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, @RequestParam(value = "file")MultipartFile multipartFile) throws IOException {
//        log.info("接收到的参数{},{},{}",name,age,multipartFile);
//        //获取原始文件名
//        String originalFilename = multipartFile.getOriginalFilename();
//        //创建新文件名使用UUID加后缀
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID() +extension;
//        //将上传的文件保存到本次磁盘
//        multipartFile.transferTo(new File("C:\\images\\" + newFileName));
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload(@RequestParam(value = "file")MultipartFile multipartFile) throws Exception {
        log.info("插入文件{}",multipartFile.getOriginalFilename());
        String url = aliyunOSSOperator.upload(multipartFile.getBytes(), multipartFile.getOriginalFilename());
        log.info("文件上传至OSS，路径为:{}",url);
        return Result.success(url);
    }
}
