import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerRecherche implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	
	public ControllerRecherche(GestionnaireDeContact gestionnaire, Vue vue) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Contact contact = new Contact(this.vue.getTxtfieldNom().getText(), this.vue.getTxtfieldTel().getText());
		int result = this.gestionnaire.rechercheContactParTel(contact);
		
		if(result == -1) {
			this.vue.getLabelResult().setText(contact.getTel() + " n'est pas connu");
		} else {
			this.vue.getLabelResult().setText(contact.getTel() + " appartient à "+ this.gestionnaire.getContacts().get(result).getNom());
		}
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
