package com.helpers;

import java.util.List;

import com.entities.Device;

public class FilteredDevices {
	
	private List<Device> devices;
	private int totalPages;
	private int begin;
	private int end;
	private int currentPage;
	
	public FilteredDevices(List<Device> devices,int totalPages, int begin, int end, int currentPage){
		this.devices = devices;
		this.totalPages = totalPages;
		this.begin = begin;
		this.end = end;
		this.currentPage = currentPage;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getCurrentPage() {
		return currentPage;
	}

}
