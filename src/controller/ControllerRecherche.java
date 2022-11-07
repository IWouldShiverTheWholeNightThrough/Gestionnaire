package controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import daoImplementation.DaoImplementationModel;
import model.Contact;
import model.GestionnaireDeContact;

import vue.Vue;

public class ControllerRecherche implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	private DaoImplementationModel dao;

	public ControllerRecherche(GestionnaireDeContact gestionnaire, Vue vue, DaoImplementationModel dao) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
		this.dao = dao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Contact contact = new Contact(this.vue.getTxtfieldNom().getText(), this.vue.getTxtfieldTel().getText());
		int result = this.gestionnaire.rechercheContactParTel(contact);

		if(result == -1) {
			this.vue.getLabelResult().setText(contact.getTel() + " n'est pas connu");
		} else {
			this.vue.getLabelResult().setText(contact.getTel() + " appartient à "+ this.gestionnaire.getContacts().get(result).getNom());


			Collections.swap(this.gestionnaire.getContacts(), 0, result);
			
			this.dao.change();
			this.dao.notifyObservers();

			this.vue.getPaneContact().getComponents()[0].setBackground(Color.red);
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
