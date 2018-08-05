package com.roachfu.solar.generator;

import lombok.*;

/**
 * 表属性实体
 *
 * @author roach
 * @date 2018/7/12 20:48
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableColumn {

    /**
     * 字段名：比如id, user_name
     */
    private String columnName;

    /**
     * 驼峰字段名：比如userName, userId
     */
    private String camelColumnName;

    /**
     * 字段类型：varchar(32), tinyint(3), int(11), bigint(20) unsigned, datetime
     */
    private String columnType;

    /**
     * 数据类型：varchar, tinyint, int , bigint
     */
    private String dataType;

    /**
     * 是否为空：NO=不为空；YES=可空
     */
    private String nullable;

    /**
     * 字段默认值
     */
    private String columnDefault;

    /**
     * 字段注释：
     * <p>
     * 性别：0=女；1=男；2=未知
     * </p>
     */
    private String columnComment;
}
