package com.entities.ImplDao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.entities.Arrival;
import com.entities.Device;
import com.entities.Dao.DevicesDao;



@Component
@Transactional
public class DeviceImpllDao 	extends RootDaoImpl<Device>
								implements DevicesDao {
	
	@Override
	public Device initArrivals(int id) {
		Device attchDevice = find(id);

		attchDevice.getArrivals().size();
		for(Arrival item : attchDevice.getArrivals())
		{
			Hibernate.initialize(item.getUser());
			Hibernate.initialize(item.getUser().getRole());
		}	
		return attchDevice;
	}
	
	
	@Override
	public Device initOrders_Sales(int id) {
		Device attchDevice = find(id);
		attchDevice.getOrders_Sales().size();
		return attchDevice;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAll(int brandId, String search) {
		
		Criteria criteria = currentSession().createCriteria(Device.class);

		criteria.createAlias("brand", "brand");
		if(brandId != 0){
			criteria.add(Restrictions.eq("brand.id", brandId));
		}
		else if(search != null && !search.isEmpty()){
			criteria.add(Restrictions.disjunction().add(Restrictions.eq("brand.brandName",search)).
					add(Restrictions.eq("model",search)));
			}
		criteria.addOrder(Order.asc("brand.brandName"));
		List<Device> devices = criteria.list();

	    return devices;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAll() {
		
		Criteria criteria = currentSession().createCriteria(Device.class);
		criteria.createAlias("brand", "brand");
		criteria.addOrder(Order.asc("brand.brandName"));
		List<Device> devices = criteria.list();

	    return devices;
	}

}
