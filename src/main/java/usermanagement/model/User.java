package usermanagement.model;

public class User {
	private int id; 
	private String nom; 
	private String prenom; 
	private String tache;
	private String description;

	
	public User(String nom, String prenom, String tache, String description) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tache = tache;
		this.description = description;
		}
	
	public User(int id, String nom, String prenom, String tache, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.tache = tache;
		this.description = description;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTache() {
		return tache;
	}
	public void setTache(String tache) {
		this.tache = tache;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
	
}
