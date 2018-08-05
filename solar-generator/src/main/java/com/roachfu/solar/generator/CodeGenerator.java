package com.roachfu.solar.generator;

import com.roachfu.solar.util.property.PropertiesUtils;
import com.roachfu.solar.util.string.StringUtils;
import com.roachfu.solar.utils.database.DBUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 代码生成器
 *
 * @author fuqiang
 * @datetime 2018/7/10 15:32
 */
@Slf4j
public class CodeGenerator {


    private static final String MAVEN_RESOURCES = "src/main/resources/";
    private static final String MAVEN_MAIN = "src/main/java/";
    private static final String UTF8 = "UTF-8";
    private static final String SLASH = "/";
    private static final String ENTITY = "entity";

    private Configuration cfg;
    private String resourcesPath;
    private String targetBasePath;

    private Map<String, Object> root;

    public static void main(String[] args) throws IOException, TemplateException {

        CodeGenerator generator = new CodeGenerator();
        generator.generate();
    }

    private void init() throws IOException {

        PropertiesUtils propertiesUtils = new PropertiesUtils("application.properties");
        String projectPath = propertiesUtils.getProperty("project.path");
        String module = propertiesUtils.getProperty("project.module");
        String basePackage = propertiesUtils.getProperty("base.package");
        String tableName = propertiesUtils.getProperty("table.name");
        String tableNamePrefix = propertiesUtils.getProperty("table.name.prefix");

        cfg = new Configuration(Configuration.VERSION_2_3_28);

        resourcesPath = projectPath + MAVEN_RESOURCES;
        String mainPath = projectPath + MAVEN_MAIN;

        // System.getProperty("user.dir") : 获取项目所在地址
        String templatesPath = System.getProperty("user.dir") + "/solar-generator/" + MAVEN_RESOURCES + "templates/";
        cfg.setDirectoryForTemplateLoading(new File(templatesPath));
        // 设置存储的编码
        cfg.setDefaultEncoding(UTF8);
        // 异常处理
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // t_demo -> Demo; t_user_info -> UserInfo
        String entity = StringUtils.underscoreToCamel(StringUtils.trimPrefix(tableNamePrefix, tableName), true);

        String basePath = basePackage.replace(".", SLASH) + SLASH;
        // "D:/Project/idea/spring-boot-tutorial/spring-boot-freemarker/src/main/java/com/roachfu/tutorial/demo/"
        targetBasePath = mainPath + basePath + module + SLASH;

        root = new HashMap<>(8);
        root.put("tableName", tableName);
        root.put("basePackage", basePackage);
        root.put("date", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        root.put("module", module);
        root.put(ENTITY, entity);
        root.put("tableColumnList", getTableColumn(tableName));
    }

    private void generate() throws IOException, TemplateException {
        init();

        generateEntity();
        generateMapper();
        generateMapperXml();
        generateService();
        generateServiceImpl();
        generateController();
    }

    private void generateEntity() throws IOException, TemplateException {
        doGenerate("entity-template.ftl", ENTITY, ENTITY, false);
    }

    private void generateMapper() throws IOException, TemplateException {
        doGenerate("mapper-template.ftl", "mapper");
    }

    private void generateMapperXml() throws IOException, TemplateException {
        doGenerateXml("mapper-xml-template.ftl", "Mapper");
    }

    private void generateService() throws IOException, TemplateException {
        doGenerate("service-template.ftl", "service");
    }

    private void generateServiceImpl() throws IOException, TemplateException {
        doGenerate("service-impl-template.ftl", "service/impl", "ServiceImpl");
    }

    private void generateController() throws IOException, TemplateException {
        doGenerate("controller-template.ftl", "controller");
    }

    private void doGenerate(String tempFile, String mvc) throws IOException, TemplateException {
        String suffix = mvc.substring(0, 1).toUpperCase() + mvc.substring(1);
        doGenerate(tempFile, mvc, suffix);
    }

    private void doGenerate(String tempFile, String mvc, String suffix) throws IOException, TemplateException {
        doGenerate(tempFile, mvc, suffix, true);
    }

    private void doGenerate(String tempFile, String mvc, String suffix, boolean isAppendSuffix) throws IOException, TemplateException {
        doGenerate(tempFile, mvc, suffix, isAppendSuffix, ".java");
    }

    private void doGenerate(String tempFile, String mvc, String suffix, boolean isAppendSuffix, String extension) throws IOException, TemplateException {
        Template template = cfg.getTemplate(tempFile);

        // demo/service/impl/DemoServiceImpl
        String targetPath = targetBasePath + mvc + SLASH;
        String filePath;
        if (isAppendSuffix) {
            filePath = targetPath + root.get(ENTITY) + suffix + extension;
        } else {
            filePath = targetPath + root.get(ENTITY) + extension;
        }

        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Writer out = new OutputStreamWriter(new FileOutputStream(file), UTF8);

        template.process(root, out);
    }

    private void doGenerateXml(String tempFile, String suffix) throws IOException, TemplateException {
        Template template = cfg.getTemplate(tempFile);

        // resources/mybatis
        String targetPath = resourcesPath + "mybatis/mapper/" + root.get("module") + SLASH;
        String filePath;
        filePath = targetPath + root.get(ENTITY) + suffix + ".xml";

        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Writer out = new OutputStreamWriter(new FileOutputStream(file), UTF8);

        template.process(root, out);
    }

    /**
     * 根据表名获取相关属性
     *
     * @param tableName 表名
     * @return
     */
    private List<TableColumn> getTableColumn(String tableName) {
        List<TableColumn> tableColumnList = new ArrayList<>();

        // 查询表字段属性：show full columns from TB_SYS_DIC
        String sql = "show full columns from ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, tableName);
            rs = ps.executeQuery();
            //4.处理数据库的返回结果(使用ResultSet类)
            while (rs.next()) {
                String columnName = rs.getString("Field");
                String columnType = rs.getString("Type");
                // 根据columnType解析得到dataType
                String dataType = columnType;
                if (columnType.contains("(")) {
                    dataType = columnType.substring(0, columnType.indexOf('('));
                }
                String nullable = rs.getString("Null");
                String columnDefault = rs.getString("Default");
                String columnComment = rs.getString("Comment");

                TableColumn column = TableColumn.builder()
                        .columnName(columnName)
                        // 下划线风格转小驼峰风格
                        .camelColumnName(StringUtils.underscoreToCamel(columnName))
                        .columnType(columnType)
                        .dataType(dataType)
                        .nullable(nullable)
                        .columnDefault(columnDefault)
                        .columnComment(columnComment)
                        .build();
                tableColumnList.add(column);
            }
        } catch (SQLException e) {
            log.error("数据库连接异常：", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                log.error("关闭结果集连接异常：", e);
            }
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error("关闭数据库连接异常：", e);
            }
        }

        return tableColumnList;
    }
}
