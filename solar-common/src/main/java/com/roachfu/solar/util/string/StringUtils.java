package com.roachfu.solar.util.string;

/**
 * @author fuqiang
 * @date 2018/7/12
 */
public class StringUtils {

    private StringUtils() {
    }

    private static final char UNDERSCORE = '_';

    private static final char A = 'a';

    private static final char Z = 'z';

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param camel 驼峰格式字符串
     * @return String
     */
    public static String camelToUnderscore(String camel) {

        if (camel == null || "".equals(camel.trim())) {
            return "";
        }

        int len = camel.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = camel.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERSCORE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param underscore 下划线字符串
     * @return String
     */
    public static String underscoreToCamel(String underscore) {
        return underscoreToCamel(underscore, false);
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param underscore 下划线字符串
     * @param initial    首字母是否大写
     * @return String
     */
    public static String underscoreToCamel(String underscore, boolean initial) {
        if (underscore == null || "".equals(underscore.trim())) {
            return "";
        }

        String underscoreStr = underscore.toLowerCase();
        String[] splits = underscoreStr.split("_");
        StringBuilder sb = new StringBuilder();
        if (splits.length > 0){
            sb = new StringBuilder(splits[0]);
            for (int i = 1; i < splits.length; i++){
                if (splits[i] != null && !"".equals(splits[i].trim())){
                    sb.append(initialToUpperCase(splits[i]));
                }
            }
        }

        if (initial) {
            return initialToUpperCase(sb.toString());
        }
        return sb.toString();
    }

    /**
     * 去掉字符串的指定前缀
     *
     * @param prefix 前缀
     * @param source 源字符串
     * @return String
     */
    public static String trimPrefix(String prefix, String source) {
        if (!source.startsWith(prefix)) {
            throw new IllegalArgumentException("源字符串[" + source + "]不包含前缀[" + prefix + "]");
        }

        return source.substring(prefix.length());
    }

    /**
     * 首字母大写
     *
     * @param source 源字符串
     * @return String
     */
    public static String initialToUpperCase(String source) {
        char[] chars = source.toCharArray();
        if (chars[0] >= A && chars[0] <= Z) {
            chars[0] = (char) (chars[0] - 32);
        }
        return String.valueOf(chars);
    }
}
