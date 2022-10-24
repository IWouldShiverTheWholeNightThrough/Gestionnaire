import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ControllerSuppression implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	
	public ControllerSuppression(GestionnaireDeContact gestionnaire, Vue vue) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i =0;i<this.vue.getPaneContact().getComponentCount();i++) {
			if (e.getSource() == ((JPanel) this.vue.getPaneContact().getComponent(i)).getComponent(1) ) {
				if( this.gestionnaire.supprimeContact(this.gestionnaire.getContacts().get(i)) ) {
					this.vue.getLabelResult().setText("Contact supprimé");
				} else {
					this.vue.getLabelResult().setText("Contact non-listé"); // un peu con non ?
				}
			}
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
