package com.sen.controller.Admin;

import com.sen.aspect.MyLog;
import com.sen.entity.Comment;
import com.sen.entity.Result.JsonResult;
import com.sen.entity.Result.ResultCode;
import com.sen.entity.Result.ResultUtil;
import com.sen.service.BlogService;
import com.sen.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @PreAuthorize("hasAuthority('content:comment:query')")
    @GetMapping("/getCommentWithChildById/{idNum}")
    public JsonResult getCommentWithChildById(@PathVariable("idNum") Long blId) {
        List<Comment> commentList = blogService.getCommentWithChildById(blId);
        return ResultUtil.success(commentList, ResultCode.SUCCESS);

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:comment:delete')")
    @PostMapping("/setDeleted")
    public JsonResult setPublished(@RequestBody Comment comment) {
        if (Objects.isNull(comment.getCoId()) || Objects.isNull(comment.getIsDelete())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = commentService.setDeleted(comment.getCoId(), comment.getIsDelete());
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

}
