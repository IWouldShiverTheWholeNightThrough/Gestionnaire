package daoImplementation;

import java.util.List;
import java.util.Observable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.DaoModel;
import model.Contact;
import model.GestionnaireDeContact;

public class DaoImplementationModel extends Observable implements DaoModel {

	private GestionnaireDeContact gestionnaire;
	private SessionFactory factory;

	public DaoImplementationModel(GestionnaireDeContact gestionnaire) {
		this.gestionnaire = gestionnaire;

		try {
			this.factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Contact.class)
					.buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			List<Contact> contacts = session.createQuery("from Contact").getResultList();
			for(Contact contact: contacts) {
				this.gestionnaire.getContacts().add(contact);
			}
			session.getTransaction().commit();
			this.setChanged();
		} catch(Exception e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
	}

	@Override
	public boolean ajouterContact(Contact contact) {
		// TODO Auto-generated method stub
		try {

			Session session = this.factory.getCurrentSession();
			session.beginTransaction();
			session.save(contact);
			session.getTransaction().commit();
			this.setChanged();
			this.notifyObservers();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean supprimerContact(Contact contact) {
		// TODO Auto-generated method stub
		try {

			Session session = this.factory.getCurrentSession();
			session.beginTransaction();
			session.remove(contact);
			session.getTransaction().commit();
			this.setChanged();
			this.notifyObservers();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modifierContact(Contact contact) {
		// TODO Auto-generated method stub
		try {

			Session session = this.factory.getCurrentSession();
			session.beginTransaction();
			session.update(contact);
			session.getTransaction().commit();
			this.setChanged();
			this.notifyObservers();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void change() {
		this.setChanged();
	}

	public GestionnaireDeContact getGestionnaire() {
		return this.gestionnaire;
	}

	public void setGestionnaire(GestionnaireDeContact gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}



}
