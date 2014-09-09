package com.controller.management;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entities.dto.ArrivalDto;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Controller
public class ArrivalsController extends RootController{
	// Arrivals

	@RequestMapping(value = "/arrivals")
	public String showArrivals(ModelMap model, Integer page) {

		FilteredCollection<ArrivalDto> fArrivals = arrivalsService.getFiltered(
				arrivalsService.getAll(), page);
		model.addAttribute("arrivals", fArrivals.getItems());
		FilteredCollectionGenerator.fillPagination(model, fArrivals);
		return "adminArrivals";
	}

	@RequestMapping(value = "/addArrival", method = RequestMethod.GET)
	public String addArrival(ModelMap model, int deviceId, Principal principal,
			Integer page) {

		model.addAttribute("arrival",
				arrivalsService.initArrival(deviceId, principal.getName()));
		model.addAttribute("page", page);
		return "adminArrivals/add";
	}

	@RequestMapping(value = "/addArrival", method = RequestMethod.POST)
	public String addArrival(@ModelAttribute("arrival") @Valid ArrivalDto arrival, BindingResult result, Principal principal,
			String action, Integer page) 
					{

		String redirect = "redirect:/management/devices";
		if (!action.equals("cancel")) {
			if(result.hasErrors())
				return "adminArrivals/add";
			arrivalsService.create(arrival);
		}

		if (page != null) {
			redirect += "?page=" + page;
		}

		return redirect;
	}

	@RequestMapping(value = "/updateArrival")
	public String updateArrival(int id, ModelMap model, Integer page) {

		ArrivalDto arrival = arrivalsService.find(id);
		model.addAttribute("arrival", arrival);
		model.addAttribute("page", page);
		return "adminArrivals/update";
	}

	@RequestMapping(value = "/updateArrival", method = RequestMethod.POST)
	public String updateArrival(@ModelAttribute("arrival") @Valid ArrivalDto arrival,BindingResult result, Principal principal,
			String action, Integer page) 
					{

		String redirect = "redirect:/management/arrivals";
		if (!action.equals("cancel")) {
			if(result.hasErrors())
				return "adminArrivals/update";
			arrival.setUser(usersService.find(principal.getName()));
			arrivalsService.update(arrival);
		}

		if (page != null) {
			redirect += "?page=" + page;
		}

		return redirect;
	}

	@RequestMapping(value = "/deleteArrival", method = RequestMethod.POST)
	public String deleteArrival(int id, Integer page) 
			{

		String redirect = "redirect:/management/arrivals";
		arrivalsService.delete(id);

		if (page != null) {
			redirect += "?page=" + page;
		}

		return redirect;
	}

	// end arrivals
}
