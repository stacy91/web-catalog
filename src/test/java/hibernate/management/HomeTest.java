package hibernate.management;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.dao.BrandDao;
import com.entities.Brand;
import com.entities.Device_Color;
import com.entities.Device_Memory;
import com.entities.Device_ScreenSize;
import com.models.entityModels.BrandModelDao;

import junit.framework.TestCase;


@SuppressWarnings("unused")
public class HomeTest extends TestCase{

	private ApplicationContext ctx;
	private BrandDao brandDao;
	
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
		brandDao = (BrandDao)ctx.getBean(BrandDao.class);
		brandDao.create(new Brand("Lenovov"));	
	}

}
