import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControllerActualisation implements ActionListener {

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	
	public ControllerActualisation(GestionnaireDeContact gestionnaire, Vue vue) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.vue.getPaneContact().removeAll();
		
		for(Contact contact: this.gestionnaire.getContacts()) {
			JPanel pane = new JPanel();
			JLabel label = new JLabel(contact.toString());
			JButton buttonSupp = new JButton("Supprimer");
			ControllerSuppression controllerSuppression = new ControllerSuppression(this.gestionnaire, this.vue);
			buttonSupp.addActionListener(controllerSuppression);
			
			JButton buttonModif = new JButton("Modifier");
			ControllerModif controllerModif = new ControllerModif(this.gestionnaire, this.vue);
			buttonModif.addActionListener(controllerModif);
			pane.add(label);
			pane.add(buttonSupp);
			pane.add(buttonModif);
			this.vue.getPaneContact().add(pane);
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
