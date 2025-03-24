/**
 * Task_project
 * model.dao.UserDAO.java
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

/**
 * m_userテーブルのDAOです。
 * @author ksudo
 */
public class UserDAO {
	
	public UserBean selectUser(String userId, String password) throws ClassNotFoundException, SQLException {
		UserBean user = new UserBean();
		
		// sqlの準備
		String sql = "SELECT * FROM m_user WHERE user_id = ? AND password = ?";
		
		// データベースへの接続
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			// プレースホルダへの値の設定
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			// sqlの実行
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while(res.next()) {
				user.setUserId(res.getString("user_id"));
			}
		}
		
		return user;
	}

}
