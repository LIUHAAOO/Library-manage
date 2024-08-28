package org.liu.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.liu.libraryElement.Book;

import java.util.LinkedList;
import java.util.List;

public interface SearchBookMapper {
    @Results({
            @Result(property = "bid", column = "bid", id = true),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "inf", column = "inf"),
    })
    @Select("SELECT * FROM books WHERE books.title = #{name}")
    List<Book> searchBookByName(@Param("name") String name);
}
