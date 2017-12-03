package com.pelotero.mp.service;

import com.pelotero.mp.bean.User;
import com.pelotero.mp.generic.GenericService;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	
	User findByEmail(String email);

	boolean isAdmin(String email);
	
}
