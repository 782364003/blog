package com.sen.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.entity.sys.SysRole;
import com.sen.mapper.sys.SysRoleMapper;
import com.sen.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> getListByPage(Integer pageNum, Integer pageSize, Wrapper<SysRole> queryWrapper) {
        PageHelper.startPage(pageNum, 5);
        List<SysRole> list = this.list(queryWrapper);
        PageInfo<SysRole> result = new PageInfo(list);
        return result;
    }

    @Override
    @Transactional
    public Boolean addRoleWithMenuBatch(SysRole sysRole) {
        this.save(sysRole);
        if(Objects.nonNull(sysRole.getMenuIds()) && sysRole.getMenuIds().length > 0) {
            List<Long> menuIds = Arrays.asList(sysRole.getMenuIds());
            sysRoleMapper.addRoleMenuBatch(sysRole.getRoleId(), menuIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean updateRoleWithMenuBatch(SysRole sysRole) {
        this.updateById(sysRole);
        sysRoleMapper.deleteRoleMenuBatch(sysRole.getRoleId());
        if (Objects.nonNull(sysRole.getMenuIds()) && sysRole.getMenuIds().length > 0) {
            List<Long> menuIds = Arrays.asList(sysRole.getMenuIds());
            sysRoleMapper.addRoleMenuBatch(sysRole.getRoleId(), menuIds);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteByIdsWithMenuBatch(List<Long> roleIds) {
        this.removeByIds(roleIds);
        roleIds.stream().forEach(id -> {
            sysRoleMapper.deleteRoleMenuBatch(id);
        });
        return true;
    }
}
