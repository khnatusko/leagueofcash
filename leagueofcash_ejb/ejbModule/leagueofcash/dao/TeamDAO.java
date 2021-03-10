package leagueofcash.dao;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import leagueofcash.entities.League;
import leagueofcash.entities.Team;
import leagueofcash.entities.User;



@Stateless
public class TeamDAO {
	private final static String UNIT_NAME = "LoC";

	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Team team) {
		em.persist(team);
	}

	public Team merge(Team team) {
		return em.merge(team);
	}
	
	public void remove(Team team) {
		em.remove(em.merge(team));
	}

	public Team find(Object id) {
		return em.find(Team.class, id);
	}

	public Team getTeamFromDatabase() {
		Team last = new Team();
		Query query = em.createQuery("SELECT t FROM Team t order by idteam DESC");
	    query.setMaxResults(1);
	      
	try {	
		last = (Team)query.getSingleResult();
		
	} catch(NoResultException e) {
		last = null;
	}
	return last;
	}
	
	public List<Team> getTeams(int offset, int pageSize){
		List<Team> team = null;
		Query query = em.createQuery("Select t From Team t");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List<Team> list = query.getResultList();
		return list;

	}

	public int getRows() {
		int list = 0;
		
		Query query = em.createQuery("Select t From Team t");
		
		try {
			list = query.getResultList().size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
	
	public List<User> getUserList(Team team){
		Team t = em.find(Team.class, team.getIdteam());
		t.getUsers().size();
		return t.getUsers();
	}
	
	
}

