package com.sen.controller.Admin;

import com.sen.aspect.MyLog;
import com.sen.entity.Result.JsonResult;
import com.sen.entity.Result.ResultCode;
import com.sen.entity.Result.ResultUtil;
import com.sen.entity.Type;
import com.sen.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @MyLog
    @PreAuthorize("hasAuthority('content:type:add')")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody Type type) {
        boolean bool = typeService.save(type);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:type:edit')")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody Type type) {
        if (Objects.isNull(type.getTyId())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = typeService.updateById(type);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @PreAuthorize("hasAuthority('content:type:remove')")
    @GetMapping("/delete/{idNum}")
    public JsonResult removeById(@PathVariable("idNum") Long tyId) {
        boolean bool = typeService.removeById(tyId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }


    @PreAuthorize("hasAuthority('content:type:query')")
    @GetMapping("/find/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long tyId) {
        Type type = typeService.getById(tyId);
        return ResultUtil.success(type, ResultCode.SUCCESS);
    }


    @PreAuthorize("hasAuthority('content:type:query')")
    @GetMapping("/list")
    public JsonResult list() {
        List<Type> typeList = typeService.list();
        return ResultUtil.success(typeList, ResultCode.SUCCESS);
    }
}
