package org.antislashn.contacts.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.antislashn.contacts.entities.Civilite;
import org.antislashn.contacts.entities.Contact;
import org.antislashn.contacts.services.ContactService;
import org.antislashn.utils.Constantes;



/**
 * Servlet implementation class MultiPartFormDataServlet
 */
@WebServlet("/MultiPartFormDataServlet")
@MultipartConfig() 
public class MultiPartFormDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger(MultiPartFormDataServlet.class.getCanonicalName());
    @EJB private ContactService service ;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Logger.info(">>>Reception du fichier");
		String folder = getServletContext().getInitParameter("upload-folder");
		// getParameter fonctionne en enctype="multipart/form-data" grace à l'annotation @MultipartConfig
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String civilite = request.getParameter("civilite");
		String id = request.getParameter("id");
		long idContact = 0;
		if(!id.equals("")) {
			idContact = Long.parseLong(id);
		}
		Contact contact = new Contact(Civilite.valueOf(civilite), nom, prenom);
	
		final Part filePart = request.getPart("avatar");
		final String fileName = getFileName(filePart);
		
		//Logger.info ("nom du fichier recu : " +fileName );
				
		contact.setImage(fileName);
		service.saveOrUpdateContact(contact);
		
		// Depuis Java 7
		// copie le fichier reçu vers son emplacemen définitif
		Path path = FileSystems.getDefault().getPath(folder, fileName);
		InputStream in = filePart.getInputStream();
		Files.copy(in, path);
		in.close();
		
		// pour supprimer le fichier temporaire
		filePart.delete();
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}  
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public MultiPartFormDataServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//}