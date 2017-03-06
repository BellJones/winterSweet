package com.winterSweet.core.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.winterSweet.core.constant.Constant.UPLOAD_FILE_PATH;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/6 下午 10:25
 * <p>
 * Version: 1.0
 * Description: 文件上传功能
 */
@Service
public class FileUploadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtil.class.getName());

    /**
     * 文件上传功能 Ⅰ
     *      使用spring自带的工具来实现
     *
     * @param multipartFile 文件对象
     * @return T/F
     */
    public boolean fileUpload(MultipartFile multipartFile) {
        boolean target = false;
        // 获取文件真实名称
        String fileName = multipartFile.getOriginalFilename();
        // 构造唯一文件路径
        String path = UPLOAD_FILE_PATH + System.currentTimeMillis() + "_" + fileName;
        // 在目标路径下创建空白文件
        File file = new File(path);
        try {
            // 判断文件是否为空
            if (!multipartFile.isEmpty()) {
                // 日志记录上传文件的大小
                LOGGER.info("upload file size : {}", multipartFile.getSize());
                // 日志记录上传文件的字节
                LOGGER.info("upload file bytes : {}", multipartFile.getBytes());
                // 使用spring自带的工具将源文件写到目标文件中
                multipartFile.transferTo(file);
                // 上传成功无异常，返回 True
                target = true;
            }
        } catch (IOException e) {
            LOGGER.error("file upload error : {}", e.getMessage());
        }
        return target;
    }

    /**
     * 文件上传功能 Ⅱ
     *      使用java工具来实现
     *
     * @param multipartFile 文件对象
     * @return T/F
     */
    public boolean fileUploadStream(MultipartFile multipartFile) {
        boolean target = false;
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            byte[] bytes = new byte[2048];
            int length = inputStream.read(bytes);
            String path = UPLOAD_FILE_PATH + System.currentTimeMillis() + "_" + fileName;
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes, 0, length);
            inputStream.close();
            fileOutputStream.close();
            target = true;
        } catch (IOException e) {
            LOGGER.error("file upload error : {}", e.getMessage());
        }
        return target;
    }

    /**
     * 多文件上传功能 Ⅰ
     *      使用spring自带的工具来实现
     *
     * @param multipartFiles 多个文件对象组
     * @return T/F
     */
    public boolean filesUpload(MultipartFile[] multipartFiles) {
        if (null != multipartFiles && 0 < multipartFiles.length) {
            for (MultipartFile multipartFile : multipartFiles) {
                boolean target = fileUpload(multipartFile);
                if (!target) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 多文件上传功能 Ⅱ
     *      使用java工具来实现
     *
     * @param multipartFiles 多个文件对象组
     * @return T/F
     */
    public boolean filesUploadStream(MultipartFile[] multipartFiles) {
        if (null != multipartFiles && 0 < multipartFiles.length) {
            for (MultipartFile multipartFile : multipartFiles) {
                boolean target = fileUploadStream(multipartFile);
                if (!target) {
                    return false;
                }
            }
        }
        return true;
    }
}
