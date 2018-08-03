package com.roachfu.solar.util.property;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author roach
 * @date 2018/7/11 22:12
 */
public class PropertiesUtilsTest {

    private PropertiesUtils propertiesUtils;

    @Before
    public void init(){
        propertiesUtils = new PropertiesUtils("src/test/resources/application.properties");
    }

    @Test
    public void test(){
        assertEquals("Fu",propertiesUtils.getProperty("roach.name"));
        assertEquals("male",propertiesUtils.getProperty("roach.gender"));

        assertEquals("江西", propertiesUtils.getProperty("roach.city", "江西"));

    }
}