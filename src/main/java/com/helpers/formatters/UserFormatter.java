package com.helpers.formatters;

import java.util.Locale;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.entities.User;
import com.entities.Dao.UsersDao;


@Component
public class UserFormatter implements Formatter<User> {
	
	@Autowired
	UsersDao userDao;
	
    @Override
    public String print(User user, Locale locale) {
    	int userId = user.getId();
        return Integer.toString(userId);
    }

    @Override
    public User parse(String id, Locale locale) throws ParseException {

        // IMPORTANT: This approach works only if your equals() method doesn't compare fields
        // beyond the ID. If it does, then you'll need those fields set too. Consider simply
        // loading the entity from the database.
    	int userId = Integer.parseInt(id);
    	User user = userDao.initRole(userId);
        return user;
    } 


}
