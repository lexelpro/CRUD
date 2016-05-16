
package com.lexelpro.service;
import com.lexelpro.entity.User;

import java.util.List;

/**
 * @author Lexel PRO
 * @version 1.0
 */
public interface UserService {
	public long createUser(User user);
    public User updateUser(User user);
    public void deleteUser(long id);
    public List<User> getAllUsers();
    public User getUser(long id);
	public List<User> getAllUsers(String userName);
    public void createDemoData();
}
