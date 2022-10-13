import java.util.Scanner;

public class TestGestionnaire {

	public static void main(String[] args) {

		GestionnaireDeContact gestionnaire = new GestionnaireDeContact(); 

		Appareil app1 = new Appareil(gestionnaire, "smartphone");
		Appareil app2 = new Appareil(gestionnaire, "television");
		Appareil app3 = new Appareil(gestionnaire, "appleWatch");

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		System.out.println("Entrez dans le gestionnaire de l'app " + app1.getCategorie());
		manipGestionnaire(app1.getGestionnaire(), scan1, scan2);

		System.out.println("Entrez dans le gestionnaire de l'app " + app2.getCategorie());
		manipGestionnaire(app2.getGestionnaire(), scan1, scan2);

		System.out.println("Entrez dans le gestionnaire de l'app " + app3.getCategorie());
		manipGestionnaire(app3.getGestionnaire(), scan1, scan2);

		scan1.close();
		scan2.close();

	}

	public static void manipGestionnaire(GestionnaireDeContact gestionnaire, Scanner scan1, Scanner scan2) {

		int entree;
		do {

			System.out.println("\nMenu gestionnaire: \n0 = sortir du menu\n1 = afficher tous les contacts\n2 = ajouter contact\n3 = supprimer contact\n4 = modif contact\n5 = chercher contact");
			entree = scan1.nextInt();
			switch(entree) {
			case 0:
				System.out.println("Bye !");
				break;
			case 1:
				gestionnaire.afficheContacts();
				break;
			case 2:
				System.out.println("Entrez un nom puis un tél");
				String nom = scan2.next();
				String tel = scan2.next();
				Contact contact = new Contact(nom, tel);
				gestionnaire.ajouteContact(contact);
				break;
			case 3:
				System.out.println("Entrez un nom:");
				String nom_a_supprimer = scan2.next();
				gestionnaire.supprimeContact(nom_a_supprimer);
				break;
			case 4:
				System.out.println("Entrez un nom à modifier puis le nouveau nom et le nouveau téléphone:");
				String ancien_nom = scan2.next();
				String nouveau_nom = scan2.next();
				String nouveau_tel = scan2.next();
				gestionnaire.modifieContact(ancien_nom, nouveau_nom, nouveau_tel);
				break;
			case 5:
				System.out.println("Entrez un nom:");
				String nom_a_chercher = scan2.next();
				int result = gestionnaire.rechercheContactParNom(nom_a_chercher);
				if(result == -1) {
					System.out.println(nom_a_chercher + " n'est pas connu");
				} else {
					System.out.println(nom_a_chercher + " a le numéro de téléphone suivant: "+ gestionnaire.getContacts()[result].getTel());
				}
				break;
			} 

		} while(entree != 0);
	}
}
