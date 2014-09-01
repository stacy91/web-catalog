package com.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.entities.dto.ArrivalDto;
import com.entities.dto.DeviceDto;
import com.entities.dto.Order_SaleDto;
import com.entities.servicesImpl.ArrivalsService;
import com.entities.servicesImpl.DevicesService;
import com.entities.servicesImpl.Orders_SalesService;

public class Charts {
	
	
	public static HashMap<String, AreaChartPoint> getAreaChart(Orders_SalesService o_sService){
		
		HashMap<String, AreaChartPoint> hmOs = new LinkedHashMap<String, AreaChartPoint>();	
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		List<Order_SaleDto> o_s = o_sService.getAllOS();
		String time = null;
		AreaChartPoint point = null;

		
		for (Order_SaleDto item : o_s) {
			if (hmOs.size() < 10) {
				if (item.getIsSold() && item.getTimeSold() != null) {
					time = df.format(item.getTimeSold());
					if (hmOs.containsKey(time)) {
						point = hmOs.get(time);
						point.setSales(point.getSales() + item.getAmount());
						hmOs.put((time), point);
					} else
						hmOs.put(time, new AreaChartPoint(0, item.getAmount()));

				}
				if (item.getTimeOrdered() != null) {
					time = df.format(item.getTimeOrdered());
					if (hmOs.containsKey(time)) {
						point = hmOs.get(time);
						point.setOrders(point.getOrders() + item.getAmount());
						hmOs.put(time, point);
					} else
						hmOs.put(time, new AreaChartPoint(item.getAmount(), 0));
				}
			}
		}
		
		
		return hmOs;
	}
	
	public static HashMap<String, Integer> getDonutChart(Orders_SalesService o_sService, DevicesService devicesService){
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		int tOrders = 0;
		int tSales = 0;
		int tDevices = 0;
		
		for(Order_SaleDto o_s : o_sService.getAllOS()){
			tOrders += o_s.getAmount();
			if(o_s.getIsSold())
				tSales += o_s.getAmount();
		}
		
		for(DeviceDto device : devicesService.getDevices()){
			tDevices += device.getAmountInStock();
		}
		hm.put("Orders", tOrders);
		hm.put("Purchases", tSales);
		hm.put("Devices", tDevices);
		
		return hm;
	}
	
	public static HashMap<String, Integer> getLineChart(ArrivalsService arrivalsService){
		
		HashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String time;
		for(ArrivalDto item : arrivalsService.getArrivals()){
			
			time = df.format(item.getTime());
			if(hm.size() < 30){
				if(hm.containsKey(time)){
					hm.put(time, hm.get(time) + item.getAmount());
				}
				else{
					hm.put(time,item.getAmount());
				}
					
			}
		}
		
		return hm;
		
	}
	
	@SuppressWarnings("serial")
	public static HashMap<String, Integer> getBarChart(Orders_SalesService o_sService){
		
		HashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
		HashMap<String, Integer> hmRet = new LinkedHashMap<String, Integer>();

		String name;
		
		for(Order_SaleDto item : o_sService.getAllSales()){
			name = item.getDevice().getBrand().getBrandName() + " " + item.getDevice().getModel();
			if(hm.containsKey(name)){
				hm.put(name, hm.get(name) + item.getAmount());
			}
			else{
				hm.put(name,item.getAmount());
			}
		}
		
		List<Entry<String,Integer>> sorted = new ArrayList<Map.Entry<String,Integer>>(hm.entrySet()) {
		};
		
		Collections.sort(sorted,new Comparator<Entry<String,Integer>>(){
			@Override
			  public int compare(Entry<String, Integer> x, Entry<String, Integer> y) {
			    return x.getValue() > y.getValue() ? -1 : 1;
			  }
		});
		
		
		for(Entry<String, Integer> item : sorted.subList(0, Math.min(5, sorted.size())))
			hmRet.put(item.getKey(), item.getValue());
		return hmRet;
	}
	

}
