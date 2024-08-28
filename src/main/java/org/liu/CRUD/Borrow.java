package org.liu.CRUD;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.liu.Mapper.BorrowBookMapper;
import org.liu.libraryElement.Book;
import org.liu.libraryElement.BookInformation;
import org.liu.libraryElement.Student;
import org.liu.midElements.searchElement;

import java.util.*;

@Slf4j
public class Borrow {
    public static String giveName() {
        System.out.println("欢迎进入借阅书籍功能，请输入书籍的名称");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static void doingBorrow(SqlSession sqlSession, Scanner scanner, Student User, Date nowDate) {
        /* 查询 */
        String name = Borrow.giveName();
        searchElement searchelement = Search.doingSearch(sqlSession, name);
        System.out.println("查询到有如下结果：");
        //将多个结果保存
        LinkedList<BookInformation>finalResult = searchelement.finalResult;
        /* 借阅 */
        //利用set来强制用户选择查询到的id
        Set<Integer> MyBid = new HashSet<>();;
        for (BookInformation i:finalResult) {
            MyBid.add(i.getBook().getBid());
            System.out.println(i);
        }
        try {
            while (true) {
                System.out.println("输入书籍的id");
                int bid = scanner.nextInt();
                if(MyBid.contains(bid)) { //是查询到的id
                    // 获取Mapper并执行插入操作
                    BorrowBookMapper borrowBookMapper = sqlSession.getMapper(BorrowBookMapper.class);
                    borrowBookMapper.insertBorrowRecord(nowDate, User.getSid(), bid);

                    System.out.println("借阅成功！请在规定时间归还");
                    log.info("用户 {} 借阅了一本书，书名为：{} ", User.getSname(), name);
                    break;
                }
                else if(bid == -1) {
                    break;
                }
                else {
                    System.out.println("错误的书籍id，请重新输入!或者输入-1退出该功能");
                }
            }
        }
        //异常捕获，防止错误的操作直接干崩程序
        catch (PersistenceException e) {
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
