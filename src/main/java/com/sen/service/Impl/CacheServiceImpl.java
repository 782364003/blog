package com.sen.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.sen.aspect.MyCache;
import com.sen.entity.*;
import com.sen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private FriendService friendService;

    @Override
    @MyCache
    public PageInfo<Blog> getIndexPage(String title, Integer pageNum) {
        return blogService.getIndexPage(title, pageNum);
    }

    @Override
    @MyCache
    public List<Type> getIndexTypes() {
        return typeService.getIndexTypes();
    }

    @Override
    @MyCache
    public List<Tag> getIndexTags() {
        return tagService.getIndexTags();
    }

    @Override
    @MyCache
    public Integer getPublishedBlogNum() {
        return blogService.count(new LambdaQueryWrapper<Blog>().eq(Blog::getPublished, true));
    }

    @Override
    @MyCache
    public Integer getTypeNum() {
        return typeService.count();
    }

    @Override
    @MyCache
    public Integer getTagNum() {
        return tagService.count();
    }

    @Override
    @MyCache
    public Integer getCommentNum() {
        return commentService.count();
    }

    @Override
    @MyCache
    public User getAdminInfo() {
        return userService.getAdminInfo();
    }

    @Override
    @MyCache
    public List<String> getPermissionList(Long usId) {
        return userService.getPermissionList(usId);
    }

    @Override
    @MyCache
    public PageInfo<Blog> getPageByType(Integer pageNum, Long tyId) {
        return blogService.getPageByType(pageNum, tyId);
    }

    @Override
    @MyCache
    public PageInfo<Blog> getPageByTag(Integer pageNum, Long taId) {
        return blogService.getPageByTag(pageNum, taId);
    }

    @Override
    @MyCache
    public Map findTimeLine() {
        return blogService.findTimeLine();
    }

    @Override
    @MyCache
    public List<Friend> getIndexFriends() {
        return friendService.list();
    }
}
