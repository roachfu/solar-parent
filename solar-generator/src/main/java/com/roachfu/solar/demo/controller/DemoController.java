package com.roachfu.solar.demo.controller;

import com.roachfu.solar.demo.entity.Demo;
import com.roachfu.solar.demo.service.DemoService;
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
 * demo控制层
 *
 * @author roach
 * @date 2018-08-03
 */

@Api(value = "demo", tags = {"demo"})
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    /**
     * 新增一条demo记录
     *
     * @param demo 新增的demo参数
     * @return int
     */
    @ApiOperation(value = "新增demo", notes = "新增一条demo记录")
    @ApiImplicitParam(name = "demo", value = "Demo实体", required = true, dataType = "Demo")
    @PostMapping
    public int add(@RequestBody Demo demo) {
        return demoService.save(demo);
    }

    /**
     * 获取demo列表
     *
     * @return List<Demo>
     */
    @ApiOperation(value = "获取demo列表", notes = "获取demo列表")
    @ApiResponse(code = 200, message = "success", response = List.class)
    @ResponseHeader
    @GetMapping
        public List<Demo> queryList() {
        return demoService.getList();
    }

    /**
    * 根据id获取一条demo记录
    *
    * @param id demo id
    * @return Demo
    */
    @ApiOperation(value = "获取一条demo", notes = "根据id获取一条demo记录")
    @ApiImplicitParam(name = "id", value = "demo id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/{id}")
    public Demo queryOne(@PathVariable("id") Integer id) {
        return demoService.getOne(id);
    }

    /**
    * 修改demo记录
    *
    * @param id
    * @param demo
    * @return
    */
    @ApiOperation(value = "修改demo", notes = "根据id修改demo")
    @ApiImplicitParam(name = "id", value = "demo id", required = true, dataType = "int", paramType = "path")
    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id, @RequestBody Demo demo) {
        demo.setId(id);
        demo.setEditTime(new Date());
        return demoService.update(demo);
    }

    /**
    * 删除一条demo记录
    *
    * @param id demo id
    * @return
    */
    @ApiOperation(value = "删除demo", notes = "删除一条demo记录")
    @ApiImplicitParam(name = "id", value = "demo id", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Integer id) {
        return demoService.delete(id);
    }
}
