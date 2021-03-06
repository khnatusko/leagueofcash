package javax.faces.simplesecurity;

import java.util.HashSet;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class RemoteClient<T> {

	private String login;
	private String password;
	private String name;
	private String title;
	private String remoteAddr;
	private String remoteHost;
	private int remotePort;
	private T details;

	private HashSet<String> roles = new HashSet<String>();

	public RemoteClient() {
	}

	public RemoteClient(ServletRequest request) {
		this.remoteAddr = request.getRemoteAddr();
		this.remoteHost = request.getRemoteHost();
		this.remotePort = request.getRemotePort();
	}

	public RemoteClient(String login, String password, String name,String title, ServletRequest request) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.title = title;
		if (request != null) {
			this.remoteAddr = request.getRemoteAddr();
			this.remoteHost = request.getRemoteHost();
			this.remotePort = request.getRemotePort();			
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public T getDetails() {
		return details;
	}

	public void setDetails(T details) {
		this.details = details;
	}

	public HashSet<String> getRoles() {
		return roles;
	}

	public void setRoles(HashSet<String> roles) {
		this.roles = roles;
	}

	public boolean isInRole(String role) {
		return roles.contains(role);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isInOneRole(HashSet<String> roles) {
		boolean found = false;
		for (String role : roles) {
			if (this.roles.contains(role)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public void store(HttpServletRequest request) {
		this.remoteAddr = request.getRemoteAddr();
		this.remoteHost = request.getRemoteHost();
		this.remotePort = request.getRemotePort();
		HttpSession session = request.getSession();
		session.setAttribute("remoteClient", this);
	}

	public static RemoteClient load(HttpSession session) {
		return (RemoteClient) session.getAttribute("remoteClient");
	}

}
