package com.itheima.service;

import com.itheima.domain.TodoListTask;
import java.util.List;

/**
 * (TodoListTask)表服务接口
 *
 * @author makejava
 * @since 2020-07-03 07:55:57
 */
public interface TodoListTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    TodoListTask queryById(Integer tid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TodoListTask> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param todoListTask 实例对象
     * @return 实例对象
     */
    TodoListTask insert(TodoListTask todoListTask);

    /**
     * 修改数据
     *
     * @param todoListTask 实例对象
     * @return 实例对象
     */
    TodoListTask update(TodoListTask todoListTask);

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer tid);

}