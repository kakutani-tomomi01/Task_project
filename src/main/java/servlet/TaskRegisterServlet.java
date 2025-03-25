package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskRegisterBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskRegisterServlet
 */
@WebServlet("/TaskRegister")
public class TaskRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskDAO taskDao = new TaskDAO();
		try {
			List<CategoryBean> categoryList = taskDao.getCategoryList();
			List<StatusBean> statusList = taskDao.getStatusList();
			List<UserBean> userList = taskDao.getUserList();
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("statusList", statusList);
			request.setAttribute("userList", userList);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/task_register/task-register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "WEB-INF/task_register/task-register-failure.jsp";
		//ゲッパら
		String taskName = request.getParameter("taskName");
		int categoryId = Integer.parseInt(request.getParameter("category"));
		Date limitDate = Date.valueOf(request.getParameter("limitDate"));
		String userId = request.getParameter("user");
		String statusCode = request.getParameter("status");
		String memo = request.getParameter("memo");
		//beanに登録情報格納
		TaskRegisterBean task = new TaskRegisterBean();
		task.setTaskName(taskName);
		task.setCategoryId(categoryId);
		task.setLimitDate(limitDate);
		task.setUserId(userId);
		task.setStatusCode(statusCode);
		task.setMemo(memo);
		
		TaskDAO taskDao = new TaskDAO();
		try {
			int registCount = taskDao.taskRegister(task);
			if(registCount > 0) {
				url = "WEB-INF/task_register/task-register-success.jsp";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
