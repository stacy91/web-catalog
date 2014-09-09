package dao;


import org.junit.Before;
import org.junit.Ignore;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.entities.Brand;
import com.entities.Dao.BrandsDao;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml" })
public class BrandsDaoTest {
	
	@Mock
	private BrandsDao brandsDao;
	private Brand brand;
	
	@Before
	public void init() {
	
		MockitoAnnotations.initMocks(this);
		brand = new Brand(1,"apple");

		
	}
	
	@Test
	public void testCreateBrand() {
		brand = brandsDao.create(brand);
		verify(brandsDao, times(1)).create(brand);
		
	}
}
