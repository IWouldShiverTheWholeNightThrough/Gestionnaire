
import controller.ControllerAjout;
import controller.ControllerRecherche;
import daoImplementation.DaoImplementationModel;
import model.GestionnaireDeContact;
import model.Utilisateur;
import vue.Vue;

public class TestGestionnaire {

	public static void main(String[] args) {

		Utilisateur user = new Utilisateur("patrick");
		GestionnaireDeContact gestionnaire = user.getGestionnaire();
		DaoImplementationModel dao = user.getDao();
		Vue vue = new Vue();
		vue.lancerVue();
		
		dao.addObserver(vue.getPaneContact());
		dao.notifyObservers();

		ControllerAjout controllerAjout = new ControllerAjout(gestionnaire, vue, dao);
		vue.getButtonAjout().addActionListener(controllerAjout);

		ControllerRecherche controllerRecherche = new ControllerRecherche(gestionnaire, vue, dao);
		vue.getButtonRecherche().addActionListener(controllerRecherche);

		gestionnaire.addObserver(vue.getLabelResult());


		vue.getFrame().pack();
		vue.getFrame().setVisible(true);
		
	}
}
