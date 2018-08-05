package com.roachfu.solar.pojo.base;

import com.roachfu.solar.util.id.IdGenUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类实体
 *
 * @author roach
 * @date 2018/7/9 22:48
 */

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7617759139232141464L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 是否删除：0=未删除；1=删除
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

    public void init() {
        this.setId(IdGenUtils.getFlowIdWorkerInstance().nextId());
        this.setEditor("");
        this.setEditorId(0L);
        this.setEditTime(new Date());
        this.setCreator("");
        this.setCreatorId(0L);
        this.setCreateTime(new Date());
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(this);
    }

}
