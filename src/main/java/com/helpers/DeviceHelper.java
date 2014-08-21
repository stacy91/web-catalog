package com.helpers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Configuration
@PropertySource("classpath:path.properties")
public class DeviceHelper {

    @Value("${imgsPath}")
    private String imgsPath;

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
        File file = new File(getImgsPath() + deviceId + ".jpg");
        return !file.exists() || file.delete();
    }

    public String getImgsPath() {
        return imgsPath;
    }

}

