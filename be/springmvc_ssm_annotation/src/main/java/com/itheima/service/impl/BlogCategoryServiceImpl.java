package com.itheima.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.dao.BlogAssayDao;
import com.itheima.domain.BlogAssay;
import com.itheima.domain.BlogCategory;
import com.itheima.dao.BlogCategoryDao;
import com.itheima.service.BlogCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BlogCategory)表服务实现类
 *
 * @author makejava
 * @since 2020-07-03 07:55:57
 */
@Service("blogCategoryService")
public class BlogCategoryServiceImpl implements BlogCategoryService {
    @Resource
    private BlogAssayDao blogAssayDao;
    @Resource
    private BlogCategoryDao blogCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogCategory queryById(Integer id) {
        return this.blogCategoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BlogCategory> queryAllByLimit(int offset, int limit) {
        return this.blogCategoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    @Override
    public BlogCategory insert(BlogCategory blogCategory) {
        this.blogCategoryDao.insert(blogCategory);
        return blogCategory;
    }

    /**
     * 修改数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    @Override
    public BlogCategory update(BlogCategory blogCategory) {
        this.blogCategoryDao.update(blogCategory);
        return this.queryById(blogCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blogCategoryDao.deleteById(id) > 0;
    }


    @Override
    public String findAllCategoryAndAssayTitle(Integer uid) {
        JSONObject o0 = new JSONObject();
        JSONArray a0 = new JSONArray();
        o0.put("data", a0);
        // 查询用户下pid为0的数据
        List<BlogCategory> cate1 = this.queryByUidAndPid(uid, 0);
        // 遍历pid为0的数据递归查询两次
        for (int i = 0; i < cate1.size(); i++) {
            JSONObject o1 = new JSONObject();
            JSONArray a1 = new JSONArray();
            o1.put("id", cate1.get(i).getId());
            o1.put("name", cate1.get(i).getName());
            o1.put("children", a1);
            a0.add(o1);
            List<BlogCategory> cate2 = this.queryByPId(cate1.get(i).getId());
            for (int j = 0; j < cate2.size(); j++) {
                JSONObject o2 = new JSONObject();
                JSONArray a2 = new JSONArray();
                o2.put("id", cate2.get(j).getId());
                o2.put("name", cate2.get(j).getName());
                o2.put("children", a2);
                a1.add(o2);
                List<BlogCategory> cate3 = this.queryByPId(cate2.get(j).getId());
                // 根据三级目录id查询assay表记录name
                for (int k = 0; k < cate3.size(); k++) {
                    JSONObject o3 = new JSONObject();
                    JSONArray a3 = new JSONArray();
                    o3.put("id", cate3.get(k).getId());
                    o3.put("name", cate3.get(k).getName());
                    o3.put("children", a3);
                    a2.add(o3);
                    List<BlogAssay> assays = blogAssayDao.queryByCid(cate3.get(k).getId());
                    for (int l = 0; l < assays.size(); l++) {
                        JSONObject o4 = new JSONObject();
                        o4.put("id", assays.get(l).getCid() + "-" + assays.get(l).getId());
                        o4.put("name", assays.get(l).getTitle());
                        a3.add(o4);
                    }
                }
            }
        }
        System.out.println(o0.toJSONString());
        return o0.toJSONString();
    }

    private List<BlogCategory> queryByPId(Integer pid) {
        return this.blogCategoryDao.queryByPid(pid);
    }

    private List<BlogCategory> queryByUidAndPid(Integer uid, Integer pid) {
        return this.blogCategoryDao.queryByUidAndPid(uid, pid);
    }
}