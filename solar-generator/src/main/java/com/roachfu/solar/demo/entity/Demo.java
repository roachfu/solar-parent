package com.roachfu.solar.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * demo 实体
 *
 * @author fuqiang
 * @date 2018-07-12
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demo implements Serializable{

    /**
     * 主键id
     */
    private Long id;

    /**
     * demo名称
     */
    private String demoName;

    /**
     * demo值
     */
    private String demoValue;

    /**
     * demo状态：1=正常，0=屏蔽
     */
    private Integer demoStatus;

    /**
     * 删除标识：0=未删除，1=删除
     */
    private Integer isDel;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 修改人id
     */
    private Long editorId;

    /**
     * 修改时间
     */
    private Date editTime;

}
