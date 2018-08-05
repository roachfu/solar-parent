package com.roachfu.solar.utils.database;

import com.roachfu.solar.util.property.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author roach
 * @date 2018/7/11 22:58
 */
@Slf4j
public class DBUtils {

    private static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DEFAULT_URL = "jdbc:mysql://192.168.56.2:3306/solar-parent?useUnicode=true&characterEncoding=utf-8";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "Fanshu_123456";

    private static Connection conn = null;

    static {
        PropertiesUtils propertiesUtils = new PropertiesUtils("application.properties");
        String driver = propertiesUtils.getProperty("jdbc.driver", DEFAULT_DRIVER);
        String url = propertiesUtils.getProperty("jdbc.url", DEFAULT_URL);
        String username = propertiesUtils.getProperty("jdbc.username", DEFAULT_USERNAME);
        String password = propertiesUtils.getProperty("jdbc.password", DEFAULT_PASSWORD);
        try {
            //1.加载驱动程序
            Class.forName(driver);
            //2.获得数据库的连接
            log.info(url);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            log.error("找不到类", e);
        } catch (SQLException e) {
            log.error("获取数据库连接有误", e);
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        return conn;
    }
}
