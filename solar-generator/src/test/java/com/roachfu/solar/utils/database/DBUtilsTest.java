package com.roachfu.solar.utils.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * @author roach
 * @date 2018/7/11 23:23
 */
public class DBUtilsTest {

    @Test
    public void test() throws SQLException {
        //2.获得数据库链接

        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        String sql = "SELECT COLUMN_NAME , COLUMN_TYPE , DATA_TYPE , IS_NULLABLE , COLUMN_DEFAULT , COLUMN_COMMENT FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'solar-parent' AND TABLE_NAME = 'tb_demo'";
        try (
                Connection conn = DBUtils.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {
            //4.处理数据库的返回结果(使用ResultSet类)
            while (rs.next()) {
                System.out.println(rs.getString("column_name") + " "
                        + rs.getString("column_type"));
            }
        }
    }
}