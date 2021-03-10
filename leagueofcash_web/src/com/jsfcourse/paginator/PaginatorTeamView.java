package com.jsfcourse.paginator;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import leagueofcash.dao.TeamDAO;
import leagueofcash.entities.Team;

@Named
@RequestScoped
public class PaginatorTeamView implements Serializable {

private LazyDataModel<Team> lazyModel;
	
	@Inject
	private TeamDAO team;
	
	@EJB
	TeamDAO teamdao;
	
	@PostConstruct
	public void init() {
		lazyModel = new PaginatorTeamDataModel(teamdao);
	}

	public LazyDataModel<Team> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Team> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public TeamDAO getTeamdao() {
		return teamdao;
	}

	public void setTeamdao(TeamDAO teamdao) {
		this.teamdao = teamdao;
	}
	
	
}
