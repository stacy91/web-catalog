package com.controller.management;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.dto.BrandDto;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Controller
public class BrandsController extends RootController {

	// Brands

	@RequestMapping(value = "/brands")
	public String showBrands(ModelMap model, Integer page) {

		FilteredCollection<BrandDto> fBrands = brandsService.getFiltered(
				brandsService.getAll(), page);
		model.addAttribute("brands", fBrands.getItems());

		FilteredCollectionGenerator.fillPagination(model, fBrands);

		return "adminBrands";
	}

	@RequestMapping(value = "/addBrand", method = RequestMethod.GET)
	public String addBrand(ModelMap model, Integer page) {

		model.addAttribute("brand", new BrandDto());
		model.addAttribute("page", page);

		return "adminBrands/add";
	}

	@RequestMapping(value = "/addBrand", method = RequestMethod.POST)
	public String addBrand(@RequestParam String action, @ModelAttribute("brand") @Valid BrandDto brand,
			BindingResult result, Integer page) 
					{
		
		String redirect = "redirect:/management/brands";
		if (!action.equals("cancel")) {
			if (result.hasErrors()){

				return "adminBrands/add";
			}
			brandsService.create(brand);
		}

		if (page != null) {
			redirect += "?page=" + page;
		}
		return redirect;
	}

	@RequestMapping(value = "/updateBrand")
	public String updateBrand(int id, ModelMap model, Integer page) {
		BrandDto brandToUpdate = brandsService.find(id);
		if (brandToUpdate == null)
			return "redirect:/management/brands";
		model.addAttribute("brand", brandToUpdate);

		return "adminBrands/update";
	}

	@RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
	public String updateBrand(@RequestParam String action,
			@ModelAttribute("brand") @Valid BrandDto brand, BindingResult result, Integer page) 
					{

		String redirect = "redirect:/management/brands";
		if (!action.equals("cancel")) {
			if (result.hasErrors())
				return "adminBrands/add";
			brandsService.update(brand);
		}

		if (page != null) {
			redirect += "?page=" + page;
		}

		return redirect;
	}

	@RequestMapping(value = "/deleteBrand", method = RequestMethod.POST)
	public String deleteBrand(int id, Integer page) 
			{
		String redirect = "redirect:/management/brands";
		brandsService.delete(id);

		if (page != null) {
			redirect += "?page=" + page;
		}

		return redirect;
	}

	// end Brands
}
