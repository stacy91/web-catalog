package com.controller.management;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entities.dto.Order_SaleDto;
import com.helpers.Charts;

@Controller
public class ManagementController extends RootController {

	@RequestMapping(value = "")
	public String index(ModelMap model, Principal principal) {

		List<Order_SaleDto> orders = o_sService.getOrders(principal.getName());
		List<Order_SaleDto> available = o_sService.findAvailable(orders);
		List<Order_SaleDto> sales = o_sService.getSales(principal.getName());

		model.addAttribute("orders", o_sService.getFiltered(orders, 1)
				.getItems());
		model.addAttribute("ordersCount", orders.size());
		model.addAttribute("availaCount", available.size());
		model.addAttribute("salesCount", sales.size());
		model.addAttribute("date",
				new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
		return "management";
	}

	// statistics

	@RequestMapping(value = "statistics")
	public String showStatistics(ModelMap model) {

		model.addAttribute("areaChart", Charts.getAreaChart(o_sService));
		model.addAttribute("donutChart",
				Charts.getDonutChart(o_sService, devicesService));
		model.addAttribute("graphChart", Charts.getLineChart(arrivalsService));
		model.addAttribute("barChart", Charts.getBarChart(o_sService));
		return "admin/statistics";
	}

	// statistics
}