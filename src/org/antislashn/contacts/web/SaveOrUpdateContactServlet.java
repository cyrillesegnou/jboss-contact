package org.antislashn.contacts.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antislashn.contacts.entities.Civilite;
import org.antislashn.contacts.entities.Contact;
import org.antislashn.contacts.services.ContactService;
import org.antislashn.utils.Constantes;

@WebServlet({ "/SaveOrUpdateContactServlet", "/SaveContact" })
public class SaveOrUpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB private ContactService service ;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		String id = request.getParameter("id");
		long idContact = 0;
		if(!id.equals("")) {
			idContact = Long.parseLong(id);
		}
		Contact contact = new Contact(Civilite.valueOf(civilite), nom, prenom);
		contact.setId(idContact);
		service.saveOrUpdateContact(contact);
		
		String page = "/AllContactsServlet";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
