package model;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;

public class SerializeGestionnaire extends Observable{
	private String fichierDat;
	private String fichierTxt;
	private GestionnaireDeContact gestionnaire;

	public SerializeGestionnaire(String fichierDat, String fichierTxt) {
		this.fichierDat = fichierDat;
		this.fichierTxt = fichierTxt;
	}
	
	public void enregistreContacts() throws IOException {
		this.gestionnaire.afficheContacts();
		
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(this.fichierDat));
			for(Contact contact: this.gestionnaire.getContacts()) {
				out.writeObject(contact);
			}
		} catch(Exception e) {
			System.out.println("Exception = "+e);
		}
		out.close();
	}
	
	public ArrayList<Contact> deserializeContacts() throws IOException{
		
		
		ArrayList<Contact> liste = new ArrayList<Contact>();

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.fichierDat))) {
			while(true) {
				liste.add((Contact) in.readObject());
			}
		} catch(EOFException e) {
			
		} catch(FileNotFoundException e) {
			File file = new File(this.fichierDat);
			file.createNewFile();
		} catch(Exception e) {
			System.out.println("Exception = "+ e);	
		} 
		
		
		return liste;
	}
	
	public void ecrireTxt() throws IOException {
		
		this.setChanged();
		this.notifyObservers();
		
		PrintWriter out = new PrintWriter(new FileWriter(this.fichierTxt));
		
		try {
			for(Contact contact: this.gestionnaire.getContacts()) {
				out.println(contact);
			}

		} catch(Exception e) {
			System.out.println("exception = " + e);
		}

		out.close();
	}
	
	public String getFichierDat() {
		return this.fichierDat;
	}
	public void setFichierDat(String fichierDat) {
		this.fichierDat = fichierDat;
	}
	
	public String getFichierTxt() {
		return this.fichierTxt;
	}
	public void setFichierTxt(String fichierTxt) {
		this.fichierTxt = fichierTxt;
	}
	
	public GestionnaireDeContact getGestionnaire() {
		return this.gestionnaire;
	}
	public void setGestionnaire(GestionnaireDeContact gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
}
