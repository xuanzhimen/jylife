package com.itheima.service.impl;

import com.itheima.domain.BlogAssay;
import com.itheima.dao.BlogAssayDao;
import com.itheima.service.BlogAssayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BlogAssay)表服务实现类
 *
 * @author makejava
 * @since 2020-07-03 07:55:55
 */
@Service("blogAssayService")
public class BlogAssayServiceImpl implements BlogAssayService {
    @Resource
    private BlogAssayDao blogAssayDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogAssay queryById(Integer id) {
        return this.blogAssayDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BlogAssay> queryAllByLimit(int offset, int limit) {
        return this.blogAssayDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param blogAssay 实例对象
     * @return 实例对象
     */
    @Override
    public BlogAssay insert(BlogAssay blogAssay) {
        this.blogAssayDao.insert(blogAssay);
        return blogAssay;
    }

    /**
     * 修改数据
     *
     * @param blogAssay 实例对象
     * @return 实例对象
     */
    @Override
    public BlogAssay update(BlogAssay blogAssay) {
        this.blogAssayDao.update(blogAssay);
        return this.queryById(blogAssay.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blogAssayDao.deleteById(id) > 0;
    }
}