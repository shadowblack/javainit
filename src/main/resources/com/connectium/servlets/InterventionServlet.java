package com.connectium.servlets;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by lmarin on 12/6/2017.
 */
@WebServlet(urlPatterns = "/upload",name = "Inter")
public class InterventionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldname = item.getFieldName();
                    String filename = FilenameUtils.getName(item.getName());
                    InputStream filecontent = item.getInputStream();
                    // ... (do your job here)


                    response.setContentType("image/jpeg");

                   // Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

                    String pathToWeb = getServletContext().getRealPath(File.separator);
                    //File f = new File(pathToWeb + "avajavalogo.jpg");
                    // String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    InputStream fileContent = filecontent/*filePart.getInputStream()*/;
                    BufferedImage bi = ImageIO.read(fileContent);

                    ImageWriter writer = (ImageWriter)ImageIO.getImageWritersByFormatName("jpeg").next();
                    ImageWriteParam iwp = writer.getDefaultWriteParam();
                    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    iwp.setCompressionQuality(1);

                    //FileImageOutputStream output = new FileImageOutputStream(file);

                    writer.setOutput(bi);

                    OutputStream out = response.getOutputStream();
                    ImageIO.write(bi, "jpeg", out);
                    out.close();

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }



      /*  String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();*/
    }
}
