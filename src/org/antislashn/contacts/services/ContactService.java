package org.antislashn.contacts.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import org.antislashn.contacts.dao.ContactDAO;
import org.antislashn.contacts.dtos.ContactDTO;
import org.antislashn.contacts.entities.Adresse;
import org.antislashn.contacts.entities.Contact;

@Singleton
public class ContactService {
	@EJB private ContactDAO contactDao;
	
	public List<ContactDTO> getAllContactsDto(){
		List<Contact> contacts = contactDao.findAllContacts();
		List<ContactDTO> dtos = new ArrayList<ContactDTO>();
		for(Contact c : contacts) {
			dtos.add(new ContactDTO(c));
		}
		return dtos;		
	}
	
	public List<Adresse> getAdressesByContatId(long idContact){
		return contactDao.getAdresses(idContact);
	}
	
	public ContactDTO findByContactId(long idContact) {
		Contact contact = contactDao.findById(idContact);
		return new ContactDTO(contact);
	}

	public void saveOrUpdateContact(Contact contact) {
		if(contact.getId()==0) {
			contactDao.create(contact);
		}else {
			Contact c = contactDao.findById(contact.getId());
			c.setCivilite(contact.getCivilite());
			c.setNom(contact.getNom());
			c.setPrenom(contact.getPrenom());
			c.setImage(contact.getImage());
			contactDao.update(c);
		}
		
	}

	public void removeContact(long idContact) {
		contactDao.delete(idContact);
		
	}
}
