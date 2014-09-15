package com.controller.management;

import java.security.Principal;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.entities.dto.UserDto;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Controller
public class UsersController extends RootController {

	// Users

	@RequestMapping(value = "users")
	public String showUsers(ModelMap model, Integer page) {

		FilteredCollection<UserDto> fUsers = usersService.getFiltered(
				usersService.getAll(), page);
		model.addAttribute("users", fUsers.getItems());

		FilteredCollectionGenerator.fillPagination(model, fUsers);
		return "admin/users";
	}

	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public String deleteUser(ModelMap model, Integer page, int id,
			Principal principal) {

		String redirect = "redirect:/management/users";
		try {
			if (page != null)
				redirect += "?page=" + page;
			UserDto authUser = usersService.find(principal.getName());
			if (authUser != null && authUser.getId() != id)
				usersService.delete(id);

		} catch (DataIntegrityViolationException ex) {

		}

		return redirect;
	}

	@RequestMapping(value = "changeRole", method = RequestMethod.POST)
	public String changeRole(ModelMap model, Integer page, int id,
			Principal principal) {

		String redirect = "redirect:/management/users";

		usersService.changeRole(id, principal.getName());
		if (page != null)
			redirect += "?page=" + page;
		return redirect;
	}

	@RequestMapping(value = "user")
	public String showUser(ModelMap model, Principal principal) {

		model.addAttribute("user", usersService.find(principal.getName()));
		return "user";
	}

	// end Users
}
