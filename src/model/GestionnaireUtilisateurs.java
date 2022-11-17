package model;
import java.util.ArrayList;

import controller.ControllerAjoutUser;
import daoImplementation.DaoImplUser;
import vue.Vue;

public class GestionnaireUtilisateurs {
	private ArrayList<Utilisateur> users;
	private DaoImplUser dao;
	private Vue vue;
	
	public GestionnaireUtilisateurs(Vue vue) {
		this.users = new ArrayList<Utilisateur>();
		
		DaoImplUser dao = new DaoImplUser(this);
		this.dao = dao;
		this.vue = vue;
		this.vue.lancerVue(this, this.dao);
		
	}
	
	public boolean creerUser(Utilisateur user) {
		if(this.rechercheUserparName(user) == -1) {
			this.users.add(user);
			return true;
		}

		return false;		
	}
	
	public int rechercheUserparName(Utilisateur user) {
		
		int n = this.users.size();
		int i = 0;
		int indice = -1;
		while(indice == -1 && i<n) {
			if(this.users.get(i).getName().equals(user.getName())) {
				indice = i;
				break;
			}
			i++;
		}
		return indice;
	}
	
	public ArrayList<Utilisateur> getUsers() {
		return this.users;
	}
	public void setUsers(ArrayList<Utilisateur> users) {
		this.users = users;
	}

	public DaoImplUser getDao() {
		return dao;
	}

	public void setDao(DaoImplUser dao) {
		this.dao = dao;
	}

	public Vue getVue() {
		return vue;
	}

	public void setVue(Vue vue) {
		this.vue = vue;
	}
	
	
	
}
