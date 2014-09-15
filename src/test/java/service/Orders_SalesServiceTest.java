package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entities.Brand;
import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.Dao.BrandsDao;
import com.entities.Dao.DevicesDao;
import com.entities.Dao.Orders_SalesDao;
import com.entities.Dao.UsersDao;
import com.entities.dto.BrandDto;
import com.entities.dto.DtoToEntity;
import com.entities.dto.Order_SaleDto;
import com.entities.servicesImpl.BrandsServiceImpl;
import com.entities.servicesImpl.Orders_SalesServiceImpl;
import com.helpers.FilteredCollection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml" })
public class Orders_SalesServiceTest {
	
	private static final int ID = 1;
	private static final int ID100 = 100;
	private static final boolean ISSOLD = false;
	private static final int AMOUNT = 1;
	
	private static final int UID = 2;
	private static final boolean UISSOLD = true;
	private static final int UAMOUNT = 2;
	private static final String SHOW = "oneItem";
	private static final String LOGIN = "testLogin";
	
	private Order_Sale o_s;
	private Order_Sale uO_s;
	private Order_SaleDto o_sDto;
	private Order_SaleDto uO_sDto;
	private List<Order_Sale> o_ss;
	private List<Order_SaleDto> o_ssDto;
	private List<Order_Sale> oneItemList;
	private List<Order_SaleDto> oneItemListDto;
	private Device device;
	private User user;
	
	@InjectMocks
	private Orders_SalesServiceImpl orders_salesService;
	@Mock
	private Orders_SalesDao order_salesDao;
	@Mock
	private UsersDao usersDao;
	@Mock
	private DevicesDao devicesDao;
	@Mock
	private DtoToEntity toEntity;
	
	
	@Before
	@SuppressWarnings("serial")
	public void init(){
		
		MockitoAnnotations.initMocks(this);
		device = mock(Device.class);
		user = mock(User.class);

		o_s = new Order_Sale(ID, device, user, ISSOLD, AMOUNT, null, null);
		uO_s = new Order_Sale(UID, device, user, UISSOLD, UAMOUNT, null, null);
		o_sDto = new Order_SaleDto(o_s);
		uO_sDto = new Order_SaleDto(uO_s);
		
		o_ss = new ArrayList<Order_Sale>();
		o_ssDto = new ArrayList<Order_SaleDto>();
		oneItemList = new ArrayList<Order_Sale>(){{add(o_s);}};
		oneItemListDto = new ArrayList<Order_SaleDto>(){{add(o_sDto);}};
		o_ss.add(o_s);
		o_ss.add(uO_s);
		o_ssDto.add(o_sDto);
		o_ssDto.add(uO_sDto);
		
		when(toEntity.convert(o_sDto)).thenReturn(o_s);
		when(toEntity.convert(uO_sDto)).thenReturn(uO_s);
		
		when(order_salesDao.create(o_s)).thenReturn(o_s);
		when(order_salesDao.update(o_s)).thenReturn(o_s);
		when(order_salesDao.create(uO_s)).thenReturn(uO_s);
		when(order_salesDao.update(uO_s)).thenReturn(uO_s);
		
		when(order_salesDao.getAll()).thenReturn(o_ss);
		when(order_salesDao.initOrder_Sale(ID)).thenReturn(o_s);
		when(order_salesDao.initOrder_Sale(ID100)).thenReturn(null);
	}
	
	
	@Test
	public void findTest(){
		
		o_sDto = orders_salesService.find(ID);
		verify(order_salesDao).initOrder_Sale(ID);
		uO_sDto = orders_salesService.find(ID100);
		verify(order_salesDao).initOrder_Sale(ID100);
		assertNull(uO_sDto);
		makeAssertions(o_sDto);
	}
	
	@Test
	public void getAllTest(){
		
		o_ssDto = orders_salesService.getAll();
		verify(order_salesDao).getAll();
		makeAssertions(o_ssDto.get(0));
	}
	
	/*
	
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
	
	*/
	
	private void makeAssertions(Order_SaleDto o_sDto){
		
		assertEquals(ID, o_sDto.getId());
		assertEquals(ISSOLD, o_sDto.getIsSold());
		assertEquals(AMOUNT, o_sDto.getAmount());
		assertNotNull(o_sDto.getDevice());
		assertNotNull(o_sDto.getUser());
	}
	
}
