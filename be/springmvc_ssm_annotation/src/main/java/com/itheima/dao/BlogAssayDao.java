package com.itheima.dao;

import com.itheima.domain.BlogAssay;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * (BlogAssay)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-03 07:57:37
 */
public interface BlogAssayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Select("select * from blog_assay where id = #{id}")
    BlogAssay queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BlogAssay> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param blogAssay 实例对象
     * @return 对象列表
     */
    List<BlogAssay> queryAll(BlogAssay blogAssay);

    /**
     * 新增数据
     *
     * @param blogAssay 实例对象
     * @return 影响行数
     */
    @Insert("        insert into jylife.blog_assay(title, content, cid, maketime, operatime)\n" +
            "        values (#{title}, #{content}, #{cid}, #{maketime}, #{operatime})")
    int insert(BlogAssay blogAssay);

    /**
     * 修改数据
     *
     * @param blogAssay 实例对象
     * @return 影响行数
     */

    @Update("update jylife.blog_assay set content = #{content} where id = #{id}")
    int update(BlogAssay blogAssay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("        select\n" +
            "          id, title, content, cid, maketime, operatime\n" +
            "        from jylife.blog_assay\n" +
            "        where cid = #{cid}")
    List<BlogAssay> queryByCid(@Param("cid") Integer cid);

}