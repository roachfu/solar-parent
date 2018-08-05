package ${basePackage}.${module}.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ${module} 实体
 *
 * @author fuqiang
 * @date 2018-07-12
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${entity} implements Serializable{

<#list tableColumnList as tableColumn>
    /**
     * ${tableColumn.columnComment}
     */
    <#if tableColumn.dataType = 'bigint'>
    private Long ${tableColumn.camelColumnName};
    </#if>
    <#if tableColumn.dataType = 'varchar'>
    private String ${tableColumn.camelColumnName};
    </#if>
    <#if tableColumn.dataType = 'tinyint' || tableColumn.dataType = 'smallint' || tableColumn.dataType = 'int'>
    private Integer ${tableColumn.camelColumnName};
    </#if>
    <#if tableColumn.dataType = 'datetime'>
    private Date ${tableColumn.camelColumnName};
    </#if>
    <#if tableColumn.dataType = 'decimal'>
    private BigDecimal ${tableColumn.camelColumnName};
    </#if>

</#list>
}
