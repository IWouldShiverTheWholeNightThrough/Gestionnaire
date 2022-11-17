package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerConnexion;

import daoImplementation.DaoImplUser;

import model.GestionnaireUtilisateurs;
import model.Utilisateur;

public class MonJPanelUser extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private GestionnaireUtilisateurs gestionnaire;
	private Vue vue;

	public MonJPanelUser(Vue vue) {
		super();
		this.vue = vue;
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		DaoImplUser dao = (DaoImplUser) o;

		this.removeAll();

		for(Utilisateur user: dao.getGestionnaire().getUsers()) {
			ContactJPanel pane = new ContactJPanel();

			JLabel label = new JLabel(user.getName());

			JButton buttonConnexion = new JButton("Se Connecter");
			ControllerConnexion controllerConnexion = new ControllerConnexion(dao.getGestionnaire(), this.vue, user);
			buttonConnexion.addActionListener(controllerConnexion);
			
			pane.add(label);
			pane.add(buttonConnexion);

			this.vue.getPaneUser().add(pane);
		}
		this.vue.getFrame().pack();
		this.vue.getFrame().setVisible(true);
	}
	public GestionnaireUtilisateurs getGestionnaire() {
		return gestionnaire;
	}
	public void setGestionnaire(GestionnaireUtilisateurs gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
	public Vue getVue() {
		return vue;
	}
	public void setVue(Vue vue) {
		this.vue = vue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
