/*
	Isabelly Barbosa Gonçalves CB3021467
	Lucas Aragão Almeida CB3013146
 */

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orders/*")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		orderDAO = new OrderDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();

		try {
			if (action == null)
				action = "/";

			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertOrder(request, response);
				break;
			case "/delete":
				deleteOrder(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateOrder(request, response);
				break;
			default:
				showAllOrders(request, response);
				break;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/add-order":
				insertOrder(request, response);
				break;
			case "/update-order":
				updateOrder(request, response);
				break;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showAllOrders(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		final List<Order> orders = orderDAO.getAllOrders();

		request.setAttribute("orders", orders);

		request.getRequestDispatcher("/order-list.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/order-form.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Order existingOrder = orderDAO.getOrder(id);

		request.setAttribute("order", existingOrder);
		request.getRequestDispatcher("/order-form.jsp").forward(request, response);

	}

	private void insertOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		float purchaseAmount = Float.parseFloat(request.getParameter("purchaseAmount"));
		int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		Order newOrder = new Order(purchaseAmount, salesmanId, customerId);
		orderDAO.addOrder(newOrder);

		response.sendRedirect("/SalesManagement/orders");
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		float purchaseAmount = Float.parseFloat(request.getParameter("purchaseAmount"));
		int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		Order order = new Order(id, purchaseAmount, salesmanId, customerId);
		orderDAO.updateOrder(order);

		response.sendRedirect("/SalesManagement/orders");
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		orderDAO.deleteOrder(id);

		response.sendRedirect("/SalesManagement/orders");

	}

}
