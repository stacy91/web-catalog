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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.entities.User;
import com.entities.UserRole;
import com.entities.Dao.UserRolesDao;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml",
		"classpath:test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("schema.xml")
public class UserRolesDaoTest {

	@Autowired
	private UserRolesDao userRolesDao;
	private UserRole userRole;
	private UserRole userRole2;

	private static final int ID = 1;
	private static final int ID2 = 2;
	private static final int UID = 3;
	private static final String ROLENAME = "admin";
	private static final String UROLENAME = "user";

	@Test
	public void findTest() {

		userRole = userRolesDao.find(ID);
		userRole2 = userRolesDao.find(UID);
		assertNotNull(userRole);
		assertNull(userRole2);
		makeAssertions(userRole);
	}

	@Test
	public void initUsersTest() {

		userRole = userRolesDao.initUsers(ID);

		assertNotNull(userRole.getUsers());

		assertEquals(2, userRole.getUsers().size());

		User user = userRole.getUsers().get(0);
		assertNotNull(user);
		assertNotNull(user.getLogin());

		makeAssertions(userRole);
	}

	@Test
	public void getAllTest() {

		List<UserRole> userRoles = userRolesDao.getAll();
		assertNotNull(userRoles);
		assertEquals(2, userRoles.size());
	}

	@Test
	public void createUserRoleTest() {

		assertEquals(2, userRolesDao.getAll().size());
		userRole = userRolesDao.create(new UserRole(UID, UROLENAME));
		assertNotNull(userRole);
		assertEquals(UROLENAME, userRole.getName());
		assertEquals(UID, userRole.getId());
		assertEquals(3, userRolesDao.getAll().size());
	}

	@Test
	public void updateBrandTest() {

		userRole = userRolesDao.find(ID);
		makeAssertions(userRole);
		assertFalse(userRole.getName().equals(UROLENAME));
		userRole.setName(UROLENAME);
		userRole = userRolesDao.update(userRole);
		assertNotNull(userRole);
		assertEquals(ID, userRole.getId());
		assertEquals(UROLENAME, userRole.getName());
	}

	@Test
	public void deleteBrandTest() {

		assertEquals(2, userRolesDao.getAll().size());
		userRole = userRolesDao.find(ID2);
		assertNotNull(userRole);
		userRolesDao.delete(ID2);
		userRole = userRolesDao.find(ID2);
		assertNull(userRole);
		assertEquals(1, userRolesDao.getAll().size());
	}

	private void makeAssertions(UserRole userRole) {

		assertNotNull(userRole);
		assertEquals(ID, userRole.getId());
		assertEquals(ROLENAME, userRole.getName());
		assertEquals(2, userRolesDao.getAll().size());
	}
}
