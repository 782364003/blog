package com.sen.controller.Admin;

import com.sen.entity.MyUser;
import com.sen.entity.Result.JsonResult;
import com.sen.entity.Result.ResultCode;
import com.sen.entity.Result.ResultUtil;
import com.sen.entity.other.TreeSelect;
import com.sen.entity.sys.SysMenu;
import com.sen.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public JsonResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId, MyUser myUser) {
        if (myUser == null) {
            return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
        }
        List<SysMenu> menus = sysMenuService.selectMenuList(myUser.getUsId());
        Map<String, Object> map = new HashMap<>();
        map.put("checkedKeys", sysMenuService.selectMenuListByRoleId(roleId));
        map.put("menus", sysMenuService.buildMenuTreeSelect(menus));
        return ResultUtil.success(map, ResultCode.SUCCESS);
    }


    /**
     * 加载菜单列表树
     */
    @GetMapping(value = "/treeselect")
    public JsonResult roleMenuTreeSelect(MyUser myUser) {
        if (myUser == null) {
            return ResultUtil.faile(ResultCode.Token_AUTH_ERROR);
        }
        List<SysMenu> menus = sysMenuService.selectMenuList(myUser.getUsId());
        List<TreeSelect> treeSelects = sysMenuService.buildMenuTreeSelect(menus);
        return ResultUtil.success(treeSelects, ResultCode.SUCCESS);
    }

}
