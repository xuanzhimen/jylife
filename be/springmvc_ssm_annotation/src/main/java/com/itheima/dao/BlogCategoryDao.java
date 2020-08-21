package com.itheima.dao;

import com.itheima.domain.BlogCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * (BlogCategory)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-03 08:00:33
 */
public interface BlogCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogCategory queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BlogCategory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param blogCategory 实例对象
     * @return 对象列表
     */
    List<BlogCategory> queryAll(BlogCategory blogCategory);

    /**
     * 新增数据
     *
     * @param blogCategory 实例对象
     * @return 影响行数
     */
    @Insert("        insert into jylife.blog_category(name, pid, uid, maketime, operatime, delflag)\n" +
            "        values (#{name}, #{pid}, #{uid}, #{maketime}, #{operatime}, 0)")
    int insert(BlogCategory blogCategory);

    /**
     * 修改数据
     *
     * @param blogCategory 实例对象
     * @return 影响行数
     */
    int update(BlogCategory blogCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Update("update jylife.blog_category set delflag = 1 where id = #{id}")
    int deleteById(Integer id);

    @Select("        select\n" +
            "          id, name, pid, uid, maketime, operatime\n" +
            "        from jylife.blog_category\n" +
            "        where uid = #{uid} and pid = #{pid} and delflag != 1")
    List<BlogCategory> queryByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

    @Select("        select\n" +
            "          id, name, pid, uid, maketime, operatime\n" +
            "        from jylife.blog_category\n" +
            "        where pid = #{pid} and delflag != 1" )
    List<BlogCategory> queryByPid(@Param("pid") Integer pid);

}