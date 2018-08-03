package com.roachfu.solar.util.property;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;
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
     * @param key 键
     * @return 值
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * 获取配置属性，如果为空则赋予默认值
     *
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }

    /**
     * 遍历properties, propertyNames()方法的使用
     *
     * @param properties
     */
    @SuppressWarnings("unchecked")
    public void forEach_1(Properties properties) {
        Enumeration<String> keyEnum = (Enumeration<String>) properties.propertyNames();
        while (keyEnum.hasMoreElements()) {
            String key = keyEnum.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key + " = " + value);
        }
    }

    /**
     * 遍历properties, stringPropertyNames()方法的使用
     *
     * @param properties
     */
    public void forEach_2(Properties properties) {
        Iterator<String> iterator = properties.stringPropertyNames().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = properties.getProperty(key);
            System.out.println(key + " = " + value);
        }
    }

    /**
     * 遍历properties, entrySet()方法的使用
     *
     * @param properties
     */
    public void forEach_3(Properties properties) {
        Iterator<Entry<Object, Object>> entrySet = properties.entrySet().iterator();
        while (entrySet.hasNext()) {
            Entry<Object, Object> entry = (Entry<Object, Object>) entrySet.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println(key + " = " + value);
        }

    }


    /**
     * 获取properties下所有的values
     *
     * @param properties
     */
    public void getValues(Properties properties) {
        Enumeration<Object> keyEnum = properties.elements();
        while (keyEnum.hasMoreElements()) {
            String value = (String) keyEnum.nextElement();
            System.out.println(value);
        }
    }
}
