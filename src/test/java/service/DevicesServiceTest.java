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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import com.entities.Brand;
import com.entities.Device;
import com.entities.Dao.BrandsDao;
import com.entities.Dao.DevicesDao;
import com.entities.dto.DeviceDto;
import com.entities.dto.DtoToEntity;
import com.entities.servicesImpl.DevicesServiceImpl;
import com.helpers.DeviceHelper;
import com.helpers.FilteredCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml" })
public class DevicesServiceTest {

	private static final int ID = 1;
	private static final int ID100 = 100;
	private static final String MODEL = "iPhone 5S";
	private static final int PRICE = 100;
	private static final int AMOUNTINSTOCK = 6;
	private static final int UID = 2;
	private static final String UMODEL = "Galaxy S5";
	private static final int UPRICE = 90;
	private static final int UAMOUNTINSTOCK = 5;
	private static final int BRANDID = 1; // Apple
	private static final String WRONGMODEL = "wrong model";

	private MultipartFile image;
	private Device device;
	private Device uDevice;
	private Device wrongDevice;
	private DeviceDto deviceDto;
	private DeviceDto uDeviceDto;
	private DeviceDto wrongDeviceDto;
	private List<Device> devices;
	private List<DeviceDto> devicesDto;

	@InjectMocks
	private DevicesServiceImpl devicesService;
	@Mock
	private DevicesDao devicesDao;
	@Mock
	private BrandsDao brandsDao;
	@Mock
	private DeviceHelper deviceHelper;
	@Mock
	private DtoToEntity toEntity;

	@Before
	@SuppressWarnings("serial")
	public void init() {

		MockitoAnnotations.initMocks(this);
		Brand brand = new Brand();
		
		image = mock(MultipartFile.class);
		device = new Device(ID, null, MODEL, PRICE, AMOUNTINSTOCK);
		deviceDto = new DeviceDto(device);
		wrongDevice = new Device() {
			{
				setModel(WRONGMODEL);
			}
		};
		uDevice = new Device(UID, null, UMODEL, UPRICE, UAMOUNTINSTOCK);
		deviceDto = new DeviceDto(device);
		uDeviceDto = new DeviceDto(uDevice);
		wrongDeviceDto = new DeviceDto(wrongDevice);
		devices = new ArrayList<Device>();
		devicesDto = new ArrayList<DeviceDto>();
		List<Device> oneDeviceList = new ArrayList<Device>() {
			{
				add(device);
			}
		};
		devices.add(device);
		devices.add(uDevice);
		devicesDto.add(deviceDto);
		devicesDto.add(uDeviceDto);
		brand.setDevices(oneDeviceList);

		when(devicesDao.create(device)).thenReturn(device);
		when(devicesDao.create(uDevice)).thenReturn(uDevice);
		when(devicesDao.update(device)).thenReturn(device);
		when(devicesDao.update(uDevice)).thenReturn(uDevice);

		when(toEntity.convert(deviceDto)).thenReturn(device);
		when(toEntity.convert(uDeviceDto)).thenReturn(uDevice);

		when(image.isEmpty()).thenReturn(false);
		when(deviceHelper.validateImage(image)).thenReturn(true);

		when(devicesDao.find(WRONGMODEL)).thenReturn(device);
		when(devicesDao.initDevice(ID)).thenReturn(device);
		when(devicesDao.initDevice(ID100)).thenReturn(null);
		when(devicesDao.getAll()).thenReturn(devices);
		when(devicesDao.getAll(MODEL)).thenReturn(oneDeviceList);
		when(devicesDao.getAll(WRONGMODEL)).thenReturn(new ArrayList<Device>());

		when(brandsDao.initDevices(BRANDID)).thenReturn(brand);
	}

	@Test
	public void findTest() {

		deviceDto = devicesService.find(ID);
		verify(devicesDao).initDevice(ID);
		uDeviceDto = devicesService.find(ID100);
		verify(devicesDao).initDevice(ID100);
		assertNull(uDeviceDto);
		makeAssertions(deviceDto);
	}

	@Test
	public void getAllTest() {

		devicesDto = devicesService.getAll();
		verify(devicesDao).getAll();
		assertEquals(devicesDto.size(), 2);
		makeAssertions(devicesDto.get(0));
	}

	@Test
	public void getAllFilteredTest() {

		devicesDto = devicesService.getAll(null, MODEL); // filter by model
		verify(devicesDao).getAll(MODEL);
		verify(brandsDao, never()).initDevices(BRANDID);
		assertNotNull(devicesDto);
		assertEquals(1, devicesDto.size());
		makeAssertions(devicesDto.get(0));

		devicesDto = devicesService.getAll(BRANDID, null); // filter by brand
		verify(devicesDao, never()).getAll(null);
		verify(brandsDao).initDevices(BRANDID);
		assertNotNull(devicesDto);
		assertEquals(1, devicesDto.size());
		makeAssertions(devicesDto.get(0));

		devicesDto = devicesService.getAll(null, WRONGMODEL);
		assertNotNull(devicesDto);
		assertEquals(0, devicesDto.size());
		verify(devicesDao).getAll(WRONGMODEL);
	}

	@Test
	public void getFilteredTest() {

		FilteredCollection<DeviceDto> fColl = devicesService.getFiltered(
				devicesDto, 1);
		assertNotNull(fColl);
		assertEquals(1, fColl.getBegin());
		assertEquals(1, fColl.getEnd());
		assertEquals(1, fColl.getTotalPages());
		assertEquals(1, fColl.getCurrentPage());
		assertEquals(2, fColl.getItems().size());
	}

	@Test
	public void createTest() {

		deviceDto = devicesService.create(deviceDto, image);
		uDeviceDto = devicesService.create(uDeviceDto, null);
		wrongDeviceDto = devicesService.create(wrongDeviceDto,null);

		verify(devicesDao).find(MODEL);
		verify(devicesDao).create(device);
		verify(deviceHelper).validateImage(image);
		verify(image).isEmpty();
		verify(deviceHelper).saveImage(ID, image);

		verify(devicesDao).find(UMODEL);
		verify(devicesDao).create(uDevice);
		verify(deviceHelper, never()).validateImage(null);
		verify(deviceHelper, never()).saveImage(UID, null);

		verify(devicesDao).find(WRONGMODEL);
		verify(devicesDao, never()).create(wrongDevice);
		verify(deviceHelper, never()).validateImage(null);
		verify(deviceHelper, never()).saveImage(UID, null);

		assertEquals(UID, uDeviceDto.getId());
		assertEquals(UMODEL, uDeviceDto.getModel());
		assertEquals(UPRICE, (int) uDeviceDto.getPrice());
		assertEquals(UAMOUNTINSTOCK, uDeviceDto.getAmountInStock());
		assertNotNull(uDeviceDto);
		assertNull(wrongDeviceDto);
		makeAssertions(deviceDto);
	}

	@Test
	public void updateTest() {

		deviceDto = devicesService.update(deviceDto, image);
		uDeviceDto = devicesService.update(uDeviceDto, null);
		wrongDeviceDto = devicesService.update(wrongDeviceDto,null);

		verify(devicesDao).find(MODEL);
		verify(devicesDao).update(device);
		verify(deviceHelper).validateImage(image);
		verify(image).isEmpty();
		verify(deviceHelper).saveImage(ID, image);

		verify(devicesDao).find(UMODEL);
		verify(devicesDao).update(uDevice);
		verify(deviceHelper, never()).validateImage(null);
		verify(deviceHelper, never()).saveImage(UID, null);

		verify(devicesDao).find(WRONGMODEL);
		verify(devicesDao, never()).update(wrongDevice);
		verify(deviceHelper, never()).validateImage(null);
		verify(deviceHelper, never()).saveImage(UID, null);

		assertEquals(UID, uDeviceDto.getId());
		assertEquals(UMODEL, uDeviceDto.getModel());
		assertEquals(UPRICE, (int) uDeviceDto.getPrice());
		assertEquals(UAMOUNTINSTOCK, uDeviceDto.getAmountInStock());
		assertNotNull(uDeviceDto);
		assertNull(wrongDeviceDto);
		makeAssertions(deviceDto);
	}

	@Test
	public void deleteTest() {

		devicesService.delete(ID);
		verify(devicesDao).delete(ID);
	}


	private void makeAssertions(DeviceDto deviceDto) {

		assertNotNull(deviceDto);
		assertEquals(ID, deviceDto.getId());
		assertEquals(MODEL, deviceDto.getModel());
		assertEquals(PRICE, (int) deviceDto.getPrice());
		assertEquals(AMOUNTINSTOCK, deviceDto.getAmountInStock());
	}
}
