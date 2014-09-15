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
import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.Dao.DevicesDao;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml",
		"classpath:test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("schema.xml")
public class DevicesDaoTest {

	@Autowired
	private DevicesDao devicesDao;
	private Device device;
	private Device device2;
	private Device device3;

	private static final int ID = 1;
	private static final int ID2 = 2;
	private static final int ID3 = 3;
	private static final int UID = 4;
	private static final String MODEL = "iPhone 5s";
	private static final String UMODEL = "Galaxy S4";
	private static final int PRICE = 100;
	private static final int UPRICE = 90;
	private static final int AMOUNTINSTOCK = 6;
	private static final int UAMOUNTINSTOCK = 5;

	@Test
	public void findByNameTest() {

		device = devicesDao.find(MODEL);
		device2 = devicesDao.find(UMODEL);
		assertNull(device2);
		makeAssertions(device);
	}

	@Test
	public void findDeviceTest() {

		device = devicesDao.find(ID);
		device2 = devicesDao.find(UID);
		assertNull(device2);
		makeAssertions(device);
	}

	@Test
	public void getAllTest() {

		List<Device> devices = devicesDao.getAll();
		assertNotNull(devices);
		assertEquals(3, devices.size());
		assertFetched(devices.get(0));
		makeAssertions(devices.get(0));
	}

	@Test
	public void getAllSearchTest() {
		List<Device> devices = devicesDao.getAll(MODEL);
		List<Device> devices2 = devicesDao.getAll(UMODEL);

		assertEquals(0, devices2.size());
		assertEquals(1, devices.size());
		makeAssertions(devices.get(0));
	}

	@Test
	public void initDeviceTest() {

		device = devicesDao.initDevice(ID);
		makeAssertions(device);
		assertFetched(device);
	}

	@Test
	public void initArrivalsTest() {
		device = devicesDao.initArrivals(ID);
		device2 = devicesDao.initArrivals(ID2);
		device3 = devicesDao.initArrivals(ID3);
		assertNotNull(device2);
		assertNotNull(device3);
		assertEquals(2, device.getArrivals().size());
		assertEquals(1, device2.getArrivals().size());
		assertEquals(0, device3.getArrivals().size());
		Arrival arrival = device2.getArrivals().get(0);
		assertNotNull(arrival);
		makeAssertions(device);
	}

	@Test
	public void initOrders_SalesTest() {

		device = devicesDao.initOrders_Sales(ID);
		device2 = devicesDao.initOrders_Sales(ID2);
		device3 = devicesDao.initOrders_Sales(ID3);
		assertNotNull(device2);
		assertNotNull(device3);
		assertEquals(2, device.getOrders_Sales().size());
		assertEquals(1, device2.getOrders_Sales().size());
		assertEquals(0, device3.getOrders_Sales().size());
		Order_Sale o_s = device2.getOrders_Sales().get(0);
		assertNotNull(o_s);
		makeAssertions(device);
	}

	@Test
	public void createDeviceTest() {

		assertEquals(3, devicesDao.getAll().size());
		device2 = devicesDao.find(ID2);
		device = devicesDao.create(new Device(UID, device2.getBrand(), UMODEL,
				UPRICE, UAMOUNTINSTOCK));
		assertNotNull(device);
		assertEquals(UID, device.getId());
		assertEquals(UMODEL, device.getModel());
		assertEquals(UPRICE, (int) device.getPrice());
		assertEquals(UAMOUNTINSTOCK, device.getAmountInStock());
		assertEquals(4, devicesDao.getAll().size());
	}

	@Test
	public void updateDeviceTest() {

		device2 = devicesDao.find(ID2);
		device = devicesDao.find(ID);
		makeAssertions(device);
		assertFalse(device.getModel().equals(UMODEL));
		assertFalse(device.getAmountInStock() == UAMOUNTINSTOCK);
		assertFalse((int) device.getPrice() == UPRICE);
		device.setBrand(device2.getBrand());
		device.setModel(UMODEL);
		device.setAmountInStock(UAMOUNTINSTOCK);
		device.setPrice(UPRICE);
		device = devicesDao.update(device);
		assertNotNull(device);
		assertEquals(ID, device.getId());
		assertEquals(UMODEL, device.getModel());
		assertEquals(UPRICE, (int) device.getPrice());
		assertEquals(UAMOUNTINSTOCK, device.getAmountInStock());
	}

	@Test
	public void deleteDeviceTest() {

		assertEquals(3, devicesDao.getAll().size());
		device3 = devicesDao.find(ID3);
		assertNotNull(device3);
		devicesDao.delete(ID3);
		device3 = devicesDao.find(ID3);
		assertNull(device3);
		assertEquals(2, devicesDao.getAll().size());
	}

	private void makeAssertions(Device device) {

		assertNotNull(device);
		assertEquals(ID, device.getId());
		assertEquals(MODEL, device.getModel());
		assertEquals(PRICE, (int) device.getPrice());
		assertEquals(AMOUNTINSTOCK, device.getAmountInStock());
	}

	private void assertFetched(Device device) {

		assertNotNull(device.getBrand());
		assertNotNull(device.getBrand().getBrandName());
	}
}
