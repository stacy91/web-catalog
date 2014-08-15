package helpers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.entities.Device;


public class DeviceHelper {

	public static final String imgPath = "/resources/img/";
	
	public static boolean validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg"))
			return false;
		return true;
	}

	public static boolean saveImage(Device device, MultipartFile image) {
		try {
			File fXmlFile = new File("/Users/mkyong/staff.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder  dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String relPath = imgPath + device.getId() + ".jpg";
		File file = new File(device.getImageURL() + relPath);
		device.setImageURL(relPath);
		try {
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean deleteImage(String path){
		File file = new File(path);
		if(file.exists())
			file.delete();
		return true;
	}
}
