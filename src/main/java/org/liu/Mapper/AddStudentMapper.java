package org.liu.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.liu.libraryElement.Student;

public interface AddStudentMapper {
    @Results({
            @Result(property = "sid", column = "sid", id = true),
            @Result(property = "sname", column = "sname"),
            @Result(property = "snum", column = "snum"),
    })
    @Insert("INSERT INTO students(sname, snum) VALUES(#{student.sname}, #{student.snum})")
    void addStudentByStudent(@Param("student") Student student);
}
