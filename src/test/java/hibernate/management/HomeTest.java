package hibernate.management;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.junit.Ignore;






import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entities.Brand;
import com.entities.Device_Color;
import com.entities.Device_Memory;
import com.entities.Device_ScreenSize;
import com.models.entityModels.BrandModelDao;

import junit.framework.TestCase;


@SuppressWarnings({ "deprecation", "unused" })
public class HomeTest extends TestCase{

	private ApplicationContext ctx;

	@Override
	protected void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
	}

	@Override
	protected void tearDown() throws Exception {
		/*if ( sessionFactory != null ) {
			sessionFactory.close();
		}*/
	}
	
	public void testBasicUsage() {
			
		BrandModelDao brandDao = ctx.getBean(BrandModelDao.class);
		brandDao.create(new Brand("Apple"));	
	}

}
