package hibernate.management;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.junit.Ignore;






import com.entities.Brand;
import com.entities.Device_Color;
import com.entities.Device_Memory;
import com.entities.Device_ScreenSize;
import com.models.entityModels.BrandModelDao;

import junit.framework.TestCase;


@SuppressWarnings({ "deprecation", "unused" })
public class HomeTest extends TestCase{

	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
        sessionFactory = new Configuration()
                .configure() 
                .buildSessionFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
	
	public void testBasicUsage() {
		BrandModelDao brandDao = new BrandModelDao();
		brandDao.create(new Brand("Apple"));
	}

}
