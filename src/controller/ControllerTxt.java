package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import vue.Vue;

public class ControllerTxt implements MouseListener{

	private Vue vue;

	public ControllerTxt(Vue vue) {
		this.vue = vue;
	}

	public void mousePressed(MouseEvent e) {
		if(e.getSource() == this.vue.getTxtfieldNom()) {
			this.vue.getTxtfieldNom().setText("");
		} else {
			this.vue.getTxtfieldTel().setText("");
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public Vue getVue() {
		return vue;
	}

	public void setVue(Vue vue) {
		this.vue = vue;
	}


}


