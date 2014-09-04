package com.controller.management;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.entities.dto.DeviceDto;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Controller
public class DevicesController extends RootController {

	// Devices

	@RequestMapping(value = "/devices")
	public String showDevices(ModelMap model, Integer page) {

		FilteredCollection<DeviceDto> fDevices = devicesService.getFiltered(
				devicesService.getAll(), page);
		List<DeviceDto> devices = fDevices.getItems();
		model.addAttribute("devices", devices);
		model.addAttribute("brands", brandsService.getAll());

		FilteredCollectionGenerator.fillPagination(model, fDevices);

		return "adminDevices";
	}

	@RequestMapping(value = "/addDevice")
	public String addDevice(ModelMap model, Integer page) {

		model.addAttribute("device", new DeviceDto());
		model.addAttribute("brands", brandsService.getAll());
		model.addAttribute("page", page);

		return "adminDevices/add";
	}

	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	public String addDevice(@Valid DeviceDto device, MultipartFile image,
			String action, BindingResult result, Integer page) {

		String redirect = "redirect:/management/devices";

		if (!action.equals("cancel")) {
			if (result.hasErrors())
				return "adminBrands/add";
			devicesService.create(device, image);
		}

		if (page != null)
			redirect += "?page=" + page;

		return redirect;
	}

	@RequestMapping(value = "/updateDevice")
	public String updateDevice(int id, ModelMap model, Integer page) {

		DeviceDto device = devicesService.find(id);
		model.addAttribute("device", device);
		model.addAttribute("brands", brandsService.getAll());
		model.addAttribute("page", page);

		return "adminDevices/update";
	}

	@RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
	public String updateDevice(@Valid DeviceDto device, MultipartFile image,
			String action, BindingResult result, Integer page) {

		String redirect = "redirect:/management/devices";
		if (!action.equals("cancel")) {
			if (result.hasErrors())
				return "adminBrands/add";
			devicesService.update(device, image);
		}

		if (page != null) {
			redirect += "?page=" + page;
		}

		return redirect;
	}

	@RequestMapping(value = "/deleteDevice", method = RequestMethod.POST)
	public String deleteDevice(int id, Integer page) {

		String redirect = "redirect:/management/devices";
		devicesService.delete(id);

		if (page != null) {
			redirect += "?page=" + page;
		}
		return redirect;
	}
	// end devices
}
