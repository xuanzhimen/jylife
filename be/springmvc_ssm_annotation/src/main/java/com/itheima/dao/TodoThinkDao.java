package com.itheima.dao;

import com.itheima.domain.TodoThink;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (TodoThink)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-07 00:52:11
 */
public interface TodoThinkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TodoThink queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TodoThink> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param todoThink 实例对象
     * @return 对象列表
     */
    List<TodoThink> queryAll(TodoThink todoThink);

    /**
     * 新增数据
     *
     * @param todoThink 实例对象
     * @return 影响行数
     */
    @Insert("insert into todo_think (uid, makeTime, content) values (#{uid}, #{makeTime}, #{content})")
    int insert(TodoThink todoThink);

    /**
     * 修改数据
     *
     * @param todoThink 实例对象
     * @return 影响行数
     */
    int update(TodoThink todoThink);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select * from todo_think where uid = #{uid} order by maketime desc")
    List<TodoThink> selectAll(Integer uid);
}