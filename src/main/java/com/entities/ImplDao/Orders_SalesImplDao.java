package com.entities.ImplDao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import com.entities.Order_Sale;
import com.entities.Dao.Orders_SalesDao;

@Component
public class Orders_SalesImplDao 	extends RootDaoImpl<Order_Sale>
									implements Orders_SalesDao {
	
										
	@SuppressWarnings("unchecked")
	private List<Order_Sale> confCriteria() {

		Criteria criteria = currentSession().createCriteria(Order_Sale.class);
		criteria.createAlias("user", "user");
		criteria.createAlias("user.role", "role");

		criteria.addOrder(Order.asc("role.name")).addOrder(
				Order.asc("user.login"));

		criteria.addOrder(Order.desc("timeOrdered"));

		List<Order_Sale> o_s = criteria.list();
		return o_s;
	}
	
	
	@Override
	public List<Order_Sale> getAll() {
		return confCriteria();
	}
	
	

}
