package dao;


import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@ContextConfiguration({ "classpath:app-context.xml" })
public class BrandsDaoTest {
	
	@Mock
	private BrandsDao brandsDao;
	private Brand brand;
	
	private static final int ID = 1;
	private static final String BRANDNAME = "apple";
	private static final String UBRANDNAME = "horizont";
	
	@Before
	public void init() {
	
		MockitoAnnotations.initMocks(this);
		brand = new Brand(ID,BRANDNAME);
		Brand brand2 = mock(Brand.class);
		List<Brand> brands = new ArrayList<Brand>();
		List<Device> devices = new ArrayList<Device>();
		devices.add(mock(Device.class));
		devices.add(mock(Device.class));		
		brands.add(brand);
		brands.add(brand2);
		when(brandsDao.create(brand)).thenReturn(brand);
		when(brandsDao.update(brand)).thenReturn(brand);
		when(brandsDao.find(ID)).thenReturn(brand);
		brand.setDevices(devices);
		when(brandsDao.initDevices(ID)).thenReturn(brand);
		when(brandsDao.getAll()).thenReturn(brands);
	}
	
	
	
	@Test
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
	}
}
