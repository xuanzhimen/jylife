package com.itheima.service;

import com.itheima.domain.BlogCategory;
import java.util.List;

/**
 * (BlogCategory)表服务接口
 *
 * @author makejava
 * @since 2020-07-03 07:55:55
 */
public interface BlogCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogCategory queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BlogCategory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    BlogCategory insert(BlogCategory blogCategory);

    /**
     * 修改数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    BlogCategory update(BlogCategory blogCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    String findAllCategoryAndAssayTitle(Integer uid);
}