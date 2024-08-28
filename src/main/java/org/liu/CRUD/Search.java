package org.liu.CRUD;
import org.apache.ibatis.session.SqlSession;
import org.liu.Mapper.SearchBookMapper;
import org.liu.Mapper.SearchBookStateMapper;
import org.liu.libraryElement.Book;
import org.liu.libraryElement.BookInformation;
import org.liu.midElements.searchElement;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Search {
    public static String giveName() {
        System.out.println("欢迎进入查询书籍功能，请输入书籍的名称");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    public static searchElement doingSearch(SqlSession sqlSession, String name) {
        //获取纯书籍信息
        SearchBookMapper searchBookMapper = sqlSession.getMapper(SearchBookMapper.class);
        List <Book>searchResult = searchBookMapper.searchBookByName(name);

        //获取书籍状态
        SearchBookStateMapper searchBookStateMapper = sqlSession.getMapper(SearchBookStateMapper.class);
        LinkedList<BookInformation>finalResult = AddBookState.addBookState(searchResult, searchBookStateMapper);

        //整合状态信息
        searchElement searchElement = new searchElement();
        searchElement.setFinalResult(finalResult);
        searchElement.setName(name);
        return searchElement;
    }
}
