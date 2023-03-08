package com.sen.controller;

import com.sen.aspect.MyLog;
import com.sen.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveShowController {
    @Autowired
    private CacheService cacheService;


    @MyLog
    @GetMapping("/archives")
    public String archivesByYearAndMonth(Model model){
        model.addAttribute("timeLineMap",cacheService.findTimeLine());
        model.addAttribute("user",cacheService.getAdminInfo());
        return "archives";
    }
}
