package org.antislashn.contacts.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "personnes")
@SecondaryTable(name ="avatars", pkJoinColumns = @PrimaryKeyJoinColumn(name= "fk_personne"))
@NamedQueries({
	@NamedQuery(name = "Contact.getByNom",query = "SELECT c FROM Contact c WHERE c.nom LIKE :nom"),
	@NamedQuery(name = "Contact.getWithAddress",query="SELECT c FROM Contact c WHERE c.adresses IS NOT EMPTY"),
	@NamedQuery(name= "Contact.getAll", query = "SELECT c FROM Contact c")
})
public class Contact implements Serializable {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk")
	private long id;
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
	private String nom;
	private String prenom;
	@ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "contacts_adresses",
			joinColumns = @JoinColumn(name="fk_personne"),
			inverseJoinColumns = @JoinColumn(name="fk_adresse"))
	private List<Adresse> adresses = new ArrayList<>();

	@Column(table = "avatars", name = "avatar")
	private String image;
	public Contact() {}
	
	public Contact(Civilite civilite, String nom, String prenom) {
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public void addAdresse(Adresse adresse) {
		adresses.add(adresse);
	}
	
	public void removeAdresse(Adresse adresse) {
		Iterator<Adresse> it = adresses.iterator();
		while(it.hasNext()) {
			Adresse a = it.next();
			if(a.getId() == adresse.getId()) {
				it.remove();
			}
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Civilite getCivilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
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

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
