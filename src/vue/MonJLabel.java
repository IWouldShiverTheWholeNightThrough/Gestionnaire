package vue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class MonJLabel extends JLabel implements Observer{

	private static final long serialVersionUID = 1L;
	private Vue vue;
	
	public MonJLabel(String texte, Vue vue) {
		super(texte);
		this.vue = vue;
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.setText((String) arg);
		this.vue.getFrame().pack();		
	}
	
	public Vue getVue() {
		return this.vue;
	}
	
	public void setVue(Vue vue) {
		this.vue = vue;
	}
}
