package cn.lngex.securitydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @PreAuthorize("hasAuthority('dept:list')")
    @RequestMapping("/dept/list")
    public String list() {
        return "dept.list";
    }

    @PreAuthorize("hasAuthority('dept:add')")
    @RequestMapping("/dept/add")
    public String add() {
        return "dept.add";
    }

    @PreAuthorize("hasAuthority('dept:update')")
    @RequestMapping("/dept/update")
    public String update() {
        return "dept.update";
    }

    @PreAuthorize("hasAuthority('dept:delete')")
    @RequestMapping("/dept/delete")
    public String delete() {
        return "dept.delete";
    }
}