package org.liu;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.liu.CRUD.*;
import org.liu.Login.UserLogin;
import org.liu.Login.beforeAllSqlSession;
import org.liu.Mapper.*;
import org.liu.libraryElement.Book;
import org.liu.libraryElement.BookInformation;
import org.liu.libraryElement.Student;
import org.liu.midElements.searchElement;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        String  UserName = "";
        Student User = null;
        boolean cannotBeLogin = true;
        Date nowDate = new Date(); // 获取当前系统日期
        SqlSessionFactory factory = beforeAllSqlSession.before();
        while (cannotBeLogin) {
            try {
                User = UserLogin.login(factory, UserName);
            } catch (PersistenceException e) {
                System.out.println("尝试登录失败！");
            }
            if(User != null) {
                System.out.println("欢迎你" + User.getSname());
                UserName = User.getSname();
                log.info("用户 {} 进入图书管理系统", UserName);
                cannotBeLogin = false;
            }
            else {
                System.out.println("尝试登录失败！");
                log.warn("尝试登录失败！");
            }
        }
        Scanner scanner = new Scanner(System.in);
        int userCin = 1;
        while (userCin != 0) {
            System.out.println("=======主菜单========");
            System.out.println("======1.增加书籍=====");
            System.out.println("======2.增加学生=====");
            System.out.println("======3.查询书籍=====");
            System.out.println("======4.借阅书籍=====");
            System.out.println("======5.归还书籍=====");
            System.out.println("======0.退   出======");
            userCin = scanner.nextInt();
            switch (userCin){
                case 0:
                    System.out.println("再见");
                    log.info("用户 {} 退出图书管理系统", UserName);
                    break;
                case 1:
                    Book book = AddInformation.addBook();
                    try(SqlSession sqlSession = factory.openSession(true)) {
                        AddBookMapper addbookmapper = sqlSession.getMapper(AddBookMapper.class);
                        addbookmapper.addBookByBook(book);
                        log.info("用户 {} 增加了一个书籍，名称是：{} ",UserName, book.getTitle());
                    }
                    System.out.println("增加书籍成功！");
                    break;
                case 2:
                    Student student = AddInformation.addStudent();
                    try(SqlSession sqlSession = factory.openSession(true)) {
                        AddStudentMapper addStudentMapper = sqlSession.getMapper(AddStudentMapper.class);
                        addStudentMapper.addStudentByStudent(student);
                        String studentName = student.getSname();
                        log.info("用户 {} 增加了一个学生信息，名字是：{} ",UserName, studentName);
                    }
                    System.out.println("录入学生成功！");
                    break;
                case 3:
                    try(SqlSession sqlSession = factory.openSession(true)) {
                        String name = Search.giveName();
                        searchElement searchelement = Search.doingSearch(sqlSession, name);
                        LinkedList<BookInformation>finalResult = searchelement.finalResult;
                        finalResult.forEach(System.out::println);
                        // 这样好像之前的slf4j出现过漏洞，利用自定义名称进行输入导致被注入攻击
                        log.info("用户 {} 查询了名称为：{} 的书籍，查询到：{} 条信息",UserName, searchelement.name, searchelement.finalResult.size());
                    }
                    break;
                case 4:
                    try(SqlSession sqlSession = factory.openSession(true)) {
                        Borrow.doingBorrow(sqlSession, scanner, User, nowDate);
                    }
                    break;
                case 5:
                    ReturnBook.returnBook(factory, User, nowDate, scanner);
                    break;
            }
        }
    }
}