package com.itheima.controller;

import com.itheima.controller.results.Code;
import com.itheima.controller.results.Result;
import com.itheima.domain.TodoTask;
import com.itheima.service.TodoTaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TodoTask)表控制层
 *
 * @author makejava
 * @since 2020-07-03 08:06:32
 */
@RestController
@RequestMapping("todoTask")
public class TodoTaskController {
    /**
     * 服务对象
     */
    @Resource
    private TodoTaskService todoTaskService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TodoTask selectOne(Integer id) {
        return this.todoTaskService.queryById(id);
    }

    @GetMapping("getAllTodoTask")
    public List<TodoTask> getAllTodoTask(Integer uid) throws InterruptedException {
        return todoTaskService.getAllTodoTask(uid);
    }

    @GetMapping("selectUnfinishTask")
    public List<TodoTask> selectUnfinishTask(Integer uid) throws InterruptedException {
        return todoTaskService.selectUnfinishTask(uid);
    }

    @PostMapping("insert")
    public Result insert(@RequestBody TodoTask todoTask) {
        TodoTask todoTask1 = todoTaskService.insert(todoTask);
        return new Result(todoTask1 == null ? Code.SAVE_OK : Code.SAVE_ERROR);
    }

    @PostMapping("finish")
    public Result finish(@RequestBody TodoTask todoTask) {
        int todoTask1 = todoTaskService.finish(todoTask);
        return new Result(todoTask1 != 0 ? Code.SAVE_OK : Code.SAVE_ERROR);
    }

    @GetMapping("delete")
    public Result finish(Integer id) {
        boolean b = todoTaskService.deleteById(id);
        return new Result(b ? Code.SAVE_OK : Code.SAVE_ERROR);
    }
}