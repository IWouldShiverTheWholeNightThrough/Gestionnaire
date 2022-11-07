package dao;

import model.Contact;

public interface DaoModel {
	public boolean ajouterContact(Contact contact);
	public boolean supprimerContact(Contact contact);
	public boolean modifierContact(Contact contact);
}
