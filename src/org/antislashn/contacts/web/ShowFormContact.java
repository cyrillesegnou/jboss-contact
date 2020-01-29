package org.antislashn.contacts.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antislashn.contacts.dtos.ContactDTO;
import org.antislashn.contacts.services.ContactService;
import org.antislashn.utils.Constantes;

/**
 * Servlet implementation class ShowFormContact
 */
@WebServlet("/ShowFormContact")
public class ShowFormContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB private ContactService service ;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String page = "/contact-form.jsp";
		long id = Long.parseLong(request.getParameter("id"));
		ContactDTO contact = service.findByContactId(id);
		request.setAttribute("contact", contact);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
