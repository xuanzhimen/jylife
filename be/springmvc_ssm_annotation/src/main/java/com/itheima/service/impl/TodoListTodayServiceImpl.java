package com.itheima.service.impl;

import com.itheima.domain.TodoListToday;
import com.itheima.dao.TodoListTodayDao;
import com.itheima.domain.TodoTask;
import com.itheima.service.TodoListTodayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * (TodoListToday)表服务实现类
 *
 * @author makejava
 * @since 2020-07-03 07:55:57
 */
@Service("todoListTodayService")
public class TodoListTodayServiceImpl implements TodoListTodayService {
    @Resource
    private TodoListTodayDao todoListTodayDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TodoListToday queryById(Integer id) {
        return this.todoListTodayDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TodoListToday> queryAllByLimit(int offset, int limit) {
        return this.todoListTodayDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param todoListToday 实例对象
     * @return 实例对象
     */
    @Override
    public TodoListToday insert(TodoListToday todoListToday) {
        this.todoListTodayDao.insert(todoListToday);
        return todoListToday;
    }

    /**
     * 修改数据
     *
     * @param todoListToday 实例对象
     * @return 实例对象
     */
    @Override
    public TodoListToday update(TodoListToday todoListToday) {
        this.todoListTodayDao.update(todoListToday);
        return this.queryById(todoListToday.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.todoListTodayDao.deleteById(id) > 0;
    }


    @Override
    public List<TodoListToday> selectUnfinishTodo(Integer uid) {
        Date cur = new Date();
        int year = cur.getYear();
        int month = cur.getMonth();
        int date = cur.getDate();
        Date date1 = new Date(year, month, date);
        long time1 = date1.getTime();
        long time2 = time1 + 86400000;
        List<TodoListToday> todoListTodays = this.todoListTodayDao.selectUnfinishTodo(uid, time1, time2);
        return todoListTodays;
    }

    @Override
    public int finish(TodoListToday todoTask) {
        return this.todoListTodayDao.finish(todoTask);
    }

    @Override
    public int beginTodo(TodoListToday todoTask) {
        return this.todoListTodayDao.beginTodo(todoTask);
    }
    @Override
    public int finishTodo(TodoListToday todoTask) {
        return this.todoListTodayDao.finishTodo(todoTask);
    }

    @Override
    public List<TodoListToday> selectFinishTodo(Integer uid) {
        Date cur = new Date();
        int year = cur.getYear();
        int month = cur.getMonth();
        int date = cur.getDate();
        Date date1 = new Date(year, month, date);
        long time1 = date1.getTime();
        long time2 = time1 + 86400000;
        List<TodoListToday> todoListTodays = this.todoListTodayDao.selectFinishTodo(uid, time1, time2);
        return todoListTodays;
    }

}