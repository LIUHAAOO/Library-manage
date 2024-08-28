package org.liu.Login;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class beforeAllSqlSession {
    public static SqlSessionFactory before() throws IOException {
        return new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("Mybatis-config.xml"));
    }
}
