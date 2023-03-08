package com.sen.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sen.entity.Type;
import com.sen.mapper.TypeMapper;
import com.sen.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getIndexTypes() {
        return typeMapper.getIndexTypes();
    }
}
