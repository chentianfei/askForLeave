package com.ctf.utils;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @ClassName UploadUtils
 * @Author tianfeichen
 * @Date 2021/12/30 16:20
 * @Version v1.0
 */
public class UploadUtils {
    //定义上传文件的存放路径
    private static final String personInfoSavePath = "C:"+File.separator+"LocalFiles";

    //获取请求中的文件，并保存到指定位置
    public static String uploadPersonInfo(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        String filePath = "";
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析请求的内容提取文件数据
        @SuppressWarnings("unchecked")
        List<FileItem> formItems = upload.parseRequest(request);

        // 迭代表单数据
        for (FileItem item : formItems) {
            // 处理不在表单中的字段
            if (!item.isFormField()) {
                String fileName = item.getName();
                int index = fileName.lastIndexOf(File.separator);
                if (index != -1) {
                    fileName = fileName.substring(index + 1);
                }
                //定义上传文件的完整路径
                filePath = String.format("%s"+File.separator+"%s", personInfoSavePath, fileName);
                File storeFile = new File(filePath);
                // 保存文件到硬盘
                item.write(storeFile);
                if(!storeFile.exists()){
                    //存在
                    return filePath;
                }else {
                    throw new Exception("上传失败");
                }
            }
        }
        return null;
    }

}
