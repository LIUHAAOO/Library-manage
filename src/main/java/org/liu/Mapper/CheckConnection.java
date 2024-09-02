package org.liu.Mapper;

import org.apache.ibatis.annotations.Select;

public interface CheckConnection {
    @Select("select * from students where sid = #{sid}")
    int checkConnection(int sid);
}
