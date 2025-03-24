package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginFromServlet
 */
@WebServlet("/login")
public class LoginFromServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFromServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストオブジェクトのエンコーディング方式を取得
		request.setCharacterEncoding("UTF-8");
		
		// リクエストパラメータの取得
		String usrId = request.getParameter("user_id");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		UserBean user = new UserBean();
		
		UserDAO dao = new UserDAO();
		
		if ("logincheck".equals(role)) {
			
		}
	}

}
