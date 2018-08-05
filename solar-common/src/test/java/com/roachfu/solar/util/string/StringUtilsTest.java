package com.roachfu.solar.util.string;

import org.junit.Test;

/**
 * @author roach
 * @date 2018/7/12 20:45
 */
public class StringUtilsTest {

    @Test
    public void test() {
        String aaa = "app_version_fld";
        System.out.println(StringUtils.underscoreToCamel(aaa));
        aaa = "appVersionFld";
        System.out.println(StringUtils.camelToUnderscore(aaa));

        System.out.println(StringUtils.trimPrefix("tb_", "tb_demo"));
        System.out.println(StringUtils.trimPrefix("tbs_", "tb_demo"));
    }

    @Test
    public void testUnderscoreToCamel() {
        System.out.println(StringUtils.underscoreToCamel("aaa_", false));
        System.out.println(StringUtils.underscoreToCamel("aaa__", false));
        System.out.println(StringUtils.underscoreToCamel("___", false));
        System.out.println(StringUtils.underscoreToCamel("_bb", false));
        System.out.println(StringUtils.underscoreToCamel("_bb__", false));
        System.out.println(StringUtils.underscoreToCamel("_bb__cc", false));
        System.out.println(StringUtils.underscoreToCamel("bb_cc_dd", false));
        System.out.println(StringUtils.underscoreToCamel("bb_cc_dd", true));
    }
}