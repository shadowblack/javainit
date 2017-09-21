package com.connectium.controllers;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultipartRequest {
    Logger log = Logger.getLogger("M3.CONECTIUM.COM");
    List items = null;

    public MultipartRequest(HttpServletRequest req) {
        DiskFileUpload upload = new DiskFileUpload();

        try {
            items = upload.parseRequest(req);
        } catch (org.apache.commons.fileupload.FileUploadException e) {
            log.error(e);
        }
    }

    public String getParameter(String key) {
        String value = null;

        if (items != null) {
            boolean found = false;
            Iterator i = items.iterator();

            while (i.hasNext() && !found) {
                FileItem item = (FileItem) i.next();

                if (item.getFieldName().equals(key)) {
                    found = true;
                    value = item.getString();
                }
            }
        }

        return value;
    }

    public String[] getParameterValues(String key) {

        ArrayList values = new ArrayList();
        String[] array = null;

        if (items != null) {

            Iterator i = items.iterator();

            while (i.hasNext()) {
                FileItem item = (FileItem) i.next();

                if (item.getFieldName().equals(key) && item.getString() != null
                        && !item.getString().equals("")) {
                    values.add(item.getString());
                }
            }
            int n = values.size();
            array = new String[n];

            for (int j = 0; j < n; j++) {
                array[j] = (String) values.get(j);
            }
        }
        return array;
    }

    public String[] getArchivos(String key) {

        ArrayList values = new ArrayList();
        String[] array = null;

        if (items != null) {
            Iterator i = items.iterator();

            while (i.hasNext()) {
                FileItem item = (FileItem) i.next();

                if (item.getFieldName().equals(key) && item.getString() != null
                        && !item.getString().equals("")) {
                    values.add(item.getName());
                }
            }

            int n = values.size();
            log.debug(values);
            array = new String[n];

            for (int j = 0; j < n; j++) {
                array[j] = (String) values.get(j);
            }
        }
        return array;
    }

    public List getParameterNames() {
        FileItem item;
        String nombreItem;
        List parameterNames = new ArrayList();
        int j = 0;

        while (j < items.size()) {
            item = (FileItem) items.get(j);
            nombreItem = item.getFieldName();
            parameterNames.add(nombreItem);
            j++;
        }
        return parameterNames;
    }

    public String getArchivo(String key) {
        String value = null;

        if (items != null) {
            boolean found = false;
            Iterator i = items.iterator();

            while (i.hasNext() && !found) {
                FileItem item = (FileItem) i.next();

                if (item.getFieldName().equals(key)) {
                    found = true;
                    value = item.getName();
                }
            }
        }
        return value;
    }

    public void deleteArchivo(String filename) {
        File file = new File(filename);
        file.delete();
    }

    public int getSize(String key) {
        int value = 0;

        if (items != null) {
            boolean found = false;
            Iterator i = items.iterator();

            while (i.hasNext() && !found) {
                FileItem item = (FileItem) i.next();

                if (item.getFieldName().equals(key)) {
                    found = true;
                    Long lvalue = new Long(item.getSize());
                    value = lvalue.intValue();
                }
            }
        }

        return value;
    }

    public void fileSaveAs(String key, String filename) {

        String name = null;
        if (items != null) {
            Iterator i = items.iterator();
            boolean found = false;
            File file;

            while (i.hasNext() && !found) {
                FileItem item = (FileItem) i.next();
                name = filename.substring(
                        filename.lastIndexOf(File.separator) + 1,
                        filename.length());

                if (item.getFieldName().equals(key) && item.getName() != null
                        && item.getName().equals(name)) {
                    found = true;
                    file = new File(filename);

                    try {

                        item.write(file);
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }
    }

    public void fileSaveAsAdmin(String key, String filename, String name) {

        String namitem = null;
        String nombre = null;
        if (items != null) {
            Iterator i = items.iterator();
            boolean found = false;
            File file;

            while (i.hasNext() && !found) {
                FileItem item = (FileItem) i.next();
                if (item.getName() != null && !item.getName().equals("")) {
                    nombre = item.getName();
                    namitem = nombre.substring(nombre.length() - name.length());
                }
                if (item.getFieldName().equals(key) && item.getName() != null) {
                    found = true;
                    file = new File(filename);

                    try {
                        item.write(file);
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }
    }

    /**
    * @author: lmarin
     * date: 21.09.2017
     * permite voltear las imagenes a ciertos grados
    * */
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

    public void fileSaveAsNombres(String key, String filename, String name) {

        String namitem = null;
        String nombre = null;
        if (items != null) {
            Iterator i = items.iterator();
            boolean found = false;
            File file;

            while (i.hasNext() && !found) {
                FileItem item = (FileItem) i.next();
                if (item.getName() != null && !item.getName().equals("")) {
                    nombre = item.getName();
                    nombre = name;
                    namitem = nombre.substring(nombre.length() - name.length());
                }
                if (item.getFieldName().equals(key) && item.getName() != null) {
                    found = true;
                    file = new File(filename);

                    try {
                        item.write(file);
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }
    }

    /**
    * @author: lmarin
    * @date: 21.09.2017
    * Logica copiada del metodo fileSaveAsName, pero se incluyen nuevas funcionalidades como comprimir
    * imagenes y girar dependiendo de sus grados recibidos por parametros
    * */
    public void fileSaveAsName(String key, String filename, String name) {

        String namitem = null;
        String nombre = null;

        // logica para girar la imagen
        Integer grados = 0;
        if (items != null) {
            Iterator i = items.iterator();
            boolean found = false;
            File file;

            while (i.hasNext() && !found) {
                FileItem item = (FileItem) i.next();

                // validando si es un formulario comun
                if (item.isFormField()){
                    if (item.getFieldName().equals("grados")){
                        grados = Integer.parseInt(item.getString());
                    }
                }
                if (item.getName() != null && !item.getName().equals("")) {
                    nombre = item.getName();
                    nombre = name;
                    namitem = nombre.substring(nombre.length() - name.length());
                }
                if (item.getFieldName().equals(key) && item.getName() != null) {
                    if (!item.isFormField()){
                        found = true;
                        try {
                            InputStream filecontent = item.getInputStream();
                            BufferedImage bi = ImageIO.read(filecontent);
                            if (grados > 0)
                                for (int y = 1; y <= (grados / 90); y++){
                                    bi = rotateCw(bi);
                                }

                            // comprimiendo imagenes
                            ImageWriter writer = (ImageWriter)ImageIO.getImageWritersByFormatName("jpeg").next();
                            ImageWriteParam iwp = writer.getDefaultWriteParam();
                            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                            iwp.setCompressionQuality(0.5f);

                            // guardando en la carpeta
                            writer.setOutput(new FileImageOutputStream(
                                    new File(filename)));

                            writer.write(null, new IIOImage(bi, null, null), iwp);
                            writer.dispose();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}