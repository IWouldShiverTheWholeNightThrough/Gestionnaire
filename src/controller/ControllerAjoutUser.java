package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import daoImplementation.DaoImplUser;
import model.GestionnaireUtilisateurs;
import model.Utilisateur;
import vue.Vue;


public class ControllerAjoutUser implements ActionListener{

	private GestionnaireUtilisateurs gestionnaire;
	private Vue vue;
	private DaoImplUser dao;
	
	public ControllerAjoutUser(GestionnaireUtilisateurs gestionnaire, Vue vue, DaoImplUser dao) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
		this.dao = dao;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Utilisateur user = new Utilisateur(this.vue.getTxtfieldNom().getText(), this.gestionnaire);
		
		if(gestionnaire.creerUser(user)) {
			this.dao.ajouterUser(user);
		}
		
	}
}
