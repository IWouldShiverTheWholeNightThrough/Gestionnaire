import java.util.ArrayList;

public class GestionnaireUtilisateurs {
	private ArrayList<Utilisateur> users;
	
	public GestionnaireUtilisateurs() {
		this.users = new ArrayList<Utilisateur>();
	}
	
	public void afficheUsers() {

		if(this.users.size() == 0) {
			System.out.println("Pas de users enregistrés");
		} else {
			System.out.println("Liste des Users:\n");
			for(int i = 0;i< this.users.size(); i++) {
				System.out.print("-> ");
				System.out.println(users.get(i));
			}
		}
		System.out.println();
	}
	
	public boolean creerUser(Utilisateur user) {
		if(this.rechercheUserparId(user) == -1) {
			this.users.add(user);
			return true;
		}

		return false;		
	}
	
	public int rechercheUserparId(Utilisateur user) {
		
		int n = this.users.size();
		int i = 0;
		int indice = -1;
		while(indice == -1 && i<n) {
			if(this.users.get(i).getId().equals(user.getId())) {
				indice = i;
				break;
			}
			i++;
		}
		return indice;
	}
	
	public ArrayList<Utilisateur> getUsers() {
		return this.users;
	}
	public void setUsers(ArrayList<Utilisateur> users) {
		this.users = users;
	}
}
