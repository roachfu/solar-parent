<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePackage}.${module}.mapper.${entity}Mapper">

    <sql id="${module}_columns">
        <#list tableColumnList as column>
            ${column.columnName}<#sep>,</#sep>
        </#list>
    </sql>

    <select id="selectList" resultType="${entity}">
        select <include refid="${module}_columns"/> from ${tableName}
    </select>

    <select id="selectOne" resultType="${entity}">
        select <include refid="${module}_columns"/> from ${tableName} where id = ${"#"}{id}
    </select>

    <insert id="insert" parameterType="${entity}">
        insert into ${tableName}(
        <include refid="${module}_columns"/>
        ) values (
        <#list tableColumnList as column>
            ${"#"}{${column.camelColumnName}}<#sep>,</#sep>
        </#list>
        )
    </insert>

    <update id="update" parameterType="${entity}">
        update ${tableName}
        <set>
            <#list tableColumnList as column>
            <if test="${column.camelColumnName} != null">
                ${column.columnName} = ${"#"}{${column.camelColumnName}}
            </if>
            </#list>
        </set>
        where id = ${"#"}{id}
    </update>
</mapper>