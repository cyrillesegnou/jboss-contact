package org.antislashn.contacts.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.antislashn.contacts.entities.Adresse;
import org.antislashn.contacts.entities.Civilite;
import org.antislashn.contacts.entities.Contact;

@Singleton
public class ContactDAO extends AbstractDAO<Contact, Long>{
	@PersistenceContext(name="contacts") private EntityManager em;
	public ContactDAO() {
		super(Contact.class);
		
	}
	public List<Contact> getContactsByCivilite(Civilite civilite){
		String sql = "SELECT c FROM Contact c WHERE c.civilite= :foo";
		
		List<Contact> contacts = em.createQuery(sql, Contact.class)
										.setParameter("foo", civilite)
										.getResultList();
		return contacts;
	}
	public List<Contact> getContactsByNom(String nom){
		
		List<Contact> contacts = em.createNamedQuery("Contact.getByNom", Contact.class)
										.setParameter("nom", nom+"%")
										.getResultList();
		return contacts;
	}
	public List<Contact> findAllContacts() {
		
		List<Contact> contacts = em.createNamedQuery("Contact.getAll", Contact.class)
										.getResultList();
		return contacts;
	}
	public List<Adresse> getAdresses(long idContact) {
		
		Contact contact = em.find(Contact.class, idContact);
		contact.getAdresses().size();
		
		return contact.getAdresses();
	}
	@Override
	protected EntityManager getEntityManager() {	
		return em;
	}
}