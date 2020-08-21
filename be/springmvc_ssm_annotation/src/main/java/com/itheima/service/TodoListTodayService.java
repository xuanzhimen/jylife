package com.itheima.service;

import com.itheima.domain.TodoListToday;
import java.util.List;

/**
 * (TodoListToday)表服务接口
 *
 * @author makejava
 * @since 2020-07-03 07:55:57
 */
public interface TodoListTodayService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TodoListToday queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TodoListToday> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param todoListToday 实例对象
     * @return 实例对象
     */
    TodoListToday insert(TodoListToday todoListToday);

    /**
     * 修改数据
     *
     * @param todoListToday 实例对象
     * @return 实例对象
     */
    TodoListToday update(TodoListToday todoListToday);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<TodoListToday> selectUnfinishTodo(Integer uid);

    int finish(TodoListToday todoTask);

    int beginTodo(TodoListToday todoTask);

    int finishTodo(TodoListToday todoTask);

    List<TodoListToday> selectFinishTodo(Integer uid);
}