
public class GestionnaireDeContact { // on suppose qu'un nom apparait qu'une seule fois
	
	private Appareil[] appareils;
	private Contact[] contacts;

	public GestionnaireDeContact() {
		this.contacts = new Contact[0];
	}

	public void afficheContacts() {

		if(this.contacts.length == 0) {
			System.out.println("Pas de contacts dans votre répertoire.");
		} else {
			System.out.println("Liste des contacts:\n");
			for(int i = 0;i< this.contacts.length; i++) {
				System.out.println("-> nom: "+contacts[i].getNom()+" , tél: "+ contacts[i].getTel());
			}
		}
	}

	public void ajouteContact(Contact contact) {
		Contact[] new_contacts;

		int n = this.contacts.length;
		new_contacts = new Contact[n + 1];
		for(int i = 0; i<n; i++) {
			new_contacts[i] = contacts[i];
		}
		new_contacts[contacts.length] = contact;

		this.contacts = new_contacts;
	}

	public void supprimeContact(String nom) {
		int n = this.contacts.length;
		int indice = rechercheContactParNom(nom);

		if(indice != -1) {
			Contact[] new_contacts = new Contact[n-1];
			for(int i = 0; i < indice; i++) {
				new_contacts[i] = this.contacts[i];
			}
			for(int i = indice; i<n-1;i++) {
				new_contacts[i] = this.contacts[i+1];
			}
			this.contacts = new_contacts;
		}	
	}

	public void modifieContact(String ancien_nom, String nouveau_nom,String nouveau_tel) {

		int indice = rechercheContactParNom(ancien_nom);

		if(indice != -1) {
			this.contacts[indice].setNom(nouveau_nom);
			this.contacts[indice].setTel(nouveau_tel);
		}	
	}

	public int rechercheContactParNom(String nom) {

		int n = this.contacts.length;
		int i = 0;
		int indice = -1;
		while(indice == -1 && i<n) {
			if(this.contacts[i].getNom().equals(nom)) {
				indice = i;
				break;
			}
			i++;
		}
		return indice;
	}
	
	public Appareil[] getAppareils() {
		return this.appareils;
	}
	
	public void setAppareils(Appareil[] appareils) {
		this.appareils = appareils;
	}

	public Contact[] getContacts() {
		return this.contacts;
	}
	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}
}
