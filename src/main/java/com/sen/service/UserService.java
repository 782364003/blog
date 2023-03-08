package com.sen.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sen.entity.MyUser;
import com.sen.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    User getAdminInfo();

    User verifyLogin(String username, String password);

    List<String> getPermissionList(Long usId);

    boolean isAdmin(Long usId);

    PageInfo<User> getListByPage(Integer pageNum, Integer pageSize, Wrapper<User> queryWrapper);

    MyUser getMyUserById(Long usId);

    User generateUserByGithubUsId(Long githubUsId, User saveUser);
}
