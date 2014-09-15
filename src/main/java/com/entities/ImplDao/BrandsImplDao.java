package com.entities.ImplDao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import com.entities.Brand;
import com.entities.Dao.BrandsDao;


@Component
public class BrandsImplDao 	extends RootDaoImpl<Brand>
							implements BrandsDao {


	@Override
	public Brand initDevices(int id) {
		Brand br = find(id);
		br.getDevices().size();
		return br;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Brand find(String brandName) {
		Criteria criteria = currentSession().createCriteria(Brand.class);
		List<Brand> brands = criteria.add(Restrictions.eq("brandName", brandName)).list();
		if(brands.size() > 0){
			return brands.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<Brand> getAll(){	
		return currentSession().createCriteria(Brand.class)
				.addOrder(Order.asc("brandName")).list();
	}
	
}
