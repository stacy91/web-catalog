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
import org.springframework.web.servlet.ModelAndView;

import com.controller.management.BrandsController;
import com.dao.BrandsDao;
import com.dao.DevicesDao;
import com.dao.UserRolesDao;
import com.dao.UsersDao;
import com.entities.Brand;
import com.entities.Device;
import com.entities.User;
import com.entities.UserRole;
import com.models.entityModels.BrandsImplDao;

import junit.framework.TestCase;

@Ignore
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
		/*BrandsDao bd = (BrandsDao)ctx.getBean(BrandsDao.class);
		DevicesDao dDao = (DevicesDao)ctx.getBean(DevicesDao.class);
		UserRolesDao uRDAo = (UserRolesDao)ctx.getBean(UserRolesDao.class);
		UsersDao uDao = (UsersDao)ctx.getBean(UsersDao.class);
		
		Device d = dDao.initProxy(dDao.findById(1));
		System.out.println(d.getBrand().getBrandName()+" " + d.getModel());
		
		System.out.println("\n\n\n\n");
		Brand b = bd.initProxy(bd.findById(1));
		
		for(Device dev : b.getDevices())
		{
			System.out.println(dev.getModel());
		}
		
		for(UserRole ur : uRDAo.getAllUserRoleValues())
		{
			System.out.println(ur.getName());
		}
		
		UserRole ur = uRDAo.initProxy(3);
		User u = uDao.initProxy(1);
		
		for(User uu : ur.getUsers())
		{
			System.out.println(uu.getLogin());
		}
		
		System.out.println(u.getRole().getName());
		
		for(Brand bbr : bd.getAllBrandValues())
		{
			System.out.println(bbr.getBrandName());
		}*/
		
		/*BrandsController bc = new BrandsController();
		ModelAndView mv = bc.showBrands();
		System.out.println();*/
	}

}
