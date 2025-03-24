/**
 * Task_project
 * model.entity.UserBean.java
 */
package model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ユーザを表します。
 * m_userのDTOであり、Beanオブジェクトです。
 * @author ksudo
 */
public class UserBean implements Serializable {
	/**
	 * ユーザID
	 */
	private String userId;
	
	/**
	 * パスワード
	 */
	private String passworc;
	
	/**
	 * ユーザ名
	 */
	private String userName;
	
	/**
	 * 更新日時
	 */
	private Timestamp updateDatetime;
	
	/**
	 * コンストラクタ
	 */
	public UserBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return passworc
	 */
	public String getPassworc() {
		return passworc;
	}

	/**
	 * @param passworc セットする passworc
	 */
	public void setPassworc(String passworc) {
		this.passworc = passworc;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return updateDatetime
	 */
	public Timestamp getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * @param updateDatetime セットする updateDatetime
	 */
	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
	
	

}
