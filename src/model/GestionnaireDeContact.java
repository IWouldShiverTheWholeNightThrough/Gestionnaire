package model;
import java.util.ArrayList;
import java.util.Observable;

public class GestionnaireDeContact extends Observable{

	private ArrayList<Appareil> appareils;
	private ArrayList<Contact> contacts;

	public GestionnaireDeContact() {
		this.contacts = new ArrayList<Contact>();
		// set les contacts au contenu de la base de données
		// créer une nouvelle instance de la dao, 
	}

	public boolean ajouteContact(Contact contact) {
		this.setChanged();
		if(this.rechercheContactParTel(contact) == -1) {
			this.contacts.add(contact);		
			
			this.notifyObservers("Contact Ajouté");
			
			return true;
		}

		this.notifyObservers("Contact déjà existant");
		return false;		
	}

	public boolean supprimeContact(Contact contact) {
		this.setChanged();
		int indice = rechercheContactParTel(contact);
		if(indice != -1) {
			this.contacts.remove(this.contacts.get(indice));
			
			this.notifyObservers("Contact supprimé");
			return true;
		} else {
			
			this.notifyObservers("Contact non-listé"); // non necessaire mais bon
			return false;
		}
	}

	public boolean modifieContact(Contact contact, Contact new_contact) {

		this.setChanged();
		
		int indice = rechercheContactParTel(contact);

		if(indice != -1 ) {
			
			if(! contact.getTel().equals(new_contact.getTel()))
			{
				if(rechercheContactParTel(new_contact) != -1) {
					this.notifyObservers("Tél déjà existant");
					return false;
				}
			}
			
			this.contacts.get(indice).setNom(new_contact.getNom());
			this.contacts.get(indice).setTel(new_contact.getTel());
			
			this.notifyObservers("Contact modifié");
			
			return true;
		}

		this.notifyObservers("Contact non-listé"); // ne devrait jamais arriver 
		return false;
	}

	public int rechercheContactParTel(Contact contact) {

		int n = this.contacts.size();
		int i = 0;
		int indice = -1;
		while(indice == -1 && i<n) {
			if(this.contacts.get(i).getTel().equals(contact.getTel())) {
				indice = i;
				break;
			}
			i++;
		}
		
		return indice;
	}
	

	public ArrayList<Appareil> getAppareils() {
		return this.appareils;
	}

	public void setAppareils(ArrayList<Appareil> appareils) {
		this.appareils = appareils;
	}

	public ArrayList<Contact> getContacts() {
		return this.contacts;
	}
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

}
