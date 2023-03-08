package com.sen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sen.entity.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<Tag> getIndexTags();
}
