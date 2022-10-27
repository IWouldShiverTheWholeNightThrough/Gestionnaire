
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JLabel;

import controller.ControllerAjout;
import controller.ControllerModif;
import controller.ControllerRecherche;
import controller.ControllerSuppression;
import model.Appareil;
import model.Contact;
import model.GestionnaireDeContact;
import model.GestionnaireUtilisateurs;
import model.Utilisateur;
import vue.ContactJPanel;
import vue.Vue;

public class TestGestionnaire {

	public static void main(String[] args) {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		GestionnaireUtilisateurs gestUsers = new GestionnaireUtilisateurs();
		int entree;
		String login;
		do {
			
			System.out.println("Menu utilisateur\n0 = sortir\n1 = afficher les users\n2 = se connecter\n3 = créer un nouvel utilisateur");
			entree = scan1.nextInt();
			switch(entree) {
			case 0:
				System.out.println("Bye!");
				break;
			case 1:
				gestUsers.afficheUsers();
				break;
			case 2:
				System.out.println("Entrez un login:");
				login = scan1.next();
				Utilisateur user = new Utilisateur(login);
				if( gestUsers.rechercheUserparId(user) != -1) {
					manipAppareils(user, scan1, scan2);
				} else {
					System.out.println("Utilisateur inconnu");
				}				
				break;
			case 3:
				System.out.println("Création d'un nouvel utilisateur, entrez un nouveau login:");
				login = scan1.next();
				Utilisateur user_a_creer = new Utilisateur(login);
				if(gestUsers.creerUser(user_a_creer)) {
					System.out.println("Nouvel Utilisateur crée");
				} else {
					System.out.println("User déjà existant");
				}
				
				break;
			}
		} while(entree != 0);

		scan1.close();
		scan2.close();

	}

	public static void manipAppareils(Utilisateur user, Scanner scan1, Scanner scan2) {
		
		
		int entree;
		String choixApp;
		do {
			System.out.println("Menu de l'utilisateur "+user.getId()+"\n0 = sortir\n1 = afficher les appareils\n2 = accéder au gestionnaire d'un appareil\n3 = ajouter un appareil");
			
			entree = scan1.nextInt();
			switch(entree) {
			case 0:
				System.out.println("Bye!");
				break;
			case 1:
				user.afficheAppareils();
				break;
			case 2:
				System.out.println("A quel Appareil se connecter ?");
				choixApp = scan1.next();
				Appareil app = new Appareil(choixApp);
				int i = user.rechercheAppareil(app);
				if(i != -1) {
					manipGestionnaire(user.getAppareils().get(i).getGestionnaire(), scan1, scan2);
				} else {
					System.out.println("Appareil inconnu");
				}
				break;
			case 3:
				System.out.println("Entrez un nouvel appareil");
				choixApp = scan1.next();
				Appareil new_app = new Appareil(choixApp);
				new_app.setGestionnaire(user.getGestionnaire());
				if(user.ajouteAppareil(new_app)) {
					System.out.println("Appareil crée");
				} else {
					System.out.println("Appareil déjà existant");
				}
				break;
			}
			
		} while(entree != 0);
		
	}
	
	public static void manipGestionnaire(GestionnaireDeContact gestionnaire, Scanner scan1, Scanner scan2) {
		Vue vue = new Vue();
		vue.lancerVue();
		
		ControllerAjout controllerAjout = new ControllerAjout(gestionnaire, vue);
		vue.getButtonAjout().addActionListener(controllerAjout);
		
		ControllerRecherche controllerRecherche = new ControllerRecherche(gestionnaire, vue);
		vue.getButtonRecherche().addActionListener(controllerRecherche);
		
		gestionnaire.addObserver(vue.getLabelResult());
		gestionnaire.getSeriGest().addObserver(vue.getPaneContact());
		
		for(Contact contact: gestionnaire.getContacts()) {
			ContactJPanel pane = new ContactJPanel();
			
			JLabel label = new JLabel(contact.toString());
			JButton buttonSupp = new JButton("Supprimer");
			ControllerSuppression controllerSuppression = new ControllerSuppression(gestionnaire, vue);
			buttonSupp.addActionListener(controllerSuppression);
			
			JButton buttonModif = new JButton("Modifier");
			ControllerModif controllerModif = new ControllerModif(gestionnaire, vue);
			buttonModif.addActionListener(controllerModif);
			pane.add(label);
			pane.add(buttonSupp);
			pane.add(buttonModif);
			
			vue.getPaneContact().add(pane);
		}
		
		
		vue.getFrame().pack();
		vue.getFrame().setVisible(true);
		
	}
}
