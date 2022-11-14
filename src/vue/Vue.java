package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vue {
	private JFrame frame;

	private JTextField txtfieldNom;
	private JTextField txtfieldTel;
	private JButton buttonAjout;
	private JButton buttonRecherche;
	private JButton buttonParcourir;
	private MonJLabel labelResult;
	private MonJPanel paneContact;
	private JPanel paneInput;
	private byte[] image;

	public void lancerVue() {
		this.frame = new JFrame("Gestionnaire de Contacts");
		this.frame.setLocation(500, 0);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.frame.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		
		
		this.paneInput = new JPanel();
		this.paneInput.setLayout(new GridBagLayout());

		this.txtfieldNom = new JTextField("Entrez un nom");
		this.txtfieldNom.setColumns(20);
		gBC.insets = new Insets(10,10,10,10);
		gBC.gridx = 0;
		gBC.gridy = 0;
		this.paneInput.add(txtfieldNom, gBC);


		this.txtfieldTel = new JTextField("Entrez un tél");
		this.txtfieldTel.setColumns(20);
		gBC.gridx = 1;
		gBC.gridy = 0;
		this.paneInput.add(txtfieldTel, gBC);
		
		this.buttonParcourir = new JButton("Parcourir");
		gBC.gridx = 2;
		gBC.gridy = 0;
		this.paneInput.add(buttonParcourir, gBC);

		this.buttonAjout = new JButton("Ajouter un contact");
		gBC.gridx = 0;
		gBC.gridy = 1;
		this.paneInput.add(buttonAjout, gBC);

		this.buttonRecherche = new JButton("Rechercher le tél");
        gBC.gridx = 1;
        gBC.gridy = 1;
		this.paneInput.add(buttonRecherche, gBC);
		
		this.labelResult = new MonJLabel("Rien à afficher", this);
		gBC.gridx = 2;
        gBC.gridy = 1;
		this.paneInput.add(labelResult, gBC);

		this.paneContact = new MonJPanel(this);
		this.paneContact.setLayout(new GridLayout(0,1));
		
		gBC.gridx = 0;
		gBC.gridy = 0;
		this.frame.getContentPane().add(this.paneInput, gBC);
		gBC.gridx = 0;
		gBC.gridy = 1;
		this.frame.getContentPane().add(this.paneContact, gBC);
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
	
	public JPanel getPaneInput() {
		return this.paneInput;
	}
	public void setPaneInput(JPanel paneInput) {
		this.paneInput = paneInput;
	}

	public JButton getButtonParcourir() {
		return buttonParcourir;
	}

	public void setButtonParcourir(JButton buttonParcourir) {
		this.buttonParcourir = buttonParcourir;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	
	
}
