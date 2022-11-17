package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.GestionnaireUtilisateurs;
import vue.Vue;

public class ControllerRetour implements MouseListener {
	
	private Vue vue;
	
	public ControllerRetour(Vue vue) {
		this.vue = vue;
	}
       
    public void mousePressed(MouseEvent e) {
    this.vue.getFrame().dispose();
     new GestionnaireUtilisateurs(new Vue());
    }

    public void mouseReleased(MouseEvent e) {
    
    }

    public void mouseEntered(MouseEvent e) {
      
    }

    public void mouseExited(MouseEvent e) {
       
    }

    public void mouseClicked(MouseEvent e) {
    
    }
    
	
	
}
