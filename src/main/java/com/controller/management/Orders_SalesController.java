package com.controller.management;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.dto.Order_SaleDto;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;
import com.helpers.Roles;

@Controller
public class Orders_SalesController extends RootController {

	// orders_sales

	@RequestMapping(value = "/allOS")
	public String showOS(ModelMap model, Integer page, String show) {

		List<Order_SaleDto> o_s = o_sService.getAll(show);
		FilteredCollection<Order_SaleDto> fO_S = o_sService.getFiltered(o_s,
				page);
		model.addAttribute("o_s", fO_S.getItems());
		model.addAttribute("show", show);
		model.addAttribute("roles", Roles.values());
		FilteredCollectionGenerator.fillPagination(model, fO_S);

		return "admin/AllOS";
	}

	@RequestMapping(value = "/deleteOS", method = RequestMethod.POST)
	public String deleteOS(int id, Integer page, String show) {
		String redirect = "redirect:/management/allOS?";
		o_sService.deleteOS(id);
		if (show != null)
			redirect += "show=" + show + "&";
		if (page != null)
			redirect += "page=" + page + "&";
		return redirect;
	}

	@RequestMapping(value = "/orders")
	public String showOrders(ModelMap model, Principal principal, Integer page,
			String show) {

		List<Order_SaleDto> o_s = o_sService.getOrders(principal.getName());
		if (show != null && show.equals("available")) {
			o_s = o_sService.findAvailable(o_s);
		} else {
			show = "all";
		}
		FilteredCollection<Order_SaleDto> fO_S = o_sService.getFiltered(o_s,
				page);
		model.addAttribute("show", show);
		model.addAttribute("orders", fO_S.getItems());
		model.addAttribute("page", page);

		FilteredCollectionGenerator.fillPagination(model, fO_S);

		return "orders";
	}

	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	public String deleteOrder(int id, Integer page, String show) {

		String redirect = "redirect:/management/orders?";
		o_sService.delete(id);

		if (show != null) {
			redirect += "show=" + show + "&";
		}
		if (page != null)
			redirect += "page=" + page;
		return redirect;
	}

	/*@RequestMapping(value = "/order")
	public String order(ModelMap model, int id, String error, String show,
			Integer page) {

		Order_SaleDto o_s = o_sService.find(id);
		model.addAttribute("order", o_s);
		model.addAttribute("page", page);
		model.addAttribute("show", show);

		if (error != null && error.equals("true")) {

			model.addAttribute("message", "Management.order.buy.errorMsg");
		}

		return "order";
	}*/

	@RequestMapping(value = "/buy")
	public String buy(ModelMap model, @RequestParam("id") int id, String show,
			 Integer page) {

		if (!o_sService.buy(id)) {
			return "notificateOrderError";
		}
		
		String link = "orders?";
		if (show != null) {
			link += "show=" + show + "&";
		}
		if (page != null)
			link += "page=" + page;
			
		model.addAttribute("link", link);
		return "notificateOrderOk";
	}

	@RequestMapping(value = "/sales")
	public String showSales(ModelMap model, Principal principal, Integer page) {

		FilteredCollection<Order_SaleDto> fO_S = o_sService.getFiltered(
				o_sService.getSales(principal.getName()), page);
		model.addAttribute("sales", fO_S.getItems());

		FilteredCollectionGenerator.fillPagination(model, fO_S);

		return "sales";
	}

	// end orders_sales
}
