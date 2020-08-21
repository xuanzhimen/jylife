package com.itheima.service.impl;

import com.itheima.domain.TodoListTask;
import com.itheima.dao.TodoListTaskDao;
import com.itheima.service.TodoListTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TodoListTask)表服务实现类
 *
 * @author makejava
 * @since 2020-07-03 07:55:57
 */
@Service("todoListTaskService")
public class TodoListTaskServiceImpl implements TodoListTaskService {
    @Resource
    private TodoListTaskDao todoListTaskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    @Override
    public TodoListTask queryById(Integer tid) {
        return this.todoListTaskDao.queryById(tid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TodoListTask> queryAllByLimit(int offset, int limit) {
        return this.todoListTaskDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param todoListTask 实例对象
     * @return 实例对象
     */
    @Override
    public TodoListTask insert(TodoListTask todoListTask) {
        this.todoListTaskDao.insert(todoListTask);
        return todoListTask;
    }

    /**
     * 修改数据
     *
     * @param todoListTask 实例对象
     * @return 实例对象
     */
    @Override
    public TodoListTask update(TodoListTask todoListTask) {
        this.todoListTaskDao.update(todoListTask);
        return this.queryById(todoListTask.getTid());
    }

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tid) {
        return this.todoListTaskDao.deleteById(tid) > 0;
    }

}