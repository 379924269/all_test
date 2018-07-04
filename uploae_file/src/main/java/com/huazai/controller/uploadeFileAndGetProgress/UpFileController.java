package com.huazai.controller.uploadeFileAndGetProgress;

import com.huazai.vo.ProgressSingleton;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
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
public class UpFileController extends HttpServlet {

    //访问http://127.0.0.1/test/uploadeFile.jsp
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024);

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> fileItems;
        InputStream in = null;
        FileOutputStream out = null;
        //文件进度长度
        long progress = 0;
        try {
            fileItems = upload.parseRequest(new ServletRequestContext(request));
            //获取文件域
            FileItem fileItem = fileItems.get(0);
            //使用sessionid + 文件名生成文件号
            String id = request.getSession().getId() + fileItem.getName();
            //向单例哈希表写入文件长度和初始进度
            ProgressSingleton.put(id + "Size", fileItem.getSize());
            //用流的方式读取文件，以便可以实时的获取进度
             in = fileItem.getInputStream();
            File file = new File("D:/test");
            file.createNewFile();
             out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readNumber;
            while ((readNumber = in.read(buffer)) != -1) {
                //每读取一次，更新一次进度大小
                progress += readNumber;
                //向单例哈希表写入进度
                ProgressSingleton.put(String.format("%sProgress", id), progress);
                out.write(buffer);
            }
            //当文件上传完成之后，从单例中移除此次上传的状态信息
            ProgressSingleton.remove(id + "Size");
            ProgressSingleton.remove(id + "Progress");
        } catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

        response.getWriter().print("done");
    }
}
