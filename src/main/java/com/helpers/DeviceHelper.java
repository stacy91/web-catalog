package com.helpers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

@Configuration
@PropertySource("classpath:path.properties")
public class DeviceHelper {

    @Value("${imgsPath}")
    private String imgsPath;
   
    private final String defImgPath = "/resources/img/default.jpg";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public DeviceHelper(){

    }

    public boolean validateImage(MultipartFile image) {
        return image.getContentType().equals("image/jpeg");
    }

    public boolean saveImage(int deviceId, MultipartFile image) {
        try {
            String imgName = deviceId + ".jpg";
            File file = new File(imgsPath + imgName);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean deleteImage(int deviceId){
        File file = new File(imgsPath + deviceId + ".jpg");
        return !file.exists() || file.delete();
    }
    
    public byte[] getImgBytes(String id, HttpServletRequest request) throws IOException{
    	BufferedImage bImage = null;
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try{
    		bImage = ImageIO.read(new File(imgsPath + id + ".jpg"));
    	}
    	catch (IOException ex){
    		String path = request.getSession().getServletContext().getRealPath(defImgPath);
    		bImage = ImageIO.read(new File(path));
    	}
    	finally{
    		ImageIO.write(bImage, "jpg", baos);
    	}	
    	return baos.toByteArray();
    }

}

