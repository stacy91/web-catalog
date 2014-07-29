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
import com.entities.Brand;
import com.entities.Device_Color;
import com.entities.Device_Memory;
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
		/*if ( sessionFactory != null ) {
			sessionFactory.close();
		}*/
	}
	

	public void testBasicUsage() {
		Device_ModelDao dm = (Device_ModelDao)ctx.getBean(Device_ModelDao.class);
		BrandsDao bd = (BrandsDao)ctx.getBean(BrandsDao.class);

		/*dm.create(new Device_Model(1,"iPhone 6"));
		dm.create(new Device_Model(1,"iPhone 6s"));
		dm.create(new Device_Model(1,"iPhone 6q"));
		dm.create(new Device_Model(1,"iPhone 6a"));*/
		Brand brand = bd.findById(1);
		
		List<Device_Model> dms = bd.getDeviceModels(brand);
		Hibernate.initialize(dms);
		for(Device_Model item : dms)
		{
			System.out.println(item.getModelName());
		}
	}

}
