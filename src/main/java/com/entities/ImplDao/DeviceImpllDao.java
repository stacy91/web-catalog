package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Arrival;
import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.Dao.DevicesDao;



@Repository
@Transactional
public class DeviceImpllDao 	extends RootModel
								implements DevicesDao {
	

	
	
	@Autowired
	public DeviceImpllDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Device device){
		currentSession().save(device);
	}

	@Override
	public void update(Device newDevice) {
		Device deviceToUpdate = findById(newDevice.getId());
		deviceToUpdate.setBrand(newDevice.getBrand());
		deviceToUpdate.setModel(newDevice.getModel());
		deviceToUpdate.setPrice(newDevice.getPrice());
		deviceToUpdate.setAmountInStock(newDevice.getAmountInStock());
		currentSession().update(deviceToUpdate);
	}

	@Override
	public void delete(int id) {
		this.delete(this.findById(id));
	}

	@Override
	public void delete(Device device) {
		currentSession().delete(device);
	}

	@Override
	public Device findById(int id) {
		Device device = (Device)currentSession().get(Device.class,id);	
		return device;
	}

	
	@Override
	public Device initBrand(int id) {
		Device attchDevice = findById(id);
		Hibernate.initialize(attchDevice.getBrand());
		return attchDevice;
	}
	
	
	@Override
	public Device initArrivals(int id) {
		Device attchDevice = findById(id);

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
		Device attchDevice = findById(id);
		attchDevice.getOrders_Sales().size();
		for(Order_Sale item : attchDevice.getOrders_Sales())
		{
			Hibernate.initialize(item.getUser());
			Hibernate.initialize(item.getUser().getRole());
		}
		return attchDevice;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAllDeviceValues(int brandId, String search) {
		
		Criteria criteria = currentSession().createCriteria(Device.class);

		criteria.createAlias("brand", "brand");
		criteria.setFetchMode("brand", FetchMode.JOIN);
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
	public List<Device> getAllDeviceValues() {
		
		Criteria criteria = currentSession().createCriteria(Device.class);

		criteria.createAlias("brand", "brand");
		criteria.setFetchMode("brand", FetchMode.JOIN);
		criteria.addOrder(Order.asc("brand.brandName"));
		List<Device> devices = criteria.list();

	    return devices;
	}

}
