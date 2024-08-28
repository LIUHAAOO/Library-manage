package org.liu.Mapper;

import org.apache.ibatis.annotations.*;
import org.liu.libraryElement.Book;
public interface AddBookMapper {
    @Results({
            @Result(property = "bid", column = "bid", id = true),
            @Result(property = "title", column = "title"),
            @Result(property = "author", column = "author"),
            @Result(property = "inf", column = "inf"),
    })
    @Insert("INSERT INTO books(title, author, inf) VALUES(#{book.title}, #{book.author}, #{book.inf})")
    int addBookByBook(@Param("book") Book book);
}

