package com.creativedeveloper.ecommerce.data.remote.model;

public class UserListItem{
	private String password;
	private String role;
	private String name;
	private int id;
	private String userName;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	@Override
 	public String toString(){
		return 
			"UserListItem{" + 
			"password = '" + password + '\'' + 
			",role = '" + role + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",userName = '" + userName + '\'' + 
			"}";
		}
}
