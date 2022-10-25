import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonJPanel extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	private Vue vue;
	
	public MonJPanel(Vue vue) {
		super();
		this.vue = vue;
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		SerializeGestionnaire seriGest = (SerializeGestionnaire) o;
		this.removeAll();
		
		for(Contact contact: seriGest.getGestionnaire().getContacts()) {
			JPanel pane = new JPanel();
			JLabel label = new JLabel(contact.toString());
			JButton buttonSupp = new JButton("Supprimer");
			ControllerSuppression controllerSuppression = new ControllerSuppression(seriGest.getGestionnaire(), this.vue);
			buttonSupp.addActionListener(controllerSuppression);
			
			JButton buttonModif = new JButton("Modifier");
			ControllerModif controllerModif = new ControllerModif(seriGest.getGestionnaire(), this.vue);
			buttonModif.addActionListener(controllerModif);
			pane.add(label);
			pane.add(buttonSupp);
			pane.add(buttonModif);
			this.vue.getPaneContact().add(pane);
		}
	
		this.vue.getFrame().setVisible(true);
	}
	
	public Vue getVue() {
		return this.vue;
	}
	
	public void setVue(Vue vue) {
		this.vue = vue;
	}
	
}
