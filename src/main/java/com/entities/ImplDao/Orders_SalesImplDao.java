package com.entities.ImplDao;

import java.util.List;
import org.joda.time.DateTime;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.entities.Order_Sale;
import com.entities.Dao.Orders_SalesDao;

@Component
@Transactional
public class Orders_SalesImplDao 	extends RootDaoImpl<Order_Sale>
									implements Orders_SalesDao {
	
										
	@SuppressWarnings("unchecked")
	private List<Order_Sale> confCriteria(Boolean isSold, String time) {

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
	public Order_Sale update(Order_Sale o_s) {
		Order_Sale attchO_S = find(o_s.getId());
		attchO_S.setIsSold(o_s.getIsSold());
		
		if(attchO_S.getIsSold())
			attchO_S.setTimeSold(new DateTime().toDate());
		currentSession().update(attchO_S);
		
		return attchO_S;
	}

	@Override
	public void delete(int id) {
		currentSession().delete(find(id));
	}

	
	@Override
	public List<Order_Sale> getAll() {
		return confCriteria(null,null);
	}
	
	

}
