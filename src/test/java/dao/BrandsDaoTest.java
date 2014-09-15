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
import com.entities.Brand;
import com.entities.Device;
import com.entities.Dao.BrandsDao;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml",
		"classpath:test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("schema.xml")
public class BrandsDaoTest {

	@Autowired
	private BrandsDao brandsDao;
	private Brand brand;
	private Brand brand2;
	private Brand brand3;

	private static final int ID = 1;
	private static final int ID2 = 2;
	private static final int ID3 = 3;
	private static final int UID = 4;
	private static final String BRANDNAME = "Apple";
	private static final String UBRANDNAME = "Horizont";

	@Test
	public void findTest() {

		brand = brandsDao.find(ID);
		brand2 = brandsDao.find(UID);
		assertNull(brand2);
		makeAssertions(brand);
	}

	@Test
	public void findByNameTest() {

		brand = brandsDao.find(BRANDNAME);
		brand2 = brandsDao.find(UBRANDNAME);
		assertNotNull(brand);
		assertNull(brand2);
		makeAssertions(brand);
	}

	@Test
	public void getAllTest() {
		List<Brand> brands = brandsDao.getAll();
		assertNotNull(brands);
		assertEquals(3, brands.size());
	}

	@Test
	public void initDevicesTest() {

		brand2 = brandsDao.initDevices(ID2);
		brand3 = brandsDao.initDevices(ID3);
		brand = brandsDao.initDevices(ID);
		assertNotNull(brand2);
		assertNotNull(brand3);

		assertNotNull(brand.getDevices());
		assertNotNull(brand2.getDevices());
		assertNotNull(brand3);

		assertEquals(2, brand.getDevices().size());
		assertEquals(1, brand2.getDevices().size());
		assertEquals(0, brand3.getDevices().size());

		Device device = brand2.getDevices().get(0);
		assertNotNull(device);
		assertNotNull(device.getModel());
		makeAssertions(brand);
	}

	@Test
	public void createBrandTest() {

		assertEquals(3, brandsDao.getAll().size());
		brand = brandsDao.create(new Brand(UID, UBRANDNAME));
		assertNotNull(brand);
		assertEquals(UBRANDNAME, brand.getBrandName());
		assertEquals(UID, brand.getId());
		assertEquals(4, brandsDao.getAll().size());
	}

	@Test
	public void updateBrandTest() {

		brand = brandsDao.find(ID);
		makeAssertions(brand);
		assertFalse(brand.getBrandName().equals(UBRANDNAME));
		brand.setBrandName(UBRANDNAME);
		brand = brandsDao.update(brand);
		assertNotNull(brand);
		assertEquals(ID, brand.getId());
		assertEquals(UBRANDNAME, brand.getBrandName());
	}

	@Test
	public void deleteBrandTest() {

		assertEquals(3, brandsDao.getAll().size());
		brand3 = brandsDao.find(ID3);
		assertNotNull(brand3);
		brandsDao.delete(ID3);
		brand3 = brandsDao.find(ID3);
		assertNull(brand3);
		assertEquals(2, brandsDao.getAll().size());
	}

	private void makeAssertions(Brand brand) {

		assertNotNull(brand);
		assertEquals(ID, brand.getId());
		assertEquals(BRANDNAME, brand.getBrandName());
		assertEquals(3, brandsDao.getAll().size());
	}
}
