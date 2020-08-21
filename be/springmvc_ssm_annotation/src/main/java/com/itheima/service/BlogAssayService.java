package com.itheima.service;

import com.itheima.domain.BlogAssay;
import java.util.List;

/**
 * (BlogAssay)表服务接口
 *
 * @author makejava
 * @since 2020-07-03 07:55:55
 */
public interface BlogAssayService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogAssay queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BlogAssay> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param blogAssay 实例对象
     * @return 实例对象
     */
    BlogAssay insert(BlogAssay blogAssay);

    /**
     * 修改数据
     *
     * @param blogAssay 实例对象
     * @return 实例对象
     */
    BlogAssay update(BlogAssay blogAssay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}