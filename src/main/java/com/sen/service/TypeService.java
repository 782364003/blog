package com.sen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sen.entity.Type;

import java.util.List;

public interface TypeService extends IService<Type> {
    List<Type> getIndexTypes();
}
