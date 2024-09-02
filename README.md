# Library-manage
一个基于Maven构建的简易的图书管理系统，有日志功能
### 使用说明：
1.登录Mysql，由于配置文件中的用户名是root，密码是123456,数据库名是`Library`，根据上述信息注册数据库

2.创建三张表如下：
```mysql
CREATE TABLE books (
    bid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tittle VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    inf VARCHAR(255)
);
```

```mysql
CREATE TABLE students (
    sid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sname VARCHAR(255) NOT NULL,
    snum INT NOT NULL,
    password VARCHAR(255) NOT NULL
);
```
```mysql
CREATE TABLE borrows (
    borrow_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    book_id INT NOT NULL UNIQUE,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(sid),
    FOREIGN KEY (book_id) REFERENCES books(bid)
);
```
3.由于一开始数据库中学生信息为空，所以增加一个学生信息用来登录：
```mysql
INSERT INTO students (sname, snum, password)
VALUES ('root', 123, '123');
```
4.在IDEA中运行，将zip下载到本地后解压，在IDEA中打开文件夹

5.如果日志的默认输出位置是桌面，如果需要放置到额外的位置请自行修改`resourses`中的log4j2.xml，java版本默认使用最新版本即可

6.将`release`中的jar文件取出放在桌面或者任意目录下，控制台中cd到放置的目录，java -jar+{jar文件名}即可一键运行：
![Pasted image 20240816165204](https://github.com/user-attachments/assets/fe978617-d93e-4243-bc71-e5a6437d0198)
