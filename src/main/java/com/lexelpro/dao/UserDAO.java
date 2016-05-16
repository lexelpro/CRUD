/**
 * 
 */
package com.lexelpro.dao;

import java.util.List;

import com.lexelpro.entity.User;

/**
 * @author Lexel PRO
 * @version 1.0
 */
public interface UserDAO {
	public long createUser(User user);
    public User updateUser(User user);
    public void deleteUser(long id);
    public List<User> getAllUsers();
    public User getUser(long id);
	public List<User> getAllUsers(String userName);

    public void createDemoData();
}
