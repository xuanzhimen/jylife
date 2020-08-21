package com.itheima.controller;

import com.itheima.controller.results.Code;
import com.itheima.controller.results.Result;
import com.itheima.domain.BlogCategory;
import com.itheima.service.BlogCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BlogCategory)表控制层
 *
 * @author makejava
 * @since 2020-07-03 08:04:57
 */
@RestController
@RequestMapping("blogCategory")
public class BlogCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private BlogCategoryService blogCategoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BlogCategory selectOne(Integer id) {
        return this.blogCategoryService.queryById(id);
    }


    @GetMapping("queryById")
    public BlogCategory queryByPId(Integer id) {
        return this.blogCategoryService.queryById(id);
    }

    @PostMapping("insertCategory")
    public Result insertCategory(@RequestBody BlogCategory blogCategory) {
        BlogCategory flag = blogCategoryService.insert(blogCategory);
        return new Result(flag != null ? Code.SAVE_OK:Code.SAVE_ERROR);
    }

    @GetMapping(value = "findAllCategoryAndAssayTitle", produces = "text/html;charset=UTF-8")
    public String findAllCategoryAndAssayTitle(Integer uid) {
        long t1 = System.currentTimeMillis();
        String allCategoryAndAssayTitle = this.blogCategoryService.findAllCategoryAndAssayTitle(uid);
        System.out.println("===============" + (System.currentTimeMillis() - t1));
        return allCategoryAndAssayTitle;
    }

    @GetMapping("deleteCategory")
    public Result deleteCategory(Integer id) {
        Boolean flag = blogCategoryService.deleteById(id);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERROR);
    }
}