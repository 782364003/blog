package com.sen.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sen.entity.RequestLog;

public interface RequestLogService extends IService<RequestLog> {
    PageInfo<RequestLog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<RequestLog> queryWrapper);
}
