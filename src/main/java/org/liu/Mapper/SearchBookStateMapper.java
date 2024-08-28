package org.liu.Mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.liu.libraryElement.BookState;


public interface SearchBookStateMapper {
    @Results({
            @Result(property = "borrow_date", column = "borrow_date"),
            @Result(property = "return_date", column = "return_date"),
    })
    @Select("SELECT * FROM borrows WHERE bid = #{bid}")
    BookState searchBookStateByBid(int bid);
}
