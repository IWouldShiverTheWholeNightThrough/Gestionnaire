package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import daoImplementation.DaoImplementationModel;
import model.Contact;
import model.GestionnaireDeContact;
import vue.Vue;

public class ControllerModif implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	private DaoImplementationModel dao;
	
	public ControllerModif(GestionnaireDeContact gestionnaire, Vue vue, DaoImplementationModel dao) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
		this.dao = dao;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i =0;i<this.vue.getPaneContact().getComponentCount();i++) {
			if (e.getSource() == ((JPanel) this.vue.getPaneContact().getComponent(i)).getComponent(2) ) {
				Contact contact = new Contact(this.vue.getTxtfieldNom().getText(), this.vue.getTxtfieldTel().getText());

				if(this.gestionnaire.modifieContact(this.gestionnaire.getContacts().get(i), contact)) {
					contact.setId(this.gestionnaire.getContacts().get(i).getId());
					contact.setImage(this.vue.getImage());
					contact.setUser(this.gestionnaire.getContacts().get(i).getUser());
					this.gestionnaire.getContacts().get(i).setImage(this.vue.getImage());
					
					this.dao.modifierContact(contact);
					this.vue.setImage(null);
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
