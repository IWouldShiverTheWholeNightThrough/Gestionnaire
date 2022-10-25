import java.util.ArrayList;
import java.util.Observable;

public class GestionnaireDeContact extends Observable{

	private ArrayList<Appareil> appareils;
	private ArrayList<Contact> contacts;
	private SerializeGestionnaire seriGest;

	public GestionnaireDeContact(SerializeGestionnaire seriGest) {
		this.seriGest = seriGest;
		this.seriGest.setGestionnaire(this);
		
		try {
		this.contacts = seriGest.deserializeContacts();
		} catch(Exception e) {
				System.out.println("exception = "+e);
		}
	}

	public void afficheContacts() {

		try {
		seriGest.ecrireTxt();
		} catch(Exception e) {
			System.out.println("exception = "+e);
		}
	}

	public boolean ajouteContact(Contact contact) {
		this.setChanged();
		if(this.rechercheContactParTel(contact) == -1) {
			this.contacts.add(contact);
			
			majContacts();
			
			
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
			
			majContacts();
			
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

		if(indice != -1) {
			this.contacts.get(indice).setNom(new_contact.getNom());
			this.contacts.get(indice).setTel(new_contact.getTel());
			
			majContacts();
			
			this.notifyObservers("Contact modifié");
			
			return true;
		}

		this.notifyObservers("Contact non-listé"); // non necessaire mais bon
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
	
	public void majContacts() {
		try {
			seriGest.enregistreContacts();
		} catch(Exception e) {
				System.out.println("exception = "+e);
		}
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
	
	public SerializeGestionnaire getSeriGest() {
		return this.seriGest;
	}
	public void setSeriGest(SerializeGestionnaire seriGest) {
		this.seriGest = seriGest;
	}



}
