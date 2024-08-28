package org.liu.Login;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.liu.Mapper.CheckLogin;
import org.liu.libraryElement.Student;
import java.util.Scanner;

public class UserLogin {
    public static Student login(SqlSessionFactory factory, String UserName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = scanner.next();
        System.out.println("请输入用户密码：");
        String pass = scanner.next();

        try(SqlSession sqlSession = factory.openSession(true)) {
            CheckLogin checkLogin = sqlSession. getMapper(CheckLogin.class);
            return checkLogin.canBeLogin(name, pass);
        }
    }
}
