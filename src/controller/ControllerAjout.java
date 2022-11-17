package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import daoImplementation.DaoImplementationModel;
import model.Contact;
import model.GestionnaireDeContact;
import model.Utilisateur;
import vue.Vue;

public class ControllerAjout implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	private DaoImplementationModel dao;

	public ControllerAjout(GestionnaireDeContact gestionnaire, Vue vue, DaoImplementationModel dao) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
		this.dao = dao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(this.vue.getTxtfieldTel().getText().equals("BIGFLEX")) {
			System.out.println("\n\n\n\n\n                               --------- READY ???? --------\n\n\n\n");
		} else {

			Contact contact = new Contact(this.vue.getTxtfieldNom().getText(), this.vue.getTxtfieldTel().getText());
			contact.setImage(this.vue.getImage());
			contact.setUser(this.gestionnaire.getUser());
			if(gestionnaire.ajouteContact(contact)) {
				this.dao.ajouterContact(contact);
				this.vue.setImage(null);
			}

		}
	}

	public GestionnaireDeContact getGestionnaire() {
		return this.gestionnaire;
	}

	public void setGestionnaire(GestionnaireDeContact gestionnaire) {
		this.gestionnaire = gestionnaire;
	}


	public Vue getVue() {
		return this.vue;
	}

	public void setVue(Vue vue) {
		this.vue = vue;
	}

	public DaoImplementationModel getDao() {
		return dao;
	}

	public void setDao(DaoImplementationModel dao) {
		this.dao = dao;
	}

}
