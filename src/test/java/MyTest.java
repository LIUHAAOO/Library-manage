
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.liu.CRUD.Borrow;
import org.liu.CRUD.Search;
import org.liu.Login.beforeAllSqlSession;
import org.liu.Mapper.BorrowBookMapper;
import org.liu.libraryElement.BookInformation;
import org.liu.libraryElement.Student;
import org.liu.midElements.searchElement;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;


@Slf4j
public class MyTest {
    @Test
    public void print() throws IOException {
        SqlSessionFactory factory = beforeAllSqlSession.before();
        Student User = new Student();
        User.setSid(2);
        User.setSname("liu");
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        try(SqlSession sqlSession = factory.openSession(true)) {
            Date nowDate = new Date();
            MyTest.TestDoingBorrow(sqlSession, User, nowDate);
        }
    }
    public static void TestDoingBorrow(SqlSession sqlSession, Student User, Date nowDate) {
        /* 查询 */
        String name = Borrow.giveName();
        searchElement searchelement = Search.doingSearch(sqlSession, name);
        System.out.println("查询到有如下结果：");
        LinkedList<BookInformation> finalResult = searchelement.finalResult;
        finalResult.forEach(System.out::println);
        /* 借阅 */
        try {
            System.out.println("输入书籍的id");
            int bid = 3;
            // 获取Mapper并执行插入操作
            BorrowBookMapper borrowBookMapper = sqlSession.getMapper(BorrowBookMapper.class);
            borrowBookMapper.insertBorrowRecord(nowDate, User.getSid(), bid);

            System.out.println("借阅成功！请在规定时间归还");
            log.info("用户 {} 借阅了一本书，书名为：{} ", User.getSname(), name);

        } catch (PersistenceException e) {
            // 捕获MyBatis的异常并记录日志
            log.error("发生错误：{}", e.getMessage());  // 记录简洁的错误信息
            System.out.println("借书失败，可能是这本书还未归还或是不存在该书信息，请稍后重试，详细错误信息：" + e.getMessage());
        } catch (Exception e) {
            // 捕获所有其他异常
            log.error("发生未知错误。错误信息：{}", e.getMessage());
            System.out.println("发生未知错误，请联系管理员");
        } finally {
            // 可选：在finally块中执行清理操作
            log.info("借书操作结束");
        }
    }
}
