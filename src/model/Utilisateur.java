package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import controller.ControllerAjout;
import controller.ControllerParcourir;
import controller.ControllerRecherche;
import daoImplementation.DaoImplementationModel;
import vue.Vue;

@Entity
@Table(name = "user")
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Contact> contacts;
	
	@Transient
	private GestionnaireUtilisateurs gestionnaireUser;
	
	@Transient
	private GestionnaireDeContact gestionnaire;
	
	@Transient
	private ArrayList<Appareil> appareils;
	
	@Transient
	private DaoImplementationModel dao;
	
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String name) {
		this.name = name;
		this.appareils = new ArrayList<Appareil>();
	}
	
	public void login() {
		this.gestionnaire = new GestionnaireDeContact();
		this.dao = new DaoImplementationModel(this.gestionnaire, this);
		Vue vue = new Vue();
		vue.lancerVue();
		
		this.dao.addObserver(vue.getPaneContact());
		this.dao.notifyObservers();

		ControllerAjout controllerAjout = new ControllerAjout(this.gestionnaire, vue, this.dao);
		vue.getButtonAjout().addActionListener(controllerAjout);

		ControllerRecherche controllerRecherche = new ControllerRecherche(this.gestionnaire, vue, this.dao);
		vue.getButtonRecherche().addActionListener(controllerRecherche);
		
		ControllerParcourir controllerParcourir = new ControllerParcourir(this.gestionnaire, vue, this.dao);
		vue.getButtonParcourir().addActionListener(controllerParcourir);

		this.gestionnaire.addObserver(vue.getLabelResult());

		
		vue.getFrame().pack();
		vue.getFrame().setVisible(true);
	}
	

	public String toString() {
		return this.name;
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Contact> getContacts() {
		return contacts;
	}


	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}


}
