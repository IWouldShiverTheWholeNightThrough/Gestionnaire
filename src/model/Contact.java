package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name ="name")
	private String nom;
	
	@Column(name = "tel")
	private String tel;
	
	@Column(name = "photo")
	@Lob
	private byte[] image;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Utilisateur user;
	

	public Contact() {
	}
	
	public Contact(String nom, String tel) {
		this.nom = nom;
		this.tel = tel;
	}
	
	public String toString() {
		
		return "Nom: "+this.nom+" "+" Tél: " + this.tel;
	}
	
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getTel() {
		return this.tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
