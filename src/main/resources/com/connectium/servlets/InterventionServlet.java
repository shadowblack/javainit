package com.connectium.servlets;



import com.connectium.controllers.MultipartRequest;
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
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;


/**
 * Created by lmarin on 19/9/2017.
 */
@WebServlet(urlPatterns = "/upload",name = "Inter")
public class InterventionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MultipartRequest multi = new MultipartRequest(request);

        multi.fileSaveAsName("file", "prueba", "prueba");

        try {

            Integer grados = 0;

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();

                    if (fieldname.equals("grados")){
                        grados = Integer.parseInt(fieldvalue);
                    }

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

                    bi = resizeImage(bi,(bi.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bi.getType()),200,200);
                    /*bi = scale(bi,0.5);
                    if (bi.getHeight() > 0){
                        bi = rotateCw(bi);
                    }*/

                    for (int i = 1; i <= (grados / 90); i++){
                        bi = rotateCw(bi);
                    }


                    // guarda en el disco duro la informacion

                    ImageWriter writer = (ImageWriter)ImageIO.getImageWritersByFormatName("jpeg").next();
                    ImageWriteParam iwp = writer.getDefaultWriteParam();
                    iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    iwp.setCompressionQuality(0.5f);

                   /* writer.setOutput(new FileImageOutputStream(
                            new File(folder.toString() + "/" + filename + ".jpg")));*/
                    writer.setOutput(new FileImageOutputStream(
                            new File(/*folder.toString()*/  "C:\\Users\\user\\Desktop\\HTML WEB CNTM\\"+ filename + "2_temp.jpg")));

                    writer.write(null, new IIOImage(bi, null, null), iwp);

                    writer.dispose();

//                    writer.setOutput(bi);

                    ByteArrayOutputStream compressed = new ByteArrayOutputStream();
                    ImageOutputStream outputStream = ImageIO.createImageOutputStream(compressed);
                    ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpeg").next();

                    ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
                    jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    jpgWriteParam.setCompressionQuality(1f);


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


    private BufferedImage scale(BufferedImage source,double ratio) {
        int w = (int) (source.getWidth() * ratio);
        int h = (int) (source.getHeight() * ratio);
        BufferedImage bi = getCompatibleImage(200, h);
        Graphics2D g2d = bi.createGraphics();
        double xScale = (double) w / source.getWidth();
        double yScale = (double) h / source.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
        g2d.drawRenderedImage(source, at);
        g2d.dispose();
        return bi;
    }

    private BufferedImage getCompatibleImage(int w, int h) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        return image;
    }

    private static BufferedImage scale2(BufferedImage before, double scale) {
        int w = before.getWidth();
        int h = before.getHeight();
        // Create a new image of the proper size
       /* int w2 = (int) (w * scale);
        int h2 = (int) (h * scale);*/
        int w2 = (int) (200);
        int h2 = (int) (200);
        BufferedImage after = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB);
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp
                = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

        Graphics2D g2 = (Graphics2D) after.getGraphics();
        // Here, you may draw anything you want into the new image, but we're
        // drawing a scaled version of the original image.
        g2.drawImage(before, scaleOp, 0, 0);
        g2.dispose();
        return after;
    }

    private static BufferedImage scale1(BufferedImage before, double scale) {
        int w = before.getWidth();
        int h = before.getHeight();
        // Create a new image of the proper size
      /*  int w2 = (int) (w * scale);
        int h2 = (int) (h * scale);*/

        int w2 = (int) 200;
        int h2 = (int) 200;
        BufferedImage after = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB);
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp
                = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

        scaleOp.filter(before, after);
        return after;
    }


    /*public static BufferedImage resizeImage(BufferedImage image,int type, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }*/

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height)
    {

        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();

        return resizedImage;
    }

    public static BufferedImage rotateCw( BufferedImage img )
    {
        int         width  = img.getWidth();
        int         height = img.getHeight();
        BufferedImage   newImage = new BufferedImage( height, width, img.getType() );

        for( int i=0 ; i < width ; i++ )
            for( int j=0 ; j < height ; j++ )
                newImage.setRGB( height-1-j, i, img.getRGB(i,j) );

        return newImage;
    }
}
