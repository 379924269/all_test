package com.huazai.controller.uploadeFileAndGetProgress;

import com.huazai.vo.ProgressSingleton;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author 华仔
 * @date 2018/6/29 14:44
 */
public class ProgressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getSession().getId();
        String filename = request.getParameter("filename");
        //使用sessionid + 文件名生成文件号，与上传的文件保持一致
        id = id + filename;
        Object size = ProgressSingleton.get(String.format("%sSize", id));
        size = size == null ? 100 : size;
        Object progress = ProgressSingleton.get(String.format("%sProgress", id));
        progress = progress == null ? 0 : progress;
        JSONObject json = new JSONObject();
        json.put("size", size);
        json.put("progress", progress);
        response.getWriter().print(json.toString());
    }
}
