package leagueofcash.dao;



import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import leagueofcash.entities.League;
import leagueofcash.entities.Team;
import leagueofcash.entities.User;



@Stateless
public class LeagueDAO {
	private final static String UNIT_NAME = "LoC";
	
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(League team) {
		em.persist(team);
	}

	public League merge(League team) {
		return em.merge(team);
	}

	public void remove(League team) {
		em.remove(em.merge(team));
	}

	public League find(Object id) {
		return em.find(League.class, id);
	}
	
	//@Override
	//public boolean equals(Object league) {
	//    if (this == league)
	//        return true;
	//    if (league == null)
	//        return false;
	//    if (!(league instanceof League))//changed this from (getClass() != obj.getClass())
	//        return false;
	//    League other = (League) league;
	 //   if (idLeague == 0) {
	 //       if (other.getIdLeague() != 0)
	//            return false;
	 //   } else if (!league.equals(other.getIdLeague()))
	 //       return false;
	//    return true;
	//}
	
	public League getLeagueFromDatabase(String name) {
		League last = new League ();
		Query query = em.createQuery("SELECT l FROM League l where l.name =: name order by idLeague DESC").setParameter("name", name);
		//Query query = em.createQuery("SELECT l FROM League l order by idLeague DESC");
	    query.setMaxResults(1);
	      
	try {	
		last = (League)query.getSingleResult();
		
	} catch(NoResultException e) {
		last = null;
	}
	return last;
	}
	
	
	public List<League> getFullList() {
		List<League> list = null;

		Query query = em.createQuery("select l from League l");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<League> getList(Map<String, Object> searchParams) {
		List<League> list = null;

		String idLeague = (String) searchParams.get("idLeague");

		Query query = em.createQuery("SELECT l FROM League l where l.idLeague = :idLeague order by l.idLeague, l.name",League.class);
		
		if (idLeague != null) {
			query.setParameter("idLeague", idLeague+"%");
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


	


}

