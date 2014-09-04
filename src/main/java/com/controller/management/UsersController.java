package com.controller.management;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		UserDto authUser = usersService.find(principal.getName());
		if (authUser.getId() != id)
			usersService.delete(id);
		if (page != null)
			redirect += "?page=" + page;
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

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String updateUser(ModelMap model, UserDto user, String oldPassword,
			String newPassword, String repeatPassword, String action) {

		UserDto updatedUser = usersService.validate(user, oldPassword,
				newPassword, repeatPassword);
		if (updatedUser != null)
			usersService.update(updatedUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				updatedUser.getLogin(), updatedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/management";
	}
	// end Users
}
