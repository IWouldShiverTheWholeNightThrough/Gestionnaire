package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.Contact;
import model.GestionnaireDeContact;
import vue.Vue;

public class ControllerModif implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	
	public ControllerModif(GestionnaireDeContact gestionnaire, Vue vue) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i =0;i<this.vue.getPaneContact().getComponentCount();i++) {
			if (e.getSource() == ((JPanel) this.vue.getPaneContact().getComponent(i)).getComponent(2) ) {
				Contact contact = new Contact(this.vue.getTxtfieldNom().getText(), this.vue.getTxtfieldTel().getText());
				this.gestionnaire.modifieContact(this.gestionnaire.getContacts().get(i), contact);
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
}
