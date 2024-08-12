/*
	Isabelly Barbosa Gonçalves CB3021467
	Lucas Aragão Almeida CB3013146
 */

import java.sql.*;
import java.util.*;

public class OrderDAO {
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

	public void addOrder(Order order) {
		final String query = "INSERT INTO orders (purchase_amount, salesman_id, customer_id) VALUES (?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setFloat(1, order.getPurchaseAmount());
			statement.setInt(2, order.getSalesmanId());
			statement.setInt(3, order.getCustomerId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Order> getAllOrders() {
		final List<Order> orders = new ArrayList<>();
		final String query = "SELECT * FROM orders";

		try (Connection conn = getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(query)) {
			while (rs.next()) {
				orders.add(new Order(rs.getInt("id"), rs.getFloat("purchase_amount"),
						rs.getTimestamp("created_at").toLocalDateTime(), rs.getInt("salesman_id"),
						rs.getInt("customer_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public Order getOrder(int id) {
		final String query = "SELECT * FROM orders WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setInt(1, id);
			final ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Order(rs.getInt("id"), rs.getFloat("purchase_amount"),
						rs.getTimestamp("created_at").toLocalDateTime(), rs.getInt("salesman_id"),
						rs.getInt("customer_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updateOrder(Order order) {
		final String query = "UPDATE orders SET purchase_amount = ?, salesman_id = ?, customer_id = ? WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setFloat(1, order.getPurchaseAmount());
			statement.setFloat(2, order.getSalesmanId());
			statement.setFloat(3, order.getCustomerId());
			statement.setInt(4, order.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOrder(int id) {
		final String query = "DELETE FROM orders WHERE id = ?";

		try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
