import java.util.Scanner;

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
	
	public static void manipGestionnaireMenu(GestionnaireDeContact gestionnaire, Scanner scan1, Scanner scan2) {

		int entree;
		do {
			System.out.println();
			System.out.println("\nMenu gestionnaire: \n0 = sortir du menu\n1 = afficher tous les contacts\n2 = ajouter contact\n3 = supprimer contact\n4 = modif contact\n5 = chercher contact");

			entree = scan1.nextInt();
			Contact contact = new Contact();
			switch(entree) {
			case 0:
				System.out.println("Bye !");
				break;
			case 1:
				gestionnaire.afficheContacts();
				break;
			case 2:
				System.out.println("Entrez un nom puis un tél");
				contact.setNom(scan2.next());
				contact.setTel(scan2.next());

				if( gestionnaire.ajouteContact(contact) ) {
					System.out.println("Contact ajouté");
				} else {
					System.out.println("Contact déjà existant");
				}

				break;
			case 3:
				System.out.println("Entrez un tel:");
				contact.setTel(scan2.next());
				if( gestionnaire.supprimeContact(contact) ) {
					System.out.println("Contact supprimé");
				} else {
					System.out.println("Contact non-listé");
				}
				break;
			case 4:
				System.out.println("Entrez un tel à modifier puis le nouveau nom et le nouveau téléphone:");

				Contact contact2 = new Contact();

				contact.setTel( scan2.next() );
				contact2.setNom( scan2.next() );
				contact2.setTel( scan2.next());
				boolean resul = gestionnaire.modifieContact(contact, contact2);
				if(resul) {
					System.out.println("Contact modifié");
				} else {
					System.out.println("Contact non-listé");
				}
				break;
			case 5:
				System.out.println("Entrez un tel:");
				contact.setTel( scan2.next() );
				int result = gestionnaire.rechercheContactParTel(contact);
				if(result == -1) {
					System.out.println(contact.getTel() + " n'est pas connu");
				} else {
					System.out.println(contact.getTel() + " appartient à "+ gestionnaire.getContacts().get(result).getNom());
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
		
		ControllerActualisation controllerActualisation = new ControllerActualisation(gestionnaire, vue);
		vue.getButtonActualisation().addActionListener(controllerActualisation);
	}
}
