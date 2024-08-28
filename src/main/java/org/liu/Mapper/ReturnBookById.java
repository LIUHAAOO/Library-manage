package org.liu.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface ReturnBookById {
    @Update("update borrows set return_date = #{returnDate} where" +
            " bid = #{bid} and sid = #{sid} and return_date is null")
    void returnBookById(@Param("bid")int bid, @Param("sid")int sid, @Param("returnDate")Date returnDate);
}
