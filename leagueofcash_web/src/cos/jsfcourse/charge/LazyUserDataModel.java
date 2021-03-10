package cos.jsfcourse.charge;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import leagueofcash.dao.UserDAO;
import leagueofcash.entities.User;

@Named
@ViewScoped

public class LazyUserDataModel extends LazyDataModel<User> {
	private static final long serialVersionUID = 1L;
	
	public LazyUserDataModel() {
		
	}
	private int IDuser;
	
	private UserDAO userDAO;
	
	@Override
	public List<User> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		List<User> users = userDAO.getUsersView(offset, pageSize, IDuser);
		setRowCount(userDAO.countUsers(IDuser));
		return users;
	}
	
	@Override
	public User getRowData(String rowKey) {
		return userDAO.find(Integer.parseInt(rowKey));
	}
	
	@Override
	public String getRowKey(User object) {
		return String.valueOf(object.getIdUser());
	}

	public int getIDuser() {
		return IDuser;
	}

	public void setIDuser(int iDuser) {
		IDuser = iDuser;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
}
