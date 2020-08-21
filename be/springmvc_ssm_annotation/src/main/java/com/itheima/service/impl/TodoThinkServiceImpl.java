package com.itheima.service.impl;

import com.itheima.dao.TodoThinkDao;
import com.itheima.domain.TodoThink;
import com.itheima.service.TodoThinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TodoThink)表服务实现类
 *
 * @author makejava
 * @since 2020-07-07 00:52:13
 */
@Service("todoThinkService")
public class TodoThinkServiceImpl implements TodoThinkService {
    @Resource
    private TodoThinkDao todoThinkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TodoThink queryById(Integer id) {
        return this.todoThinkDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TodoThink> queryAllByLimit(int offset, int limit) {
        return this.todoThinkDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param todoThink 实例对象
     * @return 实例对象
     */
    @Override
    public TodoThink insert(TodoThink todoThink) {
        this.todoThinkDao.insert(todoThink);
        return todoThink;
    }

    /**
     * 修改数据
     *
     * @param todoThink 实例对象
     * @return 实例对象
     */
    @Override
    public TodoThink update(TodoThink todoThink) {
        this.todoThinkDao.update(todoThink);
        return this.queryById(todoThink.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.todoThinkDao.deleteById(id) > 0;
    }

    @Override
    public List<TodoThink> selectAll(Integer uid) {
        return this.todoThinkDao.selectAll(uid);
    }
}