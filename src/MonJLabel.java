import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class MonJLabel extends JLabel implements Observer{

	private static final long serialVersionUID = 1L;
	
	public MonJLabel(String texte) {
		super(texte);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.setText((String) arg);
	}
}
