package model.entity;

import java.io.Serializable;
import java.sql.Date;

public class TaskBean implements Serializable {
	private String taskName;
	private int categoryId;
	private Date limitDate;
	private String userId;
	private String statusCode;
	private String memo;
}
