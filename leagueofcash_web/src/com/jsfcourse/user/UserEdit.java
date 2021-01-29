package com.jsfcourse.user;

import java.io.IOException;
import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import leagueofcash.dao.UserDAO;
import leagueofcash.entities.User;

@Named
@RequestScoped
public class UserEdit {
	private static final String PAGE_USER_ADD = "leagues?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String last_Name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	//Flash flash;
	
	@EJB
	UserDAO userDAO;
		
	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public List<User> getFullList(){
		return userDAO.getFullList();
	}

	public List<User> getList(){
		List<User> list = null;
		
		
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (last_Name != null && last_Name.length() > 0){
			searchParams.put("last_Name", last_Name);
		}
		
		
//		list = UserDAO.getList(searchParams);
		
		return list;
	}

	public String newUser(){
		User user = new User();
			
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("user", user);
		
		
		//flash.put("User", user);
		
		return PAGE_USER_ADD;
	}

	public String editUser(User user){
		
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("user", user);
		
		
		//flash.put("user", user);
		
		return PAGE_USER_ADD;
	}

	public String deletePerson(User user){
		userDAO.remove(user);
		return PAGE_STAY_AT_THE_SAME;
	}
}

	
	
	
	
	
	
