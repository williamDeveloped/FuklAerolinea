package co.com.ganso.login.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.ganso.entities.Usuario;
import co.com.ganso.nucleo.bean.BackingUI;
import co.com.ganso.services.bussiness.IUsuarioSvc;


@ManagedBean(name = "login")
@SessionScoped
public class LoginBean extends BackingUI implements Serializable {
	
	private static final long serialVersionUID = 8918479798475119956L;
	
	@EJB
	private IUsuarioSvc usuarioSvc;
	
	private Usuario usuario;
	private String password;
	private String username;
	private String administrador;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
	}

	public String accede() {
		try {
			usuario.setTLogin(getUsername());
			usuario.setTPass(getPassword());
			//usuario.setTAdministrador("S");
			usuario.setTAdministrador(getAdministrador());
			
			if (usuarioSvc.acceder(usuario)) {
				dialogInfo("Acceso Correcto");
				return "admin/premios/premios.xhtml";

			} else {
				if(getAdministrador().equals("S")){
				dialogWarn("Credenciales Incorrectas.");
				return "indexAdm.xhtml";
				}else{
					dialogWarn("Credenciales Incorrectas.");
					return "index.xhtml";
				}
				}
		} catch (Exception e) {
			dialogError(e);
			return "";
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

}
