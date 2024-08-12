/*
	Isabelly Barbosa Gonçalves CB3021467
	Lucas Aragão Almeida CB3013146
 */

import java.sql.*;
import java.util.*;

public class CustomerDAO {
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

	public void addCustomer(Customer customer) {
		final String query = "INSERT INTO customers (name, city, grade) VALUES (?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getCity());
			statement.setInt(3, customer.getGrade());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getAllCustomers() {
		final List<Customer> customers = new ArrayList<>();

		final String query = "SELECT * FROM customers";

		try (Connection conn = getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				customers.add(
						new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getInt("grade")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
	}

	public Customer getCustomer(int id) {
		final String query = "SELECT * FROM customers WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setInt(1, id);
			final ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("city"), rs.getInt("grade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updateCustomer(Customer customer) {
		final String query = "UPDATE customers SET name = ?, city = ?, grade = ? WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getCity());
			statement.setInt(3, customer.getGrade());
			statement.setInt(4, customer.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomer(int id) {
		final String query = "DELETE FROM customers WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
