package com.entities.services;

import com.entities.dto.ArrivalDto;

public interface ArrivalsService extends RootService<ArrivalDto>{
	
	public final int PAGE_SIZE = 10;
	
	public ArrivalDto initArrival(int deviceId, String login);
	
}
