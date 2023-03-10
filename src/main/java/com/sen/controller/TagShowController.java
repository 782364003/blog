package com.sen.controller;

import com.sen.aspect.MyLog;
import com.sen.entity.Tag;
import com.sen.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private CacheService cacheService;

    @MyLog
    @GetMapping("/tags/{taId}")
    public String tags(@PathVariable Long taId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model) {
        List<Tag> tags = cacheService.getIndexTags();
        if (taId == -1) {
            taId = tags.get(0).getTaId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", cacheService.getPageByTag(pageNum, taId));
        model.addAttribute("activeTagId", taId);
        model.addAttribute("user", cacheService.getAdminInfo());
        return "tags";
    }
}
