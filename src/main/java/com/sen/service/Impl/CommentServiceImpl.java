package com.sen.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.entity.Comment;
import com.sen.mapper.CommentMapper;
import com.sen.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public PageInfo<Comment> getListByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, 5);
        List<Comment> list = this.list();
        PageInfo<Comment> result = new PageInfo<>(list);
        return result;
    }

    @Override
    public boolean setDeleted(Long coId, boolean flag) {
        return this.update(new LambdaUpdateWrapper<Comment>().eq(Comment::getCoId, coId).set(Comment::getIsDelete, flag));
    }
}
