package com.itheima.controller;

import com.itheima.domain.TodoListTask;
import com.itheima.service.TodoListTaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TodoListTask)表控制层
 *
 * @author makejava
 * @since 2020-07-03 08:04:58
 */
@RestController
@RequestMapping("todoListTask")
public class TodoListTaskController {
    /**
     * 服务对象
     */
    @Resource
    private TodoListTaskService todoListTaskService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TodoListTask selectOne(Integer id) {
        return this.todoListTaskService.queryById(id);
    }


}