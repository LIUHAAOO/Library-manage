package org.liu.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface BorrowBookMapper {
    @Insert("INSERT INTO borrows (borrow_date, sid, bid) " +
            "VALUES (#{borrowDate}, #{sid}, #{bid})"
            )
    void insertBorrowRecord(@Param("borrowDate") Date borrowDate,
                            @Param("sid") int sid,
                            @Param("bid") int bid
    );
}
