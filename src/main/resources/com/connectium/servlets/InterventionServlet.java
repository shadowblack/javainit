package com.connectium.servlets;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
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
 * Created by lmarin on 19/9/2017.
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

                    InputStream fileContent = filecontent/*filePart.getInputStream()*/;
                    BufferedImage bi = ImageIO.read(fileContent);

                    // guarda en el disco duro la informacion

                    /*ImageWriter writer = (ImageWriter)ImageIO.getImageWritersByFormatName("jpeg").next();
                    ImageWriteParam iwp = writer.getDefaultWriteParam();
                    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    iwp.setCompressionQuality(0.1f);

                    writer.setOutput(new FileImageOutputStream(
                            new File(*//*folder.toString() +*//* "/" + filename + ".jpg")));
                    writer.setOutput(new FileImageOutputStream(
                            new File(*//*folder.toString() +*//* *//*"J:\\hitok\\Compartido/"*//* "./"+ filename + "_temp.jpg")));

                    writer.write(null, new IIOImage(bi, null, null), iwp);*/

//                    writer.setOutput(bi);

                    ByteArrayOutputStream compressed = new ByteArrayOutputStream();
                    ImageOutputStream outputStream = ImageIO.createImageOutputStream(compressed);
                    ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();

                    ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
                    jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    jpgWriteParam.setCompressionQuality(0.1f);

                    jpgWriter.setOutput(outputStream);
                    jpgWriter.write(null, new IIOImage(bi, null, null), jpgWriteParam);

                    // mostrando archivo original
                    /*OutputStream out = response.getOutputStream();
                    ImageIO.write(bi, "jpeg", out);*/

                    // mostrando archivo comprimido
                    // Dispose the writer to free resources
                    jpgWriter.dispose();

                    // Get data for further processing...
                    byte[] jpegData = compressed.toByteArray();

                    response.getOutputStream().write(jpegData);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }
}
