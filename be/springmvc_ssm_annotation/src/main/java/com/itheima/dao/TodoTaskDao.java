package com.itheima.dao;

import com.itheima.domain.TodoListTask;
import com.itheima.domain.TodoTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * (TodoTask)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-03 08:00:39
 */
public interface TodoTaskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TodoTask queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TodoTask> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param todoTask 实例对象
     * @return 对象列表
     */
    List<TodoTask> queryAll(TodoTask todoTask);

    /**
     * 新增数据
     *
     * @param todoTask 实例对象
     * @return 影响行数
     */
    @Insert("insert into jylife.todo_task(name, maketime, operatime, expectedtime, uid)\n" +
            "        values (#{name}, #{makeTime}, #{operaTime}, #{expectedTime}, #{uid})")
    int insert(TodoTask todoTask);

    /**
     * 修改数据
     *
     * @param todoTask 实例对象
     * @return 影响行数
     */
    int update(TodoTask todoTask);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Delete("delete from todo_task where id = ${id}")
    int deleteById(Integer id);

    @Select("select * from todo_task where uid = #{uid} and actualtime = 0")
    List<TodoTask> selectUnfinishTask(Integer uid);

    @Update("update todo_task set actualTime = ${actualTime} where id = ${id}")
    int finish(TodoTask todoTask);

    @Select("select * from todo_task where uid = #{uid} and actualTime = 0 order by maketime desc")
    List<TodoTask> getUnfinishTodoTask(Integer uid);
    @Select("select * from todo_task where uid = #{uid} and actualTime != 0 order by actualtime desc")
    List<TodoTask> getFinishTodoTask(Integer uid);
}