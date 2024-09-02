import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.liu.Mapper.CheckConnection;

import java.io.IOException;
@Slf4j
public class beforeAllSqlSession {
    public static SqlSessionFactory before() throws IOException {
        System.out.println("正在连接数据库...");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("Mybatis-config.xml"));

        // 尝试获取 SqlSession 并执行简单查询来检查连接
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 执行一个简单的 SQL 查询，通常可以是数据库的基本信息查询
            CheckConnection checkConnection = session.getMapper(CheckConnection.class);
            int retValue = checkConnection.checkConnection(1);
            System.out.println("成功连接到数据库！");
        } catch (Exception e) {
            System.err.println("无法连接到数据库: " + e.getMessage());
            log.error("连接数据库失败，请检查Mybatis-config配置");
            throw e;
        }
        return sqlSessionFactory;
    }
}
