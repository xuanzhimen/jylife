package com.itheima.controller;

import com.itheima.domain.TodoThink;
import com.itheima.service.TodoThinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TodoThink)表控制层
 *
 * @author makejava
 * @since 2020-07-07 00:52:14
 */
@RestController
@RequestMapping("todoThink")
public class TodoThinkController {
    /**
     * 服务对象
     */
    @Resource
    private TodoThinkService todoThinkService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TodoThink selectOne(Integer id) {
        return this.todoThinkService.queryById(id);
    }

    @GetMapping("selectAll")
    List<TodoThink> selectAll(Integer uid) {
        return this.todoThinkService.selectAll(uid);
    }

    @PostMapping("insert")
    public TodoThink insert(@RequestBody TodoThink todoThink) {
        return this.todoThinkService.insert(todoThink);
    }
}