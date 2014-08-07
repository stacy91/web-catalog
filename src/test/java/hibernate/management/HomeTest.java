package hibernate.management;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.joda.time.DateTime;
import com.controller.management.BrandsController;
import com.dao.ArrivalsDao;
import com.dao.BrandsDao;
import com.dao.DevicesDao;
import com.dao.Orders_SalesDao;
import com.dao.UserRolesDao;
import com.dao.UsersDao;
import com.entities.Arrival;
import com.entities.Brand;
import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.UserRole;
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
	

	/*UserRolesDao uRDAo = (UserRolesDao)ctx.getBean(UserRolesDao.class);
	
	
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
	
	/*BrandsController bc = new BrandsController(bd);
	ModelAndView mv = bc.showBrands();
	System.out.println();*/
	
	/*BrandsController bc = (BrandsController)ctx.getBean(BrandsController.class);
	bc.showBrands();*/
	
	/*oDao.create(new Order_Sale(dDao.findById(1),uDao.findById(2),
	false,2,new DateTime().toDate()));
oDao.create(new Order_Sale(dDao.findById(2),uDao.findById(2),
	false,2,new DateTime().toDate()));
oDao.create(new Order_Sale(dDao.findById(3),uDao.findById(2),
	false,2,new DateTime().toDate()));
oDao.create(new Order_Sale(dDao.findById(3),uDao.findById(2),
	false,2,new DateTime().toDate()));
oDao.create(new Order_Sale(dDao.findById(4),uDao.findById(1),
	false,2,new DateTime().toDate()));
oDao.create(new Order_Sale(dDao.findById(4),uDao.findById(2),
	false,2,new DateTime().toDate()));
oDao.create(new Order_Sale(dDao.findById(4),uDao.findById(3),
	false,2,new DateTime().toDate()));*/
	
	/*User user = uDao.initOrders_Sales(2);
	
	for(Order_Sale a : user.getOrders_Sales())
	{
		System.out.println(a.getDevice().getModel() + " " + a.getDevice().getBrand().getBrandName());
		
	}*/
	
	/*Device d = dDao.initOrders_Sales(4);
	
	for(Order_Sale o : d.getOrders_Sales())
	{
		System.out.println(o.getUser().getLogin() + " " + o.getUser().getRole().getName());
	}*/
	
	/*BrandsDao bDao = (BrandsDao)ctx.getBean(BrandsDao.class);
	DevicesDao dDao = (DevicesDao)ctx.getBean(DevicesDao.class);
	Orders_SalesDao oDao = (Orders_SalesDao)ctx.getBean(Orders_SalesDao.class);
	UsersDao uDao = (UsersDao)ctx.getBean(UsersDao.class);
	
	Order_Sale o = oDao.initProxy(1);
	System.out.println(o.getDevice().getBrand().getBrandName() + " " +
	o.getDevice().getModel() + " " + 
			o.getUser().getRole().getName() + " " +
	o.getUser().getLogin());*/
	
	public void testBasicUsage() {
		
		
	}

}
