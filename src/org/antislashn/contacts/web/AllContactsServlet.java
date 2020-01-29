package org.antislashn.contacts.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antislashn.contacts.dao.ContactDAO;
import org.antislashn.contacts.dtos.ContactDTO;
import org.antislashn.contacts.entities.Contact;
import org.antislashn.contacts.services.ContactService;
import org.antislashn.utils.Constantes;


@WebServlet(value = "/AllContactsServlet")
public class AllContactsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AllContactsServlet.class.getName());
    @EJB private ContactService service ;
    
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				String page = "/show-contacts.jsp";
		List<ContactDTO> contacts = service.getAllContactsDto();
		request.setAttribute("contacts", contacts);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
