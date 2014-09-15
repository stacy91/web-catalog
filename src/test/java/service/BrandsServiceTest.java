package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.entities.Brand;
import com.entities.Dao.BrandsDao;
import com.entities.dto.BrandDto;
import com.entities.dto.DtoToEntity;
import com.entities.servicesImpl.BrandsServiceImpl;
import com.helpers.FilteredCollection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml" })
public class BrandsServiceTest {
	
	private static final int ID = 1;
	private static final int ID100 = 100;
	private static final String BRANDNAME = "Apple";
	private static final int UID = 2;
	private static final String UBRANDNAME = "Samsung";
	
	private Brand brand;
	private Brand uBrand;
	private BrandDto brandDto;
	private BrandDto uBrandDto;
	private List<Brand> brands;
	private List<BrandDto> brandsDto;
	
	@InjectMocks
	private BrandsServiceImpl brandsService;
	@Mock
	private BrandsDao brandsDao;
	@Mock
	private DtoToEntity toEntity;
	
	@Before
	public void init(){
		
		MockitoAnnotations.initMocks(this);
		brand = new Brand(ID,BRANDNAME);
		uBrand = new Brand(UID,UBRANDNAME);
		brandDto = new BrandDto(brand);
		uBrandDto = new BrandDto(uBrand);
		brands = new ArrayList<Brand>();
		brandsDto = new ArrayList<BrandDto>();
		brands.add(brand);
		brands.add(uBrand);
		brandsDto.add(brandDto);
		brandsDto.add(uBrandDto);
		when(brandsDao.create(uBrand)).thenReturn(uBrand);
		when(brandsDao.update(uBrand)).thenReturn(uBrand);
		when(brandsDao.create(brand)).thenReturn(brand);
		when(brandsDao.update(brand)).thenReturn(brand);
		when(toEntity.convert(brandDto)).thenReturn(brand);
		when(toEntity.convert(uBrandDto)).thenReturn(uBrand);
		
		when(brandsDao.find(BRANDNAME)).thenReturn(brand);
		when(brandsDao.find(UBRANDNAME)).thenReturn(null);
		when(brandsDao.find(ID)).thenReturn(brand);	
		when(brandsDao.find(ID100)).thenReturn(null);
		when(brandsDao.getAll()).thenReturn(brands);		
	}
	
	@Test
	public void findTest(){
		
		brandDto = brandsService.find(ID);
		verify(brandsDao).find(ID);
		uBrandDto = brandsService.find(ID100);
		verify(brandsDao).find(ID100);
		assertNull(uBrandDto);
		makeAssertions(brandDto);
	}
	
	@Test
	public void getAllTest(){
		
		brandsDto = brandsService.getAll();
		verify(brandsDao).getAll();
		assertEquals(brandsDto.size(), 2);
		makeAssertions(brandsDto.get(0));
	}
	
	@Test
	public void getFilteredTest(){
		
		FilteredCollection<BrandDto> fColl = brandsService.getFiltered(brandsDto, 1);
		assertNotNull(fColl);
		assertEquals(1, fColl.getBegin());
		assertEquals(1, fColl.getEnd());
		assertEquals(1, fColl.getTotalPages());
		assertEquals(1, fColl.getCurrentPage());
		assertEquals(2, fColl.getItems().size());
	}
	
	@Test
	public void createTest(){
		
		brandDto = brandsService.create(brandDto);
		uBrandDto = brandsService.create(uBrandDto);
		verify(brandsDao).find(BRANDNAME);
		verify(brandsDao,never()).create(brand);
		
		verify(brandsDao).find(UBRANDNAME);
		verify(brandsDao).create(uBrand);
		assertNull(brandDto);
		assertNotNull(uBrandDto);
		assertEquals(UID, uBrandDto.getId());
		assertEquals(UBRANDNAME,uBrandDto.getBrandName());
	}
	
	@Test
	public void updateTest(){
		
		brandDto = brandsService.update(brandDto);
		uBrandDto = brandsService.update(uBrandDto);
		
		verify(brandsDao).find(BRANDNAME);
		verify(brandsDao,never()).update(brand);
		
		verify(brandsDao).find(UBRANDNAME);
		verify(brandsDao).update(uBrand);
		assertNull(brandDto);
		assertNotNull(uBrandDto);
		assertEquals(UID, uBrandDto.getId());
		assertEquals(UBRANDNAME,uBrandDto.getBrandName());
	}
	
	@Test
	public void deleteTest(){
		
		brandsService.delete(ID);
		verify(brandsDao).delete(ID);
	}
	
	private void makeAssertions(BrandDto brandDto){
		
		assertNotNull(brandDto);
		assertEquals(ID, brandDto.getId());
		assertEquals(BRANDNAME,brandDto.getBrandName());
	}
}
