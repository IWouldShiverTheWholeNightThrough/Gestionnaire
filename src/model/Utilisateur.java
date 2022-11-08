package model;
import java.util.ArrayList;

import controller.ControllerAjout;
import controller.ControllerRecherche;
import daoImplementation.DaoImplementationModel;
import vue.Vue;

public class Utilisateur {
	private GestionnaireUtilisateurs gestionnaireUser;
	private GestionnaireDeContact gestionnaire;
	private ArrayList<Appareil> appareils;
	private String id;
	private DaoImplementationModel dao;

	public Utilisateur(String id) {
		this.id = id;
		this.appareils = new ArrayList<Appareil>();
	}
	
	
	public void login() {
		this.gestionnaire = new GestionnaireDeContact();
		this.dao = new DaoImplementationModel(this.gestionnaire);
		Vue vue = new Vue();
		vue.lancerVue();
		
		this.dao.addObserver(vue.getPaneContact());
		this.dao.notifyObservers();

		ControllerAjout controllerAjout = new ControllerAjout(this.gestionnaire, vue, this.dao);
		vue.getButtonAjout().addActionListener(controllerAjout);

		ControllerRecherche controllerRecherche = new ControllerRecherche(this.gestionnaire, vue, this.dao);
		vue.getButtonRecherche().addActionListener(controllerRecherche);

		this.gestionnaire.addObserver(vue.getLabelResult());


		vue.getFrame().pack();
		vue.getFrame().setVisible(true);
	}
	

	public String toString() {
		return this.id;
	}

	public void afficheAppareils() {
		if(this.appareils.size() == 0) {
			System.out.println("Pas d'appareils enregistrés");
		} else {
			System.out.println("Liste des Appareils:\n");
			for(Appareil appareil: this.appareils) {
				System.out.print("-> ");
				System.out.println(appareil);
			}
		}
		System.out.println();
	}

	public boolean ajouteAppareil(Appareil appareil) {
		if(rechercheAppareil(appareil) == -1) {
			this.appareils.add(appareil);
			return true;
		}
		return false;
	}

	public int rechercheAppareil(Appareil appareil) {
		int n = appareils.size();
		int i = 0;
		int indice = -1;
		while(indice == -1 && i<n) {
			if(this.appareils.get(i).getCategorie().equals(appareil.getCategorie())) {
				indice = i;
				break;
			}
			i++;
		}
		return indice;

	}

	public ArrayList<Appareil> getAppareils() {
		return this.appareils;
	}

	public void setAppareils(ArrayList<Appareil> appareils) {
		this.appareils = appareils;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GestionnaireDeContact getGestionnaire() {
		return this.gestionnaire;
	}

	public void setGestionnaire(GestionnaireDeContact gestionnaire) {
		this.gestionnaire = gestionnaire;
	}	

	public GestionnaireUtilisateurs getGestionnaireUser() {
		return this.gestionnaireUser;
	}
	public void setGestionnaireUser(GestionnaireUtilisateurs gestionnaireUser) {
		this.gestionnaireUser = gestionnaireUser;
	}
	
	public DaoImplementationModel getDao() {
		return dao;
	}

	public void setDao(DaoImplementationModel dao) {
		this.dao = dao;
	}

}
