package com.jsfcourse.paginator;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import leagueofcash.dao.TeamDAO;
import leagueofcash.entities.Team;

public class PaginatorTeamDataModel extends LazyDataModel<Team> {

	private static final long serialVersionUID = 1L;
	
	private TeamDAO t;

	
	public TeamDAO getT() {
		return t;
	}

	public void setT(TeamDAO t) {
		this.t = t;
	}

	public PaginatorTeamDataModel(TeamDAO t) {
		this.t = t;
	}
	
	@EJB
    TeamDAO teamDAO;
    
	@Inject
	FacesContext context;
    
	@Override
	public List<Team> load (int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy){

		int rowCount = 0;
			rowCount = t.getRows();
						
			setRowCount((int) rowCount);
			
			return t.getTeams(offset, pageSize);
		
		
	}
	
}
