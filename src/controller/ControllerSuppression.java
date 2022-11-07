package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import daoImplementation.DaoImplementationModel;
import model.Contact;
import model.GestionnaireDeContact;
import vue.Vue;

public class ControllerSuppression implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	private DaoImplementationModel dao;
	
	public ControllerSuppression(GestionnaireDeContact gestionnaire, Vue vue, DaoImplementationModel dao) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
		this.dao = dao;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i =0;i<this.vue.getPaneContact().getComponentCount();i++) {
			if (e.getSource() == ((JPanel) this.vue.getPaneContact().getComponent(i)).getComponent(1) ) {
				
				Contact contact = new Contact(this.gestionnaire.getContacts().get(i).getNom(),this.gestionnaire.getContacts().get(i).getTel()); 
				contact.setId(this.gestionnaire.getContacts().get(i).getId());
				if(this.gestionnaire.supprimeContact(this.gestionnaire.getContacts().get(i))) {
					this.dao.supprimerContact(contact);
				}
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
