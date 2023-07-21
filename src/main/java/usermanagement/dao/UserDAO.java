package usermanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import usermanagement.model.*;


public class UserDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost/javabdd";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_USER_SQL = "INSERT INTO user" + "(nom, prenom, tache, description) VALUES"
 + "(?, ?, ?);";
	
	private static final String SELECT_USER_BY_ID = "select id,nom,prenom,tache,description from user where id = ? ";
	private static final String SELECT_ALL_USER = "select * from user";
	private static final String DELETE_USER_SQL = "delete from user where id = ?";
	private static final String UPDATE_USER_SQL= "update user set nom = ?, prenom = ?, tache = ?, description = ? where id = ?";

			
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername, jdbcPassword);
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	// CREATE A USER
	
	public void insertUser(User user) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)){
			preparedStatement.setString(1, user.getNom());
			preparedStatement.setString(2, user.getPrenom());
			preparedStatement.setString(3, user.getTache());
			preparedStatement.setString(4, user.getDescription());
		
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// UPDATE A USER
	
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)){
			statement.setString(1, user.getNom());
			statement.setString(2, user.getPrenom());
			statement.setString(3, user.getTache());
			statement.setString(4, user.getDescription());
			statement.setInt(5, user.getId());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		
		return rowUpdated;
	}
	
	//SELECT a USER by id 
	
	public User selectUser(int id) throws SQLException{
		User user = null;
		
		// Step established the connection 
		try (Connection connection = getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(SELECT_USER_BY_ID);){
			preparedstatement.setInt(1, id);
			System.out.println(preparedstatement);
			
		// Step Update the query +
			ResultSet res = preparedstatement.executeQuery();
			
		// Step Process the Result 
			while(res.next()) {
				String nom = res.getString("nom");
				String prenom = res.getString("prenom");
				String tache = res.getString("tache");
				String description = res.getString("description");
				user = new User(id, nom, prenom, tache, description);
				
			}	
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
		
	}
	
	//SELECT USER 
	
	public List<User> selectAllUser() throws SQLException{
		List<User> users = new ArrayList<>();
		
		// Step established the connection 
		try (Connection connection = getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(SELECT_ALL_USER);){
			System.out.println(preparedstatement);
			
		// Step Update the query 
			ResultSet res = preparedstatement.executeQuery();
			
		// Step Process the Result 
			while(res.next()) {
				int id = res.getInt("id");
				String nom = res.getString("nom");
				String prenom = res.getString("prenom");
				String tache = res.getString("tache");
				String description = res.getString("descritpion");
				users.add(new User(id, nom, prenom, tache, description));
				
			}	
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	//DELETE USER
	public boolean deleteUser(int id) throws SQLException{
		boolean rowDeleted;
		try  (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);){
			statement.setInt(1, id);		
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}	
}
