package com.sen.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sen.entity.Blog;
import com.sen.entity.Comment;

import java.util.List;
import java.util.Map;

public interface BlogService extends IService<Blog> {

    PageInfo<Blog> getIndexPage(String title, Integer pageNum);

    PageInfo<Blog> getPageByType(Integer pageNum, Long tyId);

    PageInfo<Blog> getPageByTag(Integer pageNum, Long taId);

    PageInfo<Blog> getListByPage(Integer pageNum, Integer pageSize);

    PageInfo<Blog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<Blog> queryWrapper);

    boolean setPublished(Long blId, boolean flag);

    Blog findFullById(Long blId);

    Blog findBlogWithTagsIdsById(Long blId);

    boolean addViews(Long blId);

    Map findTimeLine();

    Boolean updateBlogWithTag(Blog blog);

    Boolean addBlogWithTag(Blog blog);

    Boolean deleteBlogWithTag(Long blId);

    List<Comment> getCommentWithChildById(Long blId);

}
