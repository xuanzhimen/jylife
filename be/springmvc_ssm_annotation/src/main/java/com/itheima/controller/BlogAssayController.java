package com.itheima.controller;

import com.itheima.controller.results.Code;
import com.itheima.controller.results.Result;
import com.itheima.domain.BlogAssay;
import com.itheima.domain.BlogCategory;
import com.itheima.service.BlogAssayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BlogAssay)表控制层
 *
 * @author makejava
 * @since 2020-07-03 08:04:55
 */
@RestController
@RequestMapping("blogAssay")
public class BlogAssayController {
    /**
     * 服务对象
     */
    @Resource
    private BlogAssayService blogAssayService;

    @PostMapping("update")
    public Result update(@RequestBody BlogAssay blogAssay) {
        BlogAssay flag = blogAssayService.update(blogAssay);
        return new Result(flag != null ? Code.SAVE_OK:Code.SAVE_ERROR);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BlogAssay selectOne(Integer id) {
        return this.blogAssayService.queryById(id);
    }

    @PostMapping("insertAssay")
    public Result insertCategory(@RequestBody BlogAssay blogAssay) {
        BlogAssay flag = blogAssayService.insert(blogAssay);
        return new Result(flag != null ? Code.SAVE_OK:Code.SAVE_ERROR);
    }

}