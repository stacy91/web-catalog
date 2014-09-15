package dao;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import com.entities.Arrival;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.Dao.UsersDao;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml",
		"classpath:test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("schema.xml")
public class UsersDaoTest {

	@Autowired
	private UsersDao usersDao;
	private User user;
	private User user2;

	private static final int ID = 1;
	private static final int ID2 = 2;
	private static final int UID = 3;
	private static final String LOGIN = "stas";
	private static final String ULOGIN = "stas3";
	private static final String PASSWORD = "stas";
	private static final String UPASSWORD = "stas3";

	@Test
	public void findByNameTest() {

		user = usersDao.find(LOGIN);
		user2 = usersDao.find(ULOGIN);
		assertNull(user2);
		makeAssertions(user);
	}

	@Test
	public void findUserTest() {

		user = usersDao.find(ID);
		user2 = usersDao.find(100);
		assertNull(user2);
		makeAssertions(user);
	}
	
	@Test
	public void initUserByNameTest(){
		
		user = usersDao.initUser(LOGIN);
		makeAssertions(user);
		assertFetched(user);
		
	}
	
	@Test
	public void initUserTest(){
		
		user = usersDao.initUser(ID);
		makeAssertions(user);
		assertFetched(user);
		
	}

	@Test
	public void initArrivalsTest() {
		
		user = usersDao.initArrivals(ID);
		user2 = usersDao.initArrivals(ID2);
		
		assertNotNull(user2);
		assertEquals(3, user.getArrivals().size());
		assertEquals(0, user2.getArrivals().size());

		Arrival arrival = user.getArrivals().get(0);
		assertNotNull(arrival);
		assertNotNull(arrival.getDevice());
		makeAssertions(user);
	}

	@Test
	public void initOrders_SalesTest() {

		user = usersDao.initOS(ID);
		user2 = usersDao.initOS(ID2);
		assertNotNull(user2);
		assertEquals(3, user.getOrders_Sales().size());
		assertEquals(0, user2.getOrders_Sales().size());
		Order_Sale o_s = user.getOrders_Sales().get(0);
		assertNotNull(o_s);
		assertNotNull(o_s.getDevice());
		makeAssertions(user);
	}

	@Test
	public void createUserTest() {
		assertEquals(2, usersDao.getAll().size());
		user2 = usersDao.find(ID2);
		user = usersDao
				.create(new User(UID, user2.getRole(), ULOGIN, UPASSWORD));
		
		assertEquals(UID, user.getId());
		assertEquals(ULOGIN, user.getLogin());
		assertEquals(UPASSWORD, user.getPassword());
		assertEquals(3, usersDao.getAll().size());
	}

	@Test
	public void updateUserTest() {

		user2 = usersDao.find(ID2);
		user = usersDao.find(ID);
		makeAssertions(user);
		assertFalse(user.getLogin().equals(ULOGIN));
		assertFalse(user.getPassword().equals(UPASSWORD));
		user.setLogin(ULOGIN);
		user.setPassword(UPASSWORD);
		user.setRole(user2.getRole());
		user = usersDao.update(user);
		assertNotNull(user);
		
		assertEquals(ID, user.getId());
		assertEquals(ULOGIN, user.getLogin());
		assertEquals(UPASSWORD, user.getPassword());
	}

	@Test
	public void deleteDeviceTest() {

		assertEquals(2, usersDao.getAll().size());
		user2 = usersDao.find(ID2);
		assertNotNull(user2);
		usersDao.delete(ID2);
		user2 = usersDao.find(ID2);
		assertNull(user2);
		assertEquals(1, usersDao.getAll().size());
	}

	@Test
	public void getAllTest() {

		List<User> users = usersDao.getAll();
		assertNotNull(users);
		assertEquals(2, users.size());
	}

	private void makeAssertions(User user) {

		assertNotNull(user);
		assertEquals(ID, user.getId());
		assertEquals(LOGIN, user.getLogin());
		assertEquals(PASSWORD, user.getPassword());
	}
	
	public void assertFetched(User user){
		
		assertNotNull(user.getRole());
		assertNotNull(user.getRole().getName());
	}

}
