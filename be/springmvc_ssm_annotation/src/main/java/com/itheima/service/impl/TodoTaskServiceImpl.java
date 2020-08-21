package com.itheima.service.impl;

import com.itheima.domain.TodoTask;
import com.itheima.dao.TodoTaskDao;
import com.itheima.service.TodoTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * (TodoTask)表服务实现类
 *
 * @author makejava
 * @since 2020-07-03 07:55:57
 */
@Service("todoTaskService")
public class TodoTaskServiceImpl implements TodoTaskService {
    @Resource
    private TodoTaskDao todoTaskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TodoTask queryById(Integer id) {
        return this.todoTaskDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TodoTask> queryAllByLimit(int offset, int limit) {
        return this.todoTaskDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param todoTask 实例对象
     * @return 实例对象
     */
    @Override
    public TodoTask insert(TodoTask todoTask) {
        this.todoTaskDao.insert(todoTask);
        return todoTask;
    }

    /**
     * 修改数据
     *
     * @param todoTask 实例对象
     * @return 实例对象
     */
    @Override
    public TodoTask update(TodoTask todoTask) {
        this.todoTaskDao.update(todoTask);
        return this.queryById(todoTask.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.todoTaskDao.deleteById(id) > 0;
    }

    @Override
    public List<TodoTask> selectUnfinishTask(Integer uid) {
        return this.todoTaskDao.selectUnfinishTask(uid);
    }

    @Override
    public int finish(TodoTask todoTask) {
        return this.todoTaskDao.finish(todoTask);
    }

    @Override
    public List<TodoTask> getAllTodoTask(Integer uid) {
        List<TodoTask> unfinishTodoTask = todoTaskDao.getUnfinishTodoTask(uid);
        List<TodoTask> finishTodoTask = todoTaskDao.getFinishTodoTask(uid);
        unfinishTodoTask.addAll(finishTodoTask);
        return unfinishTodoTask;
    }
}