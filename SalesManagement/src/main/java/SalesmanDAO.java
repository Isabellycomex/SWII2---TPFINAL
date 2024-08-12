/*
	Isabelly Barbosa Gonçalves CB3021467
	Lucas Aragão Almeida CB3013146
 */

import java.sql.*;
import java.util.*;

public class SalesmanDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/SalesManagement";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345678";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to load MySQL driver", e);
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public void addSalesman(Salesman salesman) {
		final String query = "INSERT INTO salesmen (name, city, commission) VALUES (?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, salesman.getName());
			statement.setString(2, salesman.getCity());
			statement.setFloat(3, salesman.getCommission());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Salesman> getAllSalesmen() {
		final List<Salesman> salesmen = new ArrayList<>();
		final String query = "SELECT * FROM salesmen";

		try (Connection conn = getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				salesmen.add(
						new Salesman(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getInt("commission")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salesmen;
	}

	public Salesman getSalesman(int id) {
		final String query = "SELECT * FROM salesmen WHERE id = ?";
		
		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setInt(1, id);
			final ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new Salesman(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getInt("commission"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void updateSalesman(Salesman salesman) {
		final String query = "UPDATE salesmen SET name = ?, city = ?, commission = ? WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, salesman.getName());
			statement.setString(2, salesman.getCity());
			statement.setFloat(3, salesman.getCommission());
			statement.setInt(4, salesman.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSalesman(int id) {
		final String query = "DELETE FROM salesmen WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
