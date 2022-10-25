import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vue {
	private JFrame frame;
	
	private JTextField txtfieldNom;
	private JTextField txtfieldTel;
	private JButton buttonAjout;
	private JButton buttonRecherche;
	private MonJLabel labelResult;
	private MonJPanel paneContact;

	public void lancerVue() {
		this.frame = new JFrame("Gestionnaire de Contacts");
		this.frame.setLayout(new FlowLayout());
		this.frame.setSize(300,300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.txtfieldNom = new JTextField("Entrez un nom");
		this.txtfieldNom.setColumns(20);
		this.txtfieldTel = new JTextField("Entrez un tél");
		this.txtfieldTel.setColumns(20);
		this.buttonAjout = new JButton("Ajouter un contact");
		this.buttonRecherche = new JButton("Rechercher le tél");
		this.labelResult = new MonJLabel("Rien à afficher");
		
		this.paneContact = new MonJPanel(this);
		this.paneContact.setSize(300,300);
		this.paneContact.setBorder(BorderFactory.createLineBorder(Color.black));

		this.frame.getContentPane().add(paneContact);
		this.frame.getContentPane().add(txtfieldNom);
		this.frame.getContentPane().add(txtfieldTel);
		this.frame.getContentPane().add(buttonAjout);
		this.frame.getContentPane().add(buttonRecherche);
		this.frame.getContentPane().add(labelResult);
		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public JTextField getTxtfieldNom() {
		return this.txtfieldNom;
	}
	public void setTxtfieldNom(JTextField txtfield) {
		this.txtfieldNom = txtfield;
	}
	
	public JTextField getTxtfieldTel() {
		return this.txtfieldTel;
	}
	public void setTxtfieldTel(JTextField txtfield) {
		this.txtfieldTel = txtfield;
	}
	
	public JButton getButtonAjout() {
		return this.buttonAjout;
	}
	public void setButtonAjout(JButton button) {
		this.buttonAjout = button;
	}
	
	public JButton getButtonRecherche() {
		return this.buttonRecherche;
	}
	public void setButtonRecherche(JButton buttonRecherche) {
		this.buttonRecherche = buttonRecherche;
	}
	
	public MonJLabel getLabelResult() {
		return this.labelResult;
	}
	public void setLabelResult(MonJLabel labelResult) {
		this.labelResult = labelResult;
	}
	
	
	public MonJPanel getPaneContact() {
		return this.paneContact;
	}
	public void setPaneContact(MonJPanel paneContact) {
		this.paneContact = paneContact;
	}
	
}
