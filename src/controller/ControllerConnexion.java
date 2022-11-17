package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.GestionnaireUtilisateurs;
import model.Utilisateur;
import vue.Vue;

public class ControllerConnexion implements ActionListener{


	private GestionnaireUtilisateurs gestionnaire;
	private Vue vue;
	private Utilisateur user;
	
	public ControllerConnexion(GestionnaireUtilisateurs gestionnaire, Vue vue, Utilisateur user) {
		this.gestionnaire = gestionnaire;
		this.user = user;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i =0;i<this.vue.getPaneUser().getComponentCount();i++) {
			if (e.getSource() == ((JPanel) this.vue.getPaneUser().getComponent(i)).getComponent(1) ) {
				
				this.gestionnaire.getUsers().get(i).login(vue);
			}
		}
	}

	public GestionnaireUtilisateurs getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(GestionnaireUtilisateurs gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public Vue getVue() {
		return vue;
	}

	public void setVue(Vue vue) {
		this.vue = vue;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
}
