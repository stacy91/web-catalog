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
import com.entities.Order_Sale;
import com.entities.Dao.Orders_SalesDao;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:app-context.xml",
		"classpath:test-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("schema.xml")
@Transactional
public class Orders_SalesDaoTest {
	
	@Autowired
	private Orders_SalesDao order_salesDao;
	private Order_Sale o_s;
	private Order_Sale o_s2;
	private Order_Sale o_s3;
	
	private static final int ID = 1;
	private static final int ID2 = 2;
	private static final int ID3 = 3;
	private static final boolean ISSOLD = false;
	private static final int AMOUNT = 1;
	
	private static final int UID = 4;
	private static final boolean UISSOLD = true;
	private static final int UAMOUNT = 2;
	
	@Test
	public void findTest(){
		
		o_s = order_salesDao.find(ID);
		o_s2 = order_salesDao.find(UID);
		assertNull(o_s2);
		makeAssertions(o_s);
	}
	
	@Test
	public void initOrder_SaleTest(){
		
		o_s = order_salesDao.initOrder_Sale(ID);
		makeAssertions(o_s);
		assertFetched(o_s);
	}
	
	@Test
	public void getAllTest(){
		
		List<Order_Sale> o_ss = order_salesDao.getAll();
		assertNotNull(o_ss);
		assertEquals(3, o_ss.size());
		assertFetched(o_ss.get(0));
	}
	
	@Test
	public void createArrivalTest(){
		
		assertEquals(3, order_salesDao.getAll().size());
		o_s2 = order_salesDao.find(ID2);
		o_s = order_salesDao.create(new Order_Sale(UID,o_s2.getDevice(),o_s2.getUser(),UISSOLD,UAMOUNT,new DateTime().toDate(),new DateTime().toDate()));
		assertNotNull(o_s);

		assertNotNull(o_s.getTimeOrdered());
		assertNotNull(o_s.getTimeSold());
		assertEquals(UID, o_s.getId());
		assertEquals(UISSOLD, o_s.getIsSold());
		assertEquals(UAMOUNT, o_s.getAmount());
		
		assertEquals(4, order_salesDao.getAll().size());
		
	}
	
	@Test
	public void updateArrivalTest(){
		
		o_s2 = order_salesDao.find(ID2);
		o_s = order_salesDao.find(ID);
		makeAssertions(o_s);
		assertFalse(o_s.getAmount() == UAMOUNT);
		assertFalse(o_s.getIsSold() == UISSOLD);
		o_s.setDevice(o_s2.getDevice());
		o_s.setUser(o_s2.getUser());
		o_s.setAmount(UAMOUNT);
		o_s.setIsSold(UISSOLD);
		o_s.setTimeOrdered(new DateTime().toDate());
		o_s.setTimeSold(new DateTime().toDate());
		o_s = order_salesDao.update(o_s);
		
		assertNotNull(o_s);
		assertEquals(ID, o_s.getId());
		assertEquals( o_s.getAmount(), UAMOUNT);
		assertEquals( o_s.getIsSold(), UISSOLD);
	}
	
	@Test
	public void deleteArrivalTest(){
		
		assertEquals(3,order_salesDao.getAll().size());
		o_s3 = order_salesDao.find(ID3);
		assertNotNull(o_s3);
		order_salesDao.delete(ID3);
		o_s3 = order_salesDao.find(ID3);
		assertNull(o_s3);
		assertEquals(2,order_salesDao.getAll().size());
	}
	private void makeAssertions(Order_Sale o_s){
		
		assertNotNull(o_s);
		assertEquals(ID, o_s.getId());
		assertEquals((int) o_s.getAmount(), AMOUNT);
		assertEquals(o_s.getIsSold(),ISSOLD);
		assertNotNull(o_s.getTimeOrdered());
		assertNotNull(o_s.getTimeSold());
	}
	
	private void assertFetched(Order_Sale o_s){
		
		assertNotNull(o_s.getDevice());
		assertNotNull(o_s.getDevice().getBrand());
		assertNotNull(o_s.getDevice().getBrand().getBrandName());
		assertNotNull(o_s.getUser());
		assertNotNull(o_s.getUser().getRole());
		assertNotNull(o_s.getUser().getRole().getName());

	}
}
