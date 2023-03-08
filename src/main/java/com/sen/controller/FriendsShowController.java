package com.sen.controller;

import com.sen.aspect.MyLog;
import com.sen.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendsShowController {
    @Autowired
    private CacheService cacheService;

    @MyLog
    @GetMapping("/friends")
    public String friends(Model model) {
        model.addAttribute("friends", cacheService.getIndexFriends());
        model.addAttribute("user", cacheService.getAdminInfo());
        return "friends";
    }
}
