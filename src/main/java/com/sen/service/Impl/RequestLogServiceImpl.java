package com.sen.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sen.entity.RequestLog;
import com.sen.mapper.RequestLogMapper;
import com.sen.service.RequestLogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestLogServiceImpl extends ServiceImpl<RequestLogMapper, RequestLog> implements RequestLogService {
    @Override
    public PageInfo<RequestLog> getListByPage(Integer pageNum, Integer pageSize, Wrapper<RequestLog> queryWrapper) {
        PageHelper.startPage(pageNum, 5);
        List<RequestLog> list = this.list(queryWrapper);
        PageInfo<RequestLog> result = new PageInfo<>(list);
        return result;
    }
}
