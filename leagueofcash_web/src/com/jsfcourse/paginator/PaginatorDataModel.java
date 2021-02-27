package com.jsfcourse.paginator;

import java.io.IOException;
import java.io.Serializable;
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
	
	//private List<User> datasource;

   // public PaginatorDataModel(List<User> datasource) {
    //    this.datasource = datasource;
    //}
    
    @EJB
    UserDAO userDAO;
    
	@Inject
	FacesContext context;
    
	//public PaginatorDataModel() {
	//	this.setRowCount(userDAO.getUsersAll());
	//}
	//@Override
	//public List<User> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy){
		//List<User> list = userDAO.getUsers(offset, pageSize);
		//return list;
	//}
	
	
	
	 //@Override
	 //   public User getRowData(String rowKey) {
	  //      for (User customer : datasource) {
	  //          if (customer.getIdUser() == Integer.parseInt(rowKey)) {
	   //             return customer;
	   //         }
	  //      }

	   //     return null;
	  //  }

	  //  @Override
	  //  public String getRowKey(User customer) {
	 //       return String.valueOf(customer.getIdUser());
	 //   }

	  //  @Override
	 //   public List<User> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
	  //    List<User> list = userDAO.getUsers(offset,pageSize);
	    	
	    	
	    	
	  //  	  long rowCount = datasource.stream()
	               // .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
	   //             .count();

	    //    List<User> getUsers = datasource.stream()
	    //            .skip(offset)
	    //        //    .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
	     //           .limit(pageSize)
	      //          .collect(Collectors.toList());
//

	         //rowCount
	      //  setRowCount((int) rowCount);
//
	    //    return list;
	   // }



}
