package org.liu.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.liu.libraryElement.Student;

public interface CheckLogin {
    @Results({
            @Result(property = "sid", column = "sid", id = true),
            @Result(property = "sname", column = "sname"),
            @Result(property = "snum", column = "snum"),
            @Result(property = "password", column = "password"),
    })
    @Select("select * from students where sname = #{sname} and password = #{password}")
    Student canBeLogin(@Param("sname") String sid, @Param("password") String password);
}
