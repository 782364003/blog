package com.sen.controller;

import com.sen.aspect.MyLog;
import com.sen.entity.Blog;
import com.sen.entity.Comment;
import com.sen.service.BlogService;
import com.sen.service.CommentService;
import com.sen.util.IpUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentControllers {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MyLog
    @GetMapping("/comments/{blId}")
    public String comments(@PathVariable Long blId, Model model) {
        Blog blog = blogService.findFullById(blId);
        if (!blog.getPublished()) {
            throw new RuntimeException("无效资源!");
        }
        model.addAttribute("blog", blog);
        return "blog :: commentList";
    }

    @MyLog
    @PostMapping("/comments")
    public String postComments(Comment comment, HttpServletRequest request) {
        //保存评论
        if (comment.getParentId() <= -1) comment.setParentId(null);
        String ipAddress = IpUtil.getIpAddress(request);
        comment.setIp_address(ipAddress);
        commentService.saveOrUpdate(comment);
        rabbitTemplate.convertAndSend("msg-event-exchange", "msg.wx-pn", formatWxMsg(comment));
        return "redirect:/comments/" + comment.getBlId();
    }

    private String formatWxMsg(Comment comment) {
        StringBuilder sb = new StringBuilder();
        sb.append("发送人:").append(comment.getName()).append(";");
        if (comment.getParentId() != null) {
            Comment parent = commentService.getById(comment.getParentId());
            if (parent != null) {
                sb.append("接收人:").append(parent.getName()).append(";");
            }
        }
        sb.append("内容:").append(comment.getContent()).append(";");
        return sb.toString();
    }
}
