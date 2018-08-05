package com.roachfu.solar.util.property;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties 工具类
 *
 * @author roach
 * @date 2018-07-11
 */

@Slf4j
public class PropertiesUtils {

    private Properties properties;

    public PropertiesUtils(String fileName) {
        init(fileName);
    }

    /**
     * 获取properties
     *
     * @param fileName 相对于ClassPath下的全路径名
     * @return
     */
    private void init(String fileName) {
        properties = new Properties();

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);

        } catch (IOException e) {
            log.error("读取【" + fileName + "】配置文件异常. . . ", e);
        }
    }

    /**
     * 获取配置属性
     *
     * @param key 键
     * @return 值
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * 获取配置属性，如果为空则赋予默认值
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
