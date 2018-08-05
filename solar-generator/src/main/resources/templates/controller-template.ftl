package ${basePackage}.${module}.controller;

import ${basePackage}.${module}.entity.${entity};
import ${basePackage}.${module}.service.${entity}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ResponseHeader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ${module}控制层
 *
 * @author roach
 * @date ${date}
 */

@Api(value = "${module}", tags = {"${module}"})
@RestController
@RequestMapping("/${module}")
public class ${entity}Controller {

    @Resource
    private ${entity}Service ${module}Service;

    /**
     * 新增一条${module}记录
     *
     * @param ${module} 新增的${module}参数
     * @return int
     */
    @ApiOperation(value = "新增${module}", notes = "新增一条${module}记录")
    @ApiImplicitParam(name = "${module}", value = "${entity}实体", required = true, dataType = "${entity}")
    @PostMapping
    public int add(@RequestBody ${entity} ${module}) {
        return ${module}Service.save(${module});
    }

    /**
     * 获取${module}列表
     *
     * @return List<${entity}>
     */
    @ApiOperation(value = "获取${module}列表", notes = "获取${module}列表")
    @ApiResponse(code = 200, message = "success", response = List.class)
    @ResponseHeader
    @GetMapping
    public List<${entity}> queryList() {
        return ${module}Service.getList();
    }

    /**
    * 根据id获取一条${module}记录
    *
    * @param id ${module} id
    * @return ${entity}
    */
    @ApiOperation(value = "获取一条${module}", notes = "根据id获取一条${module}记录")
    @ApiImplicitParam(name = "id", value = "${module} id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{id}")
    public ${entity} queryOne(@PathVariable("id") Integer id) {
        return ${module}Service.getOne(id);
    }

    /**
    * 修改${module}记录
    *
    * @param id
    * @param ${module}
    * @return
    */
    @ApiOperation(value = "修改${module}", notes = "根据id修改${module}")
    @ApiImplicitParam(name = "id", value = "${module} id", required = true, dataType = "int", paramType = "path")
    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id, @RequestBody ${entity} ${module}) {
        ${module}.setId(id);
        ${module}.setEditTime(new Date());
        return ${module}Service.update(${module});
    }

    /**
    * 删除一条${module}记录
    *
    * @param id ${module} id
    * @return
    */
    @ApiOperation(value = "删除${module}", notes = "删除一条${module}记录")
    @ApiImplicitParam(name = "id", value = "${module} id", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Integer id) {
        return ${module}Service.delete(id);
    }
}
