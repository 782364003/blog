package com.sen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sen.entity.Comment;

public interface CommentService extends IService<Comment> {
    PageInfo<Comment> getListByPage(Integer pageNum, Integer pageSize);
    boolean setDeleted(Long coId, boolean flag);
}
