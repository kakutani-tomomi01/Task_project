package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.TaskRegisterBean;
import model.entity.UserBean;

public class TaskDAO {
	/**
	 * カテゴリの一覧取得
	 * @return カテゴリの一覧
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<CategoryBean> getCategoryList() throws SQLException, ClassNotFoundException {
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		String sql = "SELECT category_id,category_name FROM m_category";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				CategoryBean category = new CategoryBean();
				category.setCategoryId(res.getInt("category_id"));
				category.setCategoryName(res.getString("category_name"));
				categoryList.add(category);
			}
		}
		return categoryList;
	}

	/**
	 * タスク一覧の取得
	 * @return タスク一覧
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TaskBean> getTaskListAll() throws ClassNotFoundException, SQLException {
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "SELECT t1.task_id, t1.task_name, t1.category_id, t2.category_name, t1.limit_date, t1.user_id, t3.user_name, t1.status_code, t4.status_name, t1.memo FROM t_task t1 INNER JOIN m_category t2 ON t1.category_id = t2.category_id INNER JOIN m_user t3 ON t1.user_id = t3.user_id INNER JOIN m_status t4 ON t1.status_code = t4.status_code ORDER BY task_id asc";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				TaskBean task = new TaskBean();
				task.setTaskId(res.getInt("t1.task_id"));
				task.setTaskName(res.getString("t1.task_name"));
				task.setCategoryId(res.getInt("t1.category_id"));
				task.setCategoryName(res.getString("t2.category_name"));
				task.setLimitDate(res.getDate("t1.limit_date"));
				task.setUserId(res.getString("t1.user_id"));
				task.setUserName(res.getString("t3.user_name"));
				task.setStatusCode(res.getString("t1.status_code"));
				task.setStatusName(res.getString("t4.status_name"));
				task.setMemo(res.getString("t1.memo"));

				taskList.add(task);
			}
		}

		return taskList;
	}

	/**
	 * 
	 * @return ステータス一覧の取得
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<StatusBean> getStatusList() throws SQLException, ClassNotFoundException {
		List<StatusBean> statusList = new ArrayList<StatusBean>();
		String sql = "SELECT status_code,status_name FROM m_status";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				StatusBean status = new StatusBean();
				status.setStatusCode(res.getString("status_code"));
				status.setStatusName(res.getString("status_name"));
				statusList.add(status);
			}
		}
		return statusList;
	}
	/**
	 * ユーザー一覧取得
	 * @return ユーザー一覧
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<UserBean> getUserList() throws ClassNotFoundException, SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();
		String sql = "SELECT user_id,user_name FROM m_user";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				UserBean user = new UserBean();
				user.setUserId(res.getString("user_id"));
				user.setUserName(res.getString("user_name"));
				userList.add(user);
			}
		}
		return userList;
	}
	public int taskRegister(TaskRegisterBean task) throws ClassNotFoundException, SQLException {
		int registCount;
		String sql = "INSERT INTO t_task(task_name,category_id,limit_date,user_id,status_code,memo)\n"
				+ "VALUES(?,?,?,?,?,?);";
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, task.getTaskName());
			pstmt.setInt(2, task.getCategoryId());
		    pstmt.setDate(3, task.getLimitDate());
		    pstmt.setString(4, task.getUserId());
		    pstmt.setString(5, task.getStatusCode());
		    pstmt.setString(6, task.getMemo());
		    
		    registCount = pstmt.executeUpdate();
		    return registCount;
		}
	}
}
