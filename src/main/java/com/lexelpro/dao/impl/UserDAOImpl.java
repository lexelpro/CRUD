/**
 * 
 */
package com.lexelpro.dao.impl;

import com.lexelpro.dao.UserDAO;
import com.lexelpro.entity.User;
import com.lexelpro.util.HibernateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Lexel PRO
 * @version 1.0
 */

@Repository
public class UserDAOImpl implements UserDAO {
    
	public UserDAOImpl() {
    	System.out.println("UserDAOImpl");
    }
	
	@Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public long createUser(User user) {
        return (Long) hibernateUtil.create(user);
    }
    
    @Override
    public User updateUser(User user) {
        return hibernateUtil.update(user);
    }
    
    @Override
    public void deleteUser(long id) {
        User user = new User();
        user.setId(id);
        hibernateUtil.delete(user);
    }
    
    @Override
    public List<User> getAllUsers() {
        return hibernateUtil.fetchAll(User.class);
    }
    
    @Override
    public User getUser(long id) {
        return hibernateUtil.fetchById(id, User.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers(String userName) {
		String query = "SELECT e.* FROM Users e WHERE e.name like '%"+ userName +"%'";
		List<Object[]> userObjects = hibernateUtil.fetchAll(query);
		List<User> users = new ArrayList<User>();
		for(Object[] userObject: userObjects) {
			User user = new User();
			long id = ((BigInteger) userObject[0]).longValue();			
			int age = (int) userObject[1];
			String name = (String) userObject[2];
            boolean isAdmin = (boolean)userObject[3];
            Date date = (Date) userObject[4];
            user.setId(id);
			user.setName(name);
			user.setAge(age);
            user.setAdmin(isAdmin);
            user.setCreatedDate(date);
			users.add(user);
		}
		System.out.println(users);
		return users;
	}

    @Override
    public void createDemoData() {

        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setAge(18 + (int)(Math.random() * ((60 - 18) + 1))); //genarate random age from 18 - 60
            user.setAdmin(new Random().nextBoolean());
            hibernateUtil.create(user);
        }
    }
}