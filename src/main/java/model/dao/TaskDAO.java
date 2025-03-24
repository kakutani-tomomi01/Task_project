package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;

public class TaskDAO {
	/**
	 * @return カテゴリの一覧取得
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<CategoryBean> getCategoryList() throws SQLException, ClassNotFoundException{
		List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		String sql = "SELECT category_id,category_name FROM m_category";
		
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				CategoryBean category = new CategoryBean();
				category.setCategoryId(res.getInt("category_id"));
				category.setCategoryName(res.getString("category_name"));
				categoryList.add(category);
			}
		}
		return categoryList;
	}
}
