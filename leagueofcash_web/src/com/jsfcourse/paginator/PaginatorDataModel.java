package com.jsfcourse.paginator;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;


//import com.jsfcourse.paginator.UserPAG.DataService;

import leagueofcash.dao.UserDAO;
import leagueofcash.entities.User;

public class PaginatorDataModel extends LazyDataModel<User> {
	
	private static final long serialVersionUID = 1L;
	
	private List<User> getUsers;
	
    public List<User> getGetUsers() {
		return getUsers;
	}

	public void setGetUsers(List<User> getUsers) {
		this.getUsers = getUsers;
	}

	public PaginatorDataModel(List<User> getUsers) {
		this.getUsers = getUsers;
	}
	
	@EJB
    UserDAO userDAO;
    
	@Inject
	FacesContext context;
    
	@Override
	public List<User> load (int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy){
		List<User> user = new ArrayList<User>();
		int rowCount = 0;
		if(getUsers.size()>0) {
			rowCount = getUsers.size();
			
			user = (List<User>) getUsers.stream()
					.skip(offset)
					.limit(pageSize)
					.collect(Collectors.toList());
			setRowCount((int) rowCount);
			
			return user;
		}else {
			user = null;
			return user;
		}
		
	}

}
