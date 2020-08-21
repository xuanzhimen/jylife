package com.itheima.service;

import com.itheima.domain.TodoTask;
import java.util.List;

/**
 * (TodoTask)表服务接口
 *
 * @author makejava
 * @since 2020-07-03 07:55:57
 */
public interface TodoTaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TodoTask queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TodoTask> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param todoTask 实例对象
     * @return 实例对象
     */
    TodoTask insert(TodoTask todoTask);

    /**
     * 修改数据
     *
     * @param todoTask 实例对象
     * @return 实例对象
     */
    TodoTask update(TodoTask todoTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<TodoTask> selectUnfinishTask(Integer uid);

    int finish(TodoTask todoTask);

    List<TodoTask> getAllTodoTask(Integer uid);
}