package com.sen.controller;

import com.sen.aspect.MyLog;
import com.sen.entity.Blog;
import com.sen.service.BlogService;
import com.sen.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CacheService cacheService;

    @MyLog
    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer pageNum, @RequestParam(required = false, value = "title") String title, Model model){
        model.addAttribute("page", cacheService.getIndexPage(title, pageNum));
        model.addAttribute("types", cacheService.getIndexTypes());
        model.addAttribute("tags", cacheService.getIndexTags());
        model.addAttribute("blogsCount", cacheService.getPublishedBlogNum());
        model.addAttribute("typesCount", cacheService.getTypeNum());
        model.addAttribute("tagsCount", cacheService.getTagNum());
        model.addAttribute("commentsCount", cacheService.getCommentNum());
        model.addAttribute("user", cacheService.getAdminInfo());
        return "index";
    }

    @GetMapping("/blog")
    public String blog() { return "blog"; }

    @MyLog
    @GetMapping("/blog/{blId}")
    public String blog(@PathVariable Long blId, Model model) {
        Blog blog = blogService.findFullById(blId);
        blogService.addViews(blId);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源！");
        }
        model.addAttribute("blog", blog);
        model.addAttribute("user", cacheService.getAdminInfo());
        return "blog";
    }
}
