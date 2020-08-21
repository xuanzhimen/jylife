package com.itheima.dao;

import com.itheima.domain.TodoListToday;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * (TodoListToday)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-03 08:00:38
 */
public interface TodoListTodayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TodoListToday queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TodoListToday> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param todoListToday 实例对象
     * @return 对象列表
     */
    List<TodoListToday> queryAll(TodoListToday todoListToday);

    /**
     * 新增数据
     *
     * @param todoListToday 实例对象
     * @return 影响行数
     */
    @Insert("insert into jylife.todo_list_today (name, expectedtime, maketime, isoutplan, uid) VALUES (#{name},#{expectedTime},#{makeTime},#{isOutPlan},#{uid})")
    int insert(TodoListToday todoListToday);

    /**
     * 修改数据
     *
     * @param todoListToday 实例对象
     * @return 影响行数
     */
    int update(TodoListToday todoListToday);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select * from todo_list_today where uid = #{uid} and maketime > #{time1} and maketime < #{time2} and endTime = 0")
    List<TodoListToday> selectUnfinishTodo(@Param("uid") Integer uid, @Param("time1") Long time1, @Param("time2") Long time2);

    @Select("select * from todo_list_today where uid = #{uid} and maketime > #{time1} and maketime < #{time2} and endTime != 0")
    List<TodoListToday> selectFinishTodo(@Param("uid") Integer uid, @Param("time1") Long time1, @Param("time2") Long time2);

    @Update("update todo_list_today set actualTime = ${actualTime} where id = ${id}")
    int finish(TodoListToday todoTask);

    @Update("update todo_list_today set beginTime = ${beginTime}, isRunning = ${isRunning} where id = ${id}")
    int beginTodo(TodoListToday todoTask);

    @Update("update todo_list_today set endTime = ${endTime}, isRunning = ${isRunning}, actualTime = ${actualTime} where id = ${id}")
    int finishTodo(TodoListToday todoTask);
}