package vue;

import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import controller.ControllerModif;
import controller.ControllerSuppression;

import daoImplementation.DaoImplementationModel;
import model.Contact;

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
		
		DaoImplementationModel dao = (DaoImplementationModel) o;
		
		this.removeAll();
		
		for(Contact contact: dao.getGestionnaire().getContacts()) {
			ContactJPanel pane = new ContactJPanel();
	
			JLabel label = new JLabel(contact.toString());
			JButton buttonSupp = new JButton("Supprimer");
			ControllerSuppression controllerSuppression = new ControllerSuppression(dao.getGestionnaire(), this.vue, dao);
			buttonSupp.addActionListener(controllerSuppression);
			
			JButton buttonModif = new JButton("Modifier");
			ControllerModif controllerModif = new ControllerModif(dao.getGestionnaire(), this.vue, dao);
			buttonModif.addActionListener(controllerModif);
			
			
			pane.add(label);
			pane.add(buttonSupp);
			pane.add(buttonModif);
			
			JLabel picLabel = null;
			try {
				
				if(contact.getImage() != null) {
					picLabel = new JLabel(new javax.swing.ImageIcon(ImageIO.read(new java.io.ByteArrayInputStream(contact.getImage()))));
					pane.add(picLabel);
				}
					
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			this.vue.getPaneContact().add(pane);
		}
		this.vue.getFrame().pack();
		this.vue.getFrame().setVisible(true);
		
	}
	
	public Vue getVue() {
		return this.vue;
	}
	
	public void setVue(Vue vue) {
		this.vue = vue;
	}
	
}
