package controller;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import daoImplementation.DaoImplementationModel;
import model.Contact;
import model.GestionnaireDeContact;
import vue.Vue;

public class ControllerParcourir implements ActionListener{

	private GestionnaireDeContact gestionnaire;
	private Vue vue;
	private DaoImplementationModel dao;

	public ControllerParcourir(GestionnaireDeContact gestionnaire, Vue vue, DaoImplementationModel dao) {
		this.gestionnaire = gestionnaire;
		this.vue = vue;
		this.dao = dao;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Component parent = null;

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG, PNG & GIF Images", "jpg", "gif", "png");

		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("Fichier sélectionné: " +
					chooser.getSelectedFile().getName());
		}

		File file = chooser.getSelectedFile();


		try {

			String[] tab = chooser.getSelectedFile().getName().split("\\.");
			String type = tab[tab.length -1];
			
			BufferedImage image = ImageIO.read(file); 
			image = resize(image, 40);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, type, baos);
			byte[] bytes = baos.toByteArray();
			this.vue.setImage(bytes);
			this.vue.getLabelResult().setText("Fichier sélectionné");
		} catch(Exception exp) {
			exp.printStackTrace();
		}
	}


	private static BufferedImage resize(BufferedImage src, int targetSize) {
		if (targetSize <= 0) {
			return src; //this can't be resized
		}
		int targetWidth = targetSize;
		int targetHeight = targetSize;
		float ratio = ((float) src.getHeight() / (float) src.getWidth());
		if (ratio <= 1) { //square or landscape-oriented image
			targetHeight = (int) Math.ceil((float) targetWidth * ratio);
		} else { //portrait image
			targetWidth = Math.round((float) targetHeight / ratio);
		}
		BufferedImage bi = new BufferedImage(targetWidth, targetHeight, src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(src, 0, 0, targetWidth, targetHeight, null);
		g2d.dispose();
		return bi;
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

	public DaoImplementationModel getDao() {
		return dao;
	}

	public void setDao(DaoImplementationModel dao) {
		this.dao = dao;
	}



}
