/* DB作成 */
DROP DATABASE IF EXISTS taskdb;
CREATE DATABASE taskdb CHARACTER SET utf8 COLLATE utf8_general_ci;

/* DB選択 */
USE taskdb;

/* ユーザマスタ作成 */
CREATE TABLE m_user
( 
    user_id VARCHAR(24) PRIMARY KEY,
    password VARCHAR(32) NOT NULL,
    user_name VARCHAR(20) UNIQUE NOT NULL,
    update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL /* 更新日時 */
);


/* カテゴリマスタ作成 */
CREATE TABLE m_category
( 
    category_id INT PRIMARY KEY  AUTO_INCREMENT,
    category_name VARCHAR(20) UNIQUE NOT NULL,
    update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

/* ステータスマスタ作成 */
CREATE TABLE m_status
( 
    status_code CHAR(2) PRIMARY KEY,
    status_name VARCHAR(20) UNIQUE NOT NULL,
    update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL 
);
/* タスクトランザクション作成 */
CREATE TABLE t_task
(
    task_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    task_name VARCHAR(50) NOT NULL,
    category_id INT NOT NULL,
    limit_date DATE,
    user_id VARCHAR(24) NOT NULL,
    status_code CHAR(2) NOT NULL,
    memo VARCHAR(100),
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);
/* 外部キー付与 */
/* category_id*/
ALTER TABLE t_task ADD FOREIGN KEY(category_id)
REFERENCES m_category(category_id);
/* user_id */
ALTER TABLE t_task ADD FOREIGN KEY(user_id)
REFERENCES m_user(user_id);
/* status_code */
ALTER TABLE t_task ADD FOREIGN KEY(status_code)
REFERENCES m_status(status_code);

/* m_user*/
INSERT INTO m_user(user_id,password,user_name)VALUES
("admin","password","ADMIN"),
("e0001","password","employee0001"),
("e0002","password","employee0002");
/* m_category初期データ挿入 */
INSERT INTO m_category(category_name) VALUES
("新商品A:開発プロジェクト"),
("既存商品B:改良プロジェクト");
/* m_status初期データ挿入 */
INSERT INTO m_status(status_code,status_name) VALUES
('00',"未着手"),
('50',"着手"),
('99',"完了");
/* m_status初期データ挿入 */
INSERT INTO t_task(task_name,category_id,limit_date,user_id,status_code,memo,create_datetime) VALUES
("タスクテスト1",1,sysdate(),"admin","00","タスクのテストです",sysdate());