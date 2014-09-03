package com.helpers.formatters;

import java.util.Locale;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import com.entities.Dao.UsersDao;
import com.entities.dto.UserDto;


@Component
public class UserFormatter implements Formatter<UserDto> {
	
	@Autowired
	UsersDao userDao;
	
    @Override
    public String print(UserDto user, Locale locale) {
    	int userId = user.getId();
        return Integer.toString(userId);
    }

    @Override
    public UserDto parse(String id, Locale locale) throws ParseException {

    	int userId = Integer.parseInt(id);
    	UserDto user = new UserDto(userDao.find(userId));
        return user;
    } 


}
