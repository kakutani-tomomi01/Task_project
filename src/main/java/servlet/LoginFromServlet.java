package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		// 転送先のurl設定
		String url = "login-failure.html";
		
		UserBean user = new UserBean();
		
		UserDAO dao = new UserDAO();
		
		if ("logincheck".equals(role)) {
			try {
				user = dao.selectUser(userId, password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			if (userId.equals(user.getUserId())) {
				url = "menu.jsp";
				
				// セッションオブジェクトの取得
				HttpSession session = request.getSession();
				
				// セッションスコープへの値の設定
				session.setAttribute("user", user);
			}
		}
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
