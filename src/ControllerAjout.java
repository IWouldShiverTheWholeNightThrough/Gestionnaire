import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAjout implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	
	public ControllerAjout(GestionnaireDeContact gestionnaire, Vue vue) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Contact contact = new Contact(this.vue.getTxtfieldNom().getText(), this.vue.getTxtfieldTel().getText());
		if( gestionnaire.ajouteContact(contact) ) {
			this.vue.getLabelResult().setText("Contact ajouté");
		} else {
			this.vue.getLabelResult().setText("Contact déjà existant");
		}
		this.gestionnaire.afficheContacts();
	}
	
	public GestionnaireDeContact getGestionnaire() {
		return this.gestionnaire;
	}
	
	public void setGestionnaire(GestionnaireDeContact gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
	
	
	public Vue getVue() {
		return this.vue;
	}
	
	public void setVue(Vue vue) {
		this.vue = vue;
	}
	
}
