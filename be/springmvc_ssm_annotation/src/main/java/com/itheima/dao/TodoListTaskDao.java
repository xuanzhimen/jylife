package com.itheima.dao;

import com.itheima.domain.TodoListTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (TodoListTask)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-03 08:00:34
 */
public interface TodoListTaskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    TodoListTask queryById(Integer tid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TodoListTask> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param todoListTask 实例对象
     * @return 对象列表
     */
    List<TodoListTask> queryAll(TodoListTask todoListTask);

    /**
     * 新增数据
     *
     * @param todoListTask 实例对象
     * @return 影响行数
     */
    int insert(TodoListTask todoListTask);

    /**
     * 修改数据
     *
     * @param todoListTask 实例对象
     * @return 影响行数
     */
    int update(TodoListTask todoListTask);

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 影响行数
     */
    int deleteById(Integer tid);

}