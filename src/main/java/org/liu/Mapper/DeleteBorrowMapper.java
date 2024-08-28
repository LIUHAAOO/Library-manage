package org.liu.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface DeleteBorrowMapper {
    @Delete("DELETE FROM borrows WHERE sid = #{sid} AND bid = #{bid}")
    void deleteBorrowInformation(@Param("sid") int sid, @Param("bid") int bid);
}
