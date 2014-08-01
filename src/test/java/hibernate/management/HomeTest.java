package hibernate.management;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.dao.BrandsDao;
import com.dao.Device_ModelDao;
import com.dao.Device_ScreenSizeDao;
import com.dao.DevicesDao;
import com.entities.Brand;
import com.entities.Device;
import com.entities.Device_Model;
import com.entities.Device_ScreenSize;
import com.models.entityModels.BrandsImplDao;

import junit.framework.TestCase;


@SuppressWarnings("unused")
public class HomeTest extends TestCase{

	private ApplicationContext ctx;
	
	
	@Override
	protected void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
	}

	@Override
	protected void tearDown() throws Exception {

	}
	

	public void testBasicUsage() {
		Device_ModelDao dmd = (Device_ModelDao)ctx.getBean(Device_ModelDao.class);
		BrandsDao bd = (BrandsDao)ctx.getBean(BrandsDao.class);
		Device_ScreenSizeDao dssDao = (Device_ScreenSizeDao)ctx.getBean(Device_ScreenSizeDao.class);
		Brand brand = bd.initProxy(bd.findById(3));
		DevicesDao dDao = (DevicesDao)ctx.getBean(DevicesDao.class);
		
		/*dDao.create(new Device(dmd.findById(1),dssDao.findById(1),null,50,5));
		dDao.create(new Device(dmd.findById(2),dssDao.findById(2),null,50,5));
		dDao.create(new Device(dmd.findById(3),dssDao.findById(3),null,50,5));
		dDao.create(new Device(dmd.findById(5),dssDao.findById(2),null,50,5));
		dDao.create(new Device(dmd.findById(18),dssDao.findById(1),null,50,5));
		dDao.create(new Device(dmd.findById(19),dssDao.findById(1),null,50,5));
		dDao.create(new Device(dmd.findById(20),dssDao.findById(1),null,50,5));*/
		
		List<Device_Model> dms = brand.getDeviceModels();
		for(Device_Model item : dms)
		{
			System.out.println(item.getModelName());
		}
		System.out.println();System.out.println();System.out.println();System.out.println();
		Device_ScreenSize dss = dssDao.initProxy(dssDao.findById(1));
		
		for(Device item : dss.getDevices())
		{
			System.out.println(dDao.initProxy(item).getModel().getModelName());
		}
		
	}

}
