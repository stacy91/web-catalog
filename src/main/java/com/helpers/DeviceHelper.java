package com.helpers;

import java.io.File;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class DeviceHelper {
	
	
	private ServletContext servletContext;
	private String imgsPath;
	
	@Autowired
	public DeviceHelper(ServletContext servletContext){
		this.servletContext = servletContext;
		imgsPath = initImgsPath();
	}
	public DeviceHelper(){

	}
	
	
	private String initImgsPath(){
		try{
		File fXmlFile = new File(servletContext.getRealPath("/WEB-INF/properties.xml"));
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder  dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList lNode = doc.getElementsByTagName("imgPath");
		Node node = lNode.item(0);
		String path = node.getAttributes().getNamedItem("path").getNodeValue() ;
		return path;
		}
		catch (Exception e){
			return null;
		}
	}
	
	public boolean validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg"))
			return false;
		return true;
	}

	public boolean saveImage(int deviceId, MultipartFile image) {
		String imgsPath = null;
		try {

			imgsPath = getImgsPath();
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
		if(file.exists())
			file.delete();
		return true;
	}
	
	public String getImgsPath() {
		return imgsPath;
	}

}

