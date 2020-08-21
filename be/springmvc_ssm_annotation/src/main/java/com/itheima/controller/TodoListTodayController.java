package com.itheima.controller;

import com.itheima.controller.results.Code;
import com.itheima.controller.results.Result;
import com.itheima.domain.TodoListToday;
import com.itheima.domain.TodoTask;
import com.itheima.service.TodoListTodayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TodoListToday)表控制层
 *
 * @author makejava
 * @since 2020-07-03 08:06:30
 */
@RestController
@RequestMapping("todoListToday")
public class TodoListTodayController {
    /**
     * 服务对象
     */
    @Resource
    private TodoListTodayService todoListTodayService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TodoListToday selectOne(Integer id) {
        return this.todoListTodayService.queryById(id);
    }

    @GetMapping("selectFinishTodo")
    public List<TodoListToday> selectFinishTodo(Integer uid) {
        return todoListTodayService.selectFinishTodo(uid);
    }
    @GetMapping("selectUnfinishTask")
    public List<TodoListToday> selectUnfinishTask(Integer uid) {
        return todoListTodayService.selectUnfinishTodo(uid);
    }

    @PostMapping("insert")
    public Result insert(@RequestBody TodoListToday todoTask) {
        TodoListToday todoTask1 = todoListTodayService.insert(todoTask);
        return new Result(todoTask1 == null ? Code.SAVE_OK:Code.SAVE_ERROR);
    }

    @PostMapping("finish")
    public Result finish(@RequestBody TodoListToday todoTask) {
        int todoTask1 = todoListTodayService.finish(todoTask);
        return new Result(todoTask1 != 0 ? Code.SAVE_OK:Code.SAVE_ERROR);
    }

    @PostMapping("finishTodo")
    public Result finishTodo(@RequestBody TodoListToday todoTask) {
        int todoTask1 = todoListTodayService.finishTodo(todoTask);
        return new Result(todoTask1 != 0 ? Code.SAVE_OK:Code.SAVE_ERROR);
    }

    @PostMapping("beginTodo")
    public Result beginTodo(@RequestBody TodoListToday todoTask) {
        int todoTask1 = todoListTodayService.beginTodo(todoTask);
        return new Result(todoTask1 != 0 ? Code.SAVE_OK:Code.SAVE_ERROR);
    }


}