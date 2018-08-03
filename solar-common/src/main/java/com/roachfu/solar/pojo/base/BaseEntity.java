package com.roachfu.solar.pojo.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author roach
 * @date 2018/7/9 22:48
 */

@Data
public class BaseEntity implements Serializable{

    /**
     * 主键id
     */
    private Long id;

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

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(this);
    }

}
