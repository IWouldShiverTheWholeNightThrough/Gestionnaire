package daoImplementation;

import java.util.List;
import java.util.Observable;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.DaoUser;
import model.Contact;

import model.GestionnaireUtilisateurs;
import model.Utilisateur;

public class DaoImplUser extends Observable implements DaoUser {


	private GestionnaireUtilisateurs gestionnaire;
	private SessionFactory factory;

	public DaoImplUser(GestionnaireUtilisateurs gestionnaire) {
		this.gestionnaire = gestionnaire;

		try {
			this.factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Contact.class)
					.addAnnotatedClass(Utilisateur.class)
					.buildSessionFactory();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("select u from Utilisateur u", Utilisateur.class);
			
			List<Utilisateur> users = query.getResultList();
			for(Utilisateur u: users) {
				u.setGestionnaireUser(this.gestionnaire);
				this.gestionnaire.getUsers().add(u);
			}
			
			session.getTransaction().commit();
			this.setChanged();
		} catch(Exception e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
	}

	@Override
	public boolean ajouterUser(Utilisateur user) {
		// TODO Auto-generated method stub
		try {

			Session session = this.factory.getCurrentSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			this.setChanged();
			this.notifyObservers();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public GestionnaireUtilisateurs getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(GestionnaireUtilisateurs gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
}
