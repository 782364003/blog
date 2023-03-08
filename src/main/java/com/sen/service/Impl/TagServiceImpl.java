package com.sen.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sen.entity.Tag;
import com.sen.mapper.TagMapper;
import com.sen.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getIndexTags() {
        return tagMapper.getIndexTag();
    }
}
