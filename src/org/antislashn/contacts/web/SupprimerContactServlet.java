package org.antislashn.contacts.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antislashn.contacts.services.ContactService;
import org.antislashn.utils.Constantes;
import org.antislashn.utils.Util;
/**
 * Servlet implementation class SupprimerContactServlet
 */
@WebServlet({ "/SupprimerContactServlet", "/SupprimerContact" })
public class SupprimerContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB private ContactService service ;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(Util.isLong(id)) {
			long idContact = Long.parseLong(id);
			service.removeContact(idContact);
		}
		String page = "/AllContactsServlet";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(page);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
