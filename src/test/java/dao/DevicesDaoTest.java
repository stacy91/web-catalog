package dao;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import com.entities.Arrival;
import com.entities.Brand;
import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.Dao.DevicesDao;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml" })
public class DevicesDaoTest {
	
	
	@Mock
	private DevicesDao devicesDao;
	private Device device;
	
	private static final int ID = 1;
	private static final int ID2 = 2;
	private static final int ID3 = 3;
	private static final String MODEL = "iPhone 6";
	private static final float PRICE = 600;
	private static final int AMOUNTINSTOCK = 5;

	
	@Before
	public void init(){
		
		MockitoAnnotations.initMocks(this);
		Brand brand = mock(Brand.class);
		List<Device> devices = new ArrayList<Device>();
		List<Order_Sale> o_s = new ArrayList<Order_Sale>();
		List<Arrival> arrivals = new ArrayList<Arrival>();
		
		o_s.add(mock(Order_Sale.class));
		o_s.add(mock(Order_Sale.class));
		o_s.add(mock(Order_Sale.class));
		arrivals.add(mock(Arrival.class));
		arrivals.add(mock(Arrival.class));
		
		device = new Device();
		device.setId(ID);
		device.setModel(MODEL);
		device.setBrand(brand);
		device.setAmountInStock(AMOUNTINSTOCK);
		device.setPrice(PRICE);
		
		Device device2 = mock(Device.class);
		Device device3 = mock(Device.class);
		device2.setArrivals(arrivals);
		device3.setOrders_Sales(o_s);
		device2.setId(ID2);
		device3.setId(ID3);
				
		devices.add(device);
		devices.add(device2);
		devices.add(device3);
		
		devicesDao.create(device);
	
		/*when(devicesDao.create(device)).thenReturn(device);
		when(devicesDao.update(device)).thenReturn(device);
		when(devicesDao.find(ID)).thenReturn(device);
		when(devicesDao.initArrivals(ID2)).thenReturn(device2);
		when(devicesDao.initOrders_Sales(ID3)).thenReturn(device3);
		when(devicesDao.getAll(SEARCH)).thenReturn(Arrays.asList(device));
		when(devicesDao.getAll()).thenReturn(devices);*/
	}
	
	
	
	@Test
	public void testFindDevice(){
		
		Device device = devicesDao.find(ID);
		verify(devicesDao).find(ID);
		makeAssertions(device);
	}
	
	@SuppressWarnings("deprecation")
	private void makeAssertions(Device device) {
		
		assertNotNull(device);
		assertNotNull(device.getBrand());
		assertTrue(device.getId() > 0);
		assertEquals(ID, device.getId());
		assertEquals(MODEL,device.getModel());
		assertEquals(PRICE, device.getPrice());
		assertEquals(AMOUNTINSTOCK, device.getAmountInStock());
	}
}
