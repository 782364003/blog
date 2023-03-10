package com.sen.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sen.entity.sys.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    PageInfo<SysRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<SysRole> queryWrapper);

    Boolean addRoleWithMenuBatch(SysRole sysRole);

    Boolean updateRoleWithMenuBatch(SysRole sysRole);

    Boolean deleteByIdsWithMenuBatch(List<Long> roleIds);
}
