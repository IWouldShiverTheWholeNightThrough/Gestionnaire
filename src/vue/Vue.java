package vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import controller.ControllerAjout;
import controller.ControllerAjoutUser;
import controller.ControllerParcourir;
import controller.ControllerRecherche;
import controller.ControllerRetour;
import controller.ControllerTxt;
import daoImplementation.DaoImplUser;
import daoImplementation.DaoImplementationModel;
import model.GestionnaireDeContact;
import model.GestionnaireUtilisateurs;

public class Vue {
	private JFrame frame;

	private JTextField txtfieldNom;
	private JTextField txtfieldTel;
	private JButton buttonAjout;
	private JButton buttonRecherche;
	private JButton buttonParcourir;
	private MonJLabel labelResult;
	private MonJPanel paneContact;
	private MonJPanelUser paneUser;
	private JPanel paneInput;
	private byte[] image;

	public void lancerVue(GestionnaireUtilisateurs gestionnaire, DaoImplUser dao) {
		this.frame = new JFrame("Gestionnaire de Contacts");
		this.frame.setLocation(500, 0);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;

		this.paneInput = new JPanel();
		this.paneInput.setLayout(new GridBagLayout());

		JLabel label = new JLabel("Bienvenue dans le Gestionnaire de Contacts");
		gBC.gridx = 0;
		gBC.gridy = 0;
		this.paneInput.add(label, gBC);

		this.txtfieldNom = new JTextField("Entrez un user");
		this.txtfieldNom.setColumns(20);
		gBC.insets = new Insets(10,10,10,10);
		gBC.gridx = 0;
		gBC.gridy = 1;
		this.paneInput.add(txtfieldNom, gBC);

		this.buttonAjout = new JButton("Créer un user");
		gBC.gridx = 1;
		gBC.gridy = 1;
		this.paneInput.add(buttonAjout, gBC);

		this.paneUser = new MonJPanelUser(this);
		this.paneUser.setLayout(new GridLayout(0,1));

		gBC.gridx = 0;
		gBC.gridy = 0;
		this.frame.getContentPane().add(this.paneInput, gBC);
		gBC.gridx = 0;
		gBC.gridy = 1;
		this.frame.getContentPane().add(this.paneUser, gBC);

		dao.addObserver(this.getPaneUser());
		dao.notifyObservers();

		ControllerAjoutUser controllerAjoutUser = new ControllerAjoutUser(gestionnaire, this, dao);
		this.getButtonAjout().addActionListener(controllerAjoutUser);
		
		ControllerTxt controllerTxt = new ControllerTxt(this);
		this.txtfieldNom.addMouseListener(controllerTxt);

		this.getFrame().pack();
		this.getFrame().setVisible(true);
	}


	public void lancerVueUser(GestionnaireDeContact gestionnaire, DaoImplementationModel dao) {

		this.frame.getContentPane().removeAll();

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

		JToggleButton retour = new JToggleButton();
		
		retour.setIcon(new ImageIcon("/home/bouddha/eclipse-workspace/Gestionnaire/src/retour.png"));
		retour.setBorderPainted(false);
		gBC.gridx = 0;
		gBC.gridy = 2;
		this.frame.getContentPane().add(retour, gBC);


		ControllerAjout controllerAjout = new ControllerAjout(gestionnaire, this, dao);
		this.getButtonAjout().addActionListener(controllerAjout);

		ControllerRecherche controllerRecherche = new ControllerRecherche(gestionnaire, this, dao);
		this.getButtonRecherche().addActionListener(controllerRecherche);

		ControllerParcourir controllerParcourir = new ControllerParcourir(gestionnaire, this, dao);
		this.getButtonParcourir().addActionListener(controllerParcourir);
		
		ControllerRetour controllerRetour = new ControllerRetour(this);
		retour.addMouseListener(controllerRetour);
		
		ControllerTxt controllerTxt = new ControllerTxt(this);
		this.txtfieldNom.addMouseListener(controllerTxt);
		this.txtfieldTel.addMouseListener(controllerTxt);

		gestionnaire.addObserver(this.getLabelResult());

		dao.addObserver(this.getPaneContact());
		dao.notifyObservers();


		this.getFrame().pack();
		this.getFrame().setVisible(true);
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

	public MonJPanelUser getPaneUser() {
		return paneUser;
	}

	public void setPaneUser(MonJPanelUser paneUser) {
		this.paneUser = paneUser;
	}
}
