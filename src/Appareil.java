
public class Appareil {
	private static GestionnaireDeContact gestionnaire;
	private String categorie;
	
	public Appareil(GestionnaireDeContact gestionnaire, String categorie) {
		Appareil.gestionnaire = gestionnaire;
		this.categorie = categorie;
	}
	
	public GestionnaireDeContact getGestionnaire() {
		return Appareil.gestionnaire;
	}
	
	public void setGestionnaire(GestionnaireDeContact gestionnaire) {
		Appareil.gestionnaire = gestionnaire;
	}	
	
	public String getCategorie() {
		return this.categorie;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}	
}
