package com.creativedeveloper.ecommerce.data.remote.model;

import java.util.List;

public class UserResponse {
	private List<UserListItem> userList;

	public void setUserList(List<UserListItem> userList){
		this.userList = userList;
	}

	public List<UserListItem> getUserList(){
		return userList;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" +
			"userList = '" + userList + '\'' + 
			"}";
		}
}