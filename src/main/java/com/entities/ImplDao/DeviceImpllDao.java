package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.entities.Device;
import com.entities.Dao.DevicesDao;



@Component
public class DeviceImpllDao 	extends RootDaoImpl<Device>
								implements DevicesDao {
	
	@Override
	public Device initArrivals(int id) {
		Device attchDevice = find(id);
		Hibernate.initialize(attchDevice);	
		attchDevice.getArrivals().size();	
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
	public Device find(String model) {
		Criteria criteria = currentSession().createCriteria(Device.class);
		List<Device> devices = criteria.add(Restrictions.eq("model", model)).list();
		if(devices.size() > 0){
			return devices.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAll(String search) {
		
		Criteria criteria = currentSession().createCriteria(Device.class);

		criteria.createAlias("brand", "brand");

		if(search != null && !search.isEmpty()){
			criteria.add(Restrictions.disjunction().add(Restrictions.eq("brand.brandName",search)).
					add(Restrictions.eq("model",search)));
			}
		criteria.addOrder(Order.asc("brand.brandName"));
		criteria.setFetchMode("brand", FetchMode.JOIN);
		List<Device> devices = criteria.list();

	    return devices;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAll() {
		
		Criteria criteria = currentSession().createCriteria(Device.class);
		criteria.createAlias("brand", "brand");
		criteria.addOrder(Order.asc("brand.brandName"));
		criteria.setFetchMode("brand", FetchMode.JOIN);
		List<Device> devices = criteria.list();

	    return devices;
	}


	@Override
	public Device initDevice(int id) {
		Device device = find(id);
		device.getBrand().getBrandName();
		return device;
	}

}
