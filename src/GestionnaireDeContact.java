import java.util.ArrayList;

public class GestionnaireDeContact{

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

		if(this.contacts.size() == 0) {
			System.out.println("Pas de contacts dans votre répertoire.");
		} else {
			System.out.println("Liste des contacts:\n");
			for(int i = 0;i< this.contacts.size(); i++) {
				System.out.print("-> ");
				System.out.println(contacts.get(i));
			}
		}
		
		try {
		seriGest.ecrireTxt();
		} catch(Exception e) {
			System.out.println("exception = "+e);
		}
	}

	public boolean ajouteContact(Contact contact) {
		if(this.rechercheContactParTel(contact) == -1) {
			this.contacts.add(contact);
			
			majContacts();
			
			return true;
		}

		return false;		
	}

	public boolean supprimeContact(Contact contact) {
		int indice = rechercheContactParTel(contact);
		if(indice != -1) {
			this.contacts.remove(this.contacts.get(indice));
			
			majContacts();
			
			return true;
		} else {
			return false;
		}
	}

	public boolean modifieContact(Contact contact, Contact new_contact) {

		int indice = rechercheContactParTel(contact);

		if(indice != -1) {
			this.contacts.get(indice).setNom(new_contact.getNom());
			this.contacts.get(indice).setTel(new_contact.getTel());
			
			majContacts();
			
			return true;
		}

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
