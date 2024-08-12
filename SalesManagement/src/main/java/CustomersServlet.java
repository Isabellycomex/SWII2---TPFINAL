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

@WebServlet("/customers/*")
public class CustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		customerDAO = new CustomerDAO();
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
				insertCustomer(request, response);
				break;
			case "/delete":
				deleteCustomer(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCustomer(request, response);
				break;
			default:
				showAllCustomers(request, response);
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
			case "/add-customer":
				insertCustomer(request, response);
				break;
			case "/update-customer":
				updateCustomer(request, response);
				break;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showAllCustomers(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		final List<Customer> customers = customerDAO.getAllCustomers();

		request.setAttribute("customers", customers);

		request.getRequestDispatcher("/customer-list.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/customer-form.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Customer existingCustomer = customerDAO.getCustomer(id);

		request.setAttribute("customer", existingCustomer);
		request.getRequestDispatcher("/customer-form.jsp").forward(request, response);

	}

	private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		int grade = Integer.parseInt(request.getParameter("grade"));

		Customer newCustomer = new Customer(name, city, grade);
		customerDAO.addCustomer(newCustomer);

		response.sendRedirect("/SalesManagement/customers");
	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		int grade = Integer.parseInt(request.getParameter("grade"));

		Customer customer = new Customer(id, name, city, grade);
		customerDAO.updateCustomer(customer);

		response.sendRedirect("/SalesManagement/customers");
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		customerDAO.deleteCustomer(id);

		response.sendRedirect("/SalesManagement/customers");

	}

}
