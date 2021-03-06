package com.jsfcourse.paginator;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;


import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;



//import com.jsfcourse.paginator.UserPAG.DataService;

import leagueofcash.dao.UserDAO;
import leagueofcash.entities.User;

public class PaginatorDataModel extends LazyDataModel<User> {
	
	private static final long serialVersionUID = 1L;
	
	private UserDAO u;

	

	public UserDAO getU() {
		return u;
	}

	public void setU(UserDAO u) {
		this.u = u;
	}

	public PaginatorDataModel(UserDAO u) {
		this.u = u;
	}
	
	@EJB
    UserDAO userDAO;
    
	@Inject
	FacesContext context;
    
	@Override
	public List<User> load (int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy){

		int rowCount = 0;
			rowCount = u.getRows();
						
			setRowCount((int) rowCount);
			
			return u.getUsers(offset, pageSize);
		
		
	}
	

}
