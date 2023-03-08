package com.sen.controller;

import com.sen.aspect.MyLog;
import com.sen.entity.Type;
import com.sen.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private CacheService cacheService;

    @MyLog
    @GetMapping("/types/{tyId}")
    public String types(@PathVariable Long tyId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model){

        List<Type> types = cacheService.getIndexTypes();
        if(tyId==-1){
            tyId =types.get(0).getTyId();
        }
        model.addAttribute("types",types);
        model.addAttribute("page",cacheService.getPageByType(pageNum,tyId));
        model.addAttribute("activeTypeId",tyId);
        model.addAttribute("user",cacheService.getAdminInfo());
        return "types";
    }
}
