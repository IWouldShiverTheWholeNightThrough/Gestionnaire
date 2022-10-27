package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Contact;
import model.GestionnaireDeContact;
import vue.Vue;

public class ControllerAjout implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	
	public ControllerAjout(GestionnaireDeContact gestionnaire, Vue vue) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Contact contact = new Contact(this.vue.getTxtfieldNom().getText(), this.vue.getTxtfieldTel().getText());
		gestionnaire.ajouteContact(contact);
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
	
}
