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
import com.dao.DevicesDao;
import com.entities.Device;
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
		BrandsDao bd = (BrandsDao)ctx.getBean(BrandsDao.class);
		DevicesDao dDao = (DevicesDao)ctx.getBean(DevicesDao.class);
		
		Device d = dDao.initProxy(dDao.findById(1));
		System.out.println(d.getBrand() + d.getModel());

	}

}
