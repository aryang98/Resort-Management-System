package com.resort.service;

import com.resort.dto.UserRequest;

// Interface representing login-related service operations
public interface ILoginService {

	/*
	 * Method signature for loginUser, takes a UserRequest object as a parameter and
	 * returns a String
	 */
	String loginUser(UserRequest request);
}
