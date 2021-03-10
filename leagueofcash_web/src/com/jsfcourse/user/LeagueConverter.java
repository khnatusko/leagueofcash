package com.jsfcourse.user;


import java.io.Serializable;
import java.lang.System.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import leagueofcash.dao.LeagueDAO;
import leagueofcash.entities.League;

@ManagedBean(name="converterLeagueBean")
@ViewScoped
@FacesConverter(value = "leagueConverter")
public class LeagueConverter implements Converter, Serializable {
	
	
	
	@EJB
	LeagueDAO leagueDAO;
	
	private final static String UNIT_NAME = "LoC";
	@PersistenceContext(unitName = UNIT_NAME)
 
    private transient EntityManager em; 
	
	

	@Override
    public Object getAsObject(FacesContext ctx, UIComponent component,String league) {
	  League liga;	
		
	  if(league.isEmpty()) {
		  System.out.println("Liga jest pusta");
		  return null;
	  }
	  
	  try {
		  Integer id = Integer.parseInt(league);
		  liga = em.find(League.class, id);
		  System.out.println("NAZWE POBRANEJ LIGI TO:");
		  System.out.println(liga.getName());
		  
	      return liga; 
	  }catch(NumberFormatException e) {
		  System.out.println("BLAD, TO NIE LICZBA");
	  }
	 
	  return null;	
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object league) {
    	if (league == null || league.toString().isEmpty() || !(league instanceof League)) {
    		return "";
    	}
    	
    	
    	if(league instanceof League) {
    		return String.valueOf(((League) league).getIdLeague());
    	}else {
    		throw new ConverterException(new FacesMessage(String.format("%s is not a valid league",league)));
    	}
    	
//    	return league.toString();
    }
    
//    
//    @Override
//    public int hashCode() {
//    	final int prime = 31;
//    	int result = 1;
//    	result = prime * result + (int) (id ^ (id >>> 32));
//    }
    
}
