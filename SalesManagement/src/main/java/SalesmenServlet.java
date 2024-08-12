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

@WebServlet("/salesmen/*")
public class SalesmenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalesmanDAO salesmanDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		salesmanDAO = new SalesmanDAO();
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
				insertSalesman(request, response);
				break;
			case "/delete":
				deleteSalesman(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateSalesman(request, response);
				break;
			default:
				showAllSalesmen(request, response);
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
			case "/add-salesman":
				insertSalesman(request, response);
				break;
			case "/update-salesman":
				updateSalesman(request, response);
				break;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showAllSalesmen(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		final List<Salesman> salesmen = salesmanDAO.getAllSalesmen();

		request.setAttribute("salesmen", salesmen);

		request.getRequestDispatcher("/salesman-list.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/salesman-form.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Salesman existingSalesman = salesmanDAO.getSalesman(id);

		request.setAttribute("salesman", existingSalesman);
		request.getRequestDispatcher("/salesman-form.jsp").forward(request, response);

	}

	private void insertSalesman(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		float commission = Float.parseFloat(request.getParameter("commission"));

		Salesman newSalesman = new Salesman(name, city, commission);
		salesmanDAO.addSalesman(newSalesman);

		response.sendRedirect("/SalesManagement/salesmen");
	}

	private void updateSalesman(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		float commission = Float.parseFloat(request.getParameter("commission"));

		Salesman salesman = new Salesman(id, name, city, commission);
		salesmanDAO.updateSalesman(salesman);

		response.sendRedirect("/SalesManagement/salesmen");
	}

	private void deleteSalesman(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		salesmanDAO.deleteSalesman(id);

		response.sendRedirect("/SalesManagement/salesmen");

	}

}
