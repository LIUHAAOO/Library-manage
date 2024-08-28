package org.liu.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.liu.libraryElement.Book;

import java.util.List;

public interface SearchUserBooks {
        @Results({
                @Result(property = "bid", column = "bid"),
                @Result(property = "title", column = "title"),
                @Result(property = "author", column = "author"),
                @Result(property = "inf", column = "inf")
        })
        @Select("SELECT books.bid, books.title, books.author, books.inf " + // 选择 inf 字段
                "FROM borrows " +
                "INNER JOIN books ON borrows.bid = books.bid " +
                "WHERE borrows.sid = #{sid} AND borrows.return_date IS NULL"
        )
        List<Book> getBooksBySid(@Param("sid") int sid);
}
