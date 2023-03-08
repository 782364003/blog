package com.sen.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sen.entity.Friend;
import com.sen.mapper.FriendMapper;
import com.sen.service.FriendService;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
}
