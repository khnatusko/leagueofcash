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
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.primefaces.model.LazyDataModel;

import leagueofcash.dao.UserDAO;
import leagueofcash.entities.User;


@Named
@RequestScoped
public class PaginatorView implements Serializable{
	
	private LazyDataModel<User> lazyModel;
	
	@Inject
	private UserDAO user;
	
	@EJB
	UserDAO userdao;
	
	@PostConstruct
	public void init() {
		lazyModel = new PaginatorDataModel(userdao);
	}

	public LazyDataModel<User> getLazyModel() {
		return lazyModel;
	}


	public void setLazyModel(LazyDataModel<User> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	
	
	
	
}
