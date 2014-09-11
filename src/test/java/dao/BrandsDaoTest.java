package dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import com.entities.Brand;
import com.entities.Device;
import com.entities.Dao.BrandsDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml","classpath:test-context.xml" })

public class BrandsDaoTest {
	
	@Autowired
	private BrandsDao brandsDao;
	private Brand brand;
	
	private static final int ID = 1;
	private static final String BRANDNAME = "apple";
	private static final String UBRANDNAME = "horizont";
	
	@Before
	public void init() {
	
		brandsDao.create(new Brand(1,"appleaaaaa"));
	}
	
	@Test
	public void find(){
		
		Brand brand = brandsDao.find(1);
		assertNotNull(brand);
		assertEquals("appleaaaaa", brand.getBrandName());
	}
	
	/*@Test
	public void testCreateBrand() {
		brand = brandsDao.create(brand);
		verify(brandsDao).create(brand);
		makeAssertions(brand);
	}
	
	@Test
	public void testUpdateBrand(){
		assertFalse("not updated yet",brand.getBrandName().equals(UBRANDNAME));
		brand.setBrandName(UBRANDNAME);
		brand = brandsDao.update(brand);
		verify(brandsDao).update(brand);
		assertNotNull(brand);
		assertEquals(UBRANDNAME, brand.getBrandName());
	}
	
	@Test
	public void testDeleteBrand(){
		brandsDao.delete(ID);
		verify(brandsDao).delete(ID);
		
	}
	
	@Test
	public void testGetAll(){
		List<Brand> brands = brandsDao.getAll();
		verify(brandsDao).getAll();
		assertNotNull(brands);
		assertEquals(2, brands.size());
	}
	
	@Test
	public void testFind(){
		
		brand = brandsDao.find(ID);
		verify(brandsDao).find(ID);
		makeAssertions(brand);
	}
	
	@Test
	public void initDevices(){
		
		brand = brandsDao.initDevices(ID);
		verify(brandsDao).initDevices(ID);
		assertEquals(2,brand.getDevices().size());
		makeAssertions(brand);		
	}
	
	private void makeAssertions(Brand brand) {
		assertNotNull(brand);
		assertTrue(brand.getId() > 0);
		assertEquals(ID, brand.getId());
		assertEquals(BRANDNAME,brand.getBrandName());
	}*/
}
