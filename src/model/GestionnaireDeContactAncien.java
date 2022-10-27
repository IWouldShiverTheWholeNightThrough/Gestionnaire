package model;
import java.util.HashSet;

public class GestionnaireDeContactAncien {

	private HashSet<Appareil> appareils;
	private Contact[] contacts;

	public GestionnaireDeContactAncien() {
		this.contacts = new Contact[0];
	}

	public void afficheContacts() {

		if(this.contacts.length == 0) {
			System.out.println("Pas de contacts dans votre répertoire.");
		} else {
			System.out.println("Liste des contacts:\n");
			for(int i = 0;i< this.contacts.length; i++) {
				System.out.print("-> ");
				System.out.println(contacts[i]);
			}
		}
	}

	public boolean ajouteContact(Contact contact) {
		if(this.rechercheContactParTel(contact) == -1) {
			
			Contact[] new_contacts = new Contact[this.contacts.length + 1];
			for(int i = 0; i<this.contacts.length; i++) {
				new_contacts[i] = this.contacts[i];
			}
			new_contacts[this.contacts.length] = contact;
			this.contacts = new_contacts;
			return true;
		}

		return false;		
	}

	public boolean supprimeContact(Contact contact) {
		int indice = rechercheContactParTel(contact);
		if(indice != -1) {
			
			Contact[] new_contacts = new Contact[this.contacts.length - 1];
			for(int i = 0; i<indice; i++) {
				new_contacts[i] = this.contacts[i];
			}
			for(int i = indice; i<this.contacts.length - 1; i++) {
				new_contacts[i] = this.contacts[i+1];
			}
			new_contacts[this.contacts.length] = contact;
			this.contacts = new_contacts;
			
			return true;
		} else {
			return false;
		}
	}

	public boolean modifieContact(Contact contact, Contact new_contact) {

		int indice = rechercheContactParTel(contact);

		if(indice != -1) {
			this.contacts[indice].setNom(new_contact.getNom());
			this.contacts[indice].setTel(new_contact.getTel());
			return true;
		}

		return false;
	}

	public int rechercheContactParTel(Contact contact) {

		int n = this.contacts.length;
		int i = 0;
		int indice = -1;
		while(indice == -1 && i<n) {
			if(this.contacts[indice].getTel().equals(contact.getTel())) {
				indice = i;
				break;
			}
			i++;
		}
		return indice;
	}

	public HashSet<Appareil> getAppareils() {
		return this.appareils;
	}

	public void setAppareils(HashSet<Appareil> appareils) {
		this.appareils = appareils;
	}

	public Contact[] getContacts() {
		return this.contacts;
	}
	public void setContacts(Contact[] contacts) {
		this.contacts = contacts;
	}


}
