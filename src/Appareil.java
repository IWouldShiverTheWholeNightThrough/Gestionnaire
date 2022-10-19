
public class Appareil {
	private GestionnaireDeContact gestionnaire;
	private String categorie;
	
	public Appareil() {
		
	}
	
	public Appareil(String categorie) {
		this.categorie = categorie;
	}
	
	public String toString() {
		return this.categorie;
	}
	
	public GestionnaireDeContact getGestionnaire() {
		return this.gestionnaire;
	}
	
	public void setGestionnaire(GestionnaireDeContact gestionnaire) {
		this.gestionnaire = gestionnaire;
	}	
	
	public String getCategorie() {
		return this.categorie;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}	
}
