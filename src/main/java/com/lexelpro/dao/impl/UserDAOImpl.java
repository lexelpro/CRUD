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
import java.util.ArrayList;
import java.util.List;

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
			float salary = (float) userObject[3];
			user.setId(id);
			user.setName(name);
			user.setAge(age);
			user.setSalary(salary);
			users.add(user);
		}
		System.out.println(users);
		return users;
	}
}