package com.jsfcourse.paginator;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;



import leagueofcash.dao.UserDAO;
import leagueofcash.entities.User;


@Named
@RequestScoped



public class PaginatorView{

	private String last_Name;
	
	@Inject
	ExternalContext extcontext;
	
	@EJB
	UserDAO UserDAO;

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public List<User> getFullList(){
		return UserDAO.getFullList();
	}
	
	
	public List<User> getList(){
		List<User> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (last_Name != null && last_Name.length() > 0){
			searchParams.put("last_Name", last_Name);
		}
		
		//2. Get list
		list = UserDAO.getList(searchParams);
		
		return list;
	}
    

}