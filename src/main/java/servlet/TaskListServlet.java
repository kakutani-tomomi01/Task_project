package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskDAO;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskListServlet
 */
@WebServlet("/tasklist")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskListServlet() {
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
		//  リクエストオブジェクトのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		// リクエストパラメータの取得
		String role = request.getParameter("role");
		
		TaskBean task = new TaskBean();
		List<TaskBean> taskList = null;
		
		// 転送先のurl設定
		String url = "login-failure.html";
		
		TaskDAO dao = new TaskDAO();
		
		if ("taskList".equals(role)) {
			try {
				taskList = dao.getTaskListAll();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			if (Objects.isNull(taskList)) {
				request.setAttribute("taskList", taskList);
				
				url = "task-list.jsp";
			}
		}
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
