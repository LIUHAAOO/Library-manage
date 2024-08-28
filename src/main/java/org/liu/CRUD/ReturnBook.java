package org.liu.CRUD;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.liu.Mapper.DeleteBorrowMapper;
import org.liu.Mapper.ReturnBookById;
import org.liu.Mapper.SearchUserBooks;
import org.liu.libraryElement.Book;
import org.liu.libraryElement.Student;

import java.util.*;

@Slf4j
public class ReturnBook {
    public static void returnBook(SqlSessionFactory factory, Student User, Date nowDate, Scanner scanner) {
        try(SqlSession sqlSession = factory.openSession(true)) {

            SearchUserBooks searchUserBooks = sqlSession.getMapper(SearchUserBooks.class);
            List<Book> books = searchUserBooks.getBooksBySid(User.getSid());

            DeleteBorrowMapper deleteBorrowMapper = sqlSession.getMapper(DeleteBorrowMapper.class);

            if(!books.isEmpty()) {
                System.out.println("查询到您借阅如下书籍未归还：");
                Set<Integer> MyBid = new HashSet<>();;
                for (Book i:books) {
                    MyBid.add(i.getBid());
                    System.out.println(i);
                }
                System.out.println("请输入需要归还的书籍id");
                while (true) {
                    int returnBookId = scanner.nextInt();
                    if(MyBid.contains(returnBookId)) {
                        ReturnBookById returnBookById = sqlSession.getMapper(ReturnBookById.class);
                        returnBookById.returnBookById(returnBookId, User.getSid(), nowDate);
                        log.info("用户 {} 归还了一本书，书的id为：{} ",User.getSname(), returnBookId);
                        System.out.println("还书成功！");
                        deleteBorrowMapper.deleteBorrowInformation(User.getSid(), returnBookId);
                        break;
                    }
                    else if(returnBookId == -1) {
                        break;
                    }
                    else {
                        System.out.println("错误的书籍id，请重新输入!或者输入-1退出该功能");
                    }
                }

            }
            else {
                System.out.println("您目前没有借阅任何书籍！");
                log.info("用户 {} 的借书量为0 ",User.getSname());
            }
        }
    }
}
