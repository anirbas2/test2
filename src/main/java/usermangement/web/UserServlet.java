package usermangement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import usermanagement.dao.*;
import usermanagement.model.*;
/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
    	this.userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertUser(request, response);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			// List
			try {
				listUser(request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, reponse);
	} 
	
	private void insertUser(HttpServletRequest request, HttpServletResponse reponse) throws IOException, SQLException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tache = request.getParameter("tache");
		String description = request.getParameter("description");
		User newUser = new User(nom, prenom, tache, description);
		userDAO.insertUser(newUser);
		reponse.sendRedirect("list");	
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse reponse)throws IOException, SQLException{
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		reponse.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse reponse) throws SQLException, ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, reponse);	
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse reponse) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tache = request.getParameter("tache");	
		String description = request.getParameter("description");	
		User book = new User(id, nom, prenom, tache, description);
		userDAO.updateUser(book);
		reponse.sendRedirect("list");
	}
		
	private void listUser(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException, SQLException {
		List<User> listUser = userDAO.selectAllUser();
		request.setAttribute("listUser",listUser); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, reponse);	
		
	}
}