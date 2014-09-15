package dao;


import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import com.entities.Arrival;
import com.entities.Dao.ArrivalsDao;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml",
		"classpath:test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("schema.xml")
@Transactional
public class ArrivalsDaoTest {
	
	@Autowired
	private ArrivalsDao arrivalsDao;
	private Arrival arrival;
	private Arrival arrival2;
	private Arrival arrival3;
	
	private static final int ID = 1;
	private static final int ID2 = 2;
	private static final int ID3 = 3;
	private static final int PRICE = 90;
	private static final int AMOUNT = 6;
	
	private static final int UID = 4;
	private static final int UPRICE = 70;
	private static final int UAMOUNT = 5;
	
	@Test
	public void findTest(){
		
		arrival = arrivalsDao.find(ID);
		arrival2 = arrivalsDao.find(UID);
		assertNull(arrival2);
		makeAssertions(arrival);
	}
	
	@Test
	public void initArrivalTest(){
		
		arrival = arrivalsDao.initArrival(ID);
		makeAssertions(arrival);
		assertFetched(arrival);
	}
	
	@Test
	public void getAllTest(){
		
		List<Arrival> arrivals = arrivalsDao.getAll();
		assertNotNull(arrivals);
		assertEquals(3, arrivals.size());
		makeAssertions(arrivals.get(0));
		assertFetched(arrivals.get(0));
	}
	
	@Test
	public void createArrivalTest(){
		
		assertEquals(3, arrivalsDao.getAll().size());
		
		arrival2 = arrivalsDao.find(ID2);
		arrival = arrivalsDao.create(new Arrival(4,arrival2.getDevice(),arrival2.getUser(),UAMOUNT,UPRICE,new DateTime().toDate()));
		assertNotNull(arrival);
		assertNotNull(arrival.getTime());
		assertEquals(UID, arrival.getId());
		assertEquals(UAMOUNT, arrival.getAmount());
		assertEquals(UPRICE, (int) arrival.getPrice());
		
		assertEquals(4, arrivalsDao.getAll().size());
		
	}
	
	@Test
	public void updateArrivalTest(){
		
		arrival2 = arrivalsDao.find(ID2);
		arrival = arrivalsDao.find(ID);
		makeAssertions(arrival);
		assertFalse(arrival.getAmount() == UAMOUNT);
		assertFalse(arrival.getPrice() == UPRICE);
		arrival.setAmount(UAMOUNT);
		arrival.setPrice(UPRICE);
		arrival.setDevice(arrival2.getDevice());
		arrival.setUser(arrival2.getUser());
		arrival.setTime(new DateTime().toDate());
		arrival = arrivalsDao.update(arrival);
		assertNotNull(arrival);
		assertNotNull(arrival.getTime());
		assertEquals((int) arrival.getPrice(), UPRICE);
		assertEquals(arrival.getAmount(),UAMOUNT);
	}
	
	@Test
	public void deleteArrivalTest(){

		assertEquals(3,arrivalsDao.getAll().size());
		arrival3 = arrivalsDao.find(ID3);
		assertNotNull(arrival3);
		arrivalsDao.delete(ID3);
		arrival3 = arrivalsDao.find(ID3);
		assertNull(arrival3);
		assertEquals(2,arrivalsDao.getAll().size());
	}
	
	private void makeAssertions(Arrival arrival){
		
		assertNotNull(arrival);
		assertNotNull(arrival.getTime());
		assertEquals(ID, arrival.getId());
		assertEquals((int) arrival.getPrice(), PRICE);
		assertEquals(arrival.getAmount(),AMOUNT);
	}
	
	private void assertFetched(Arrival arrival){
		
		assertNotNull(arrival.getUser());
		assertNotNull(arrival.getUser().getRole());
		assertNotNull(arrival.getUser().getRole().getName());
		assertNotNull(arrival.getDevice());
		assertNotNull(arrival.getDevice().getBrand());
		assertNotNull(arrival.getDevice().getBrand().getBrandName());
	}
}
