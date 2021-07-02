package cn.lngex.securitydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @PreAuthorize("hasAuthority('employee:update')")
    @RequestMapping("/employee/list")
    public String list() {
        return "employee.list";
    }

    @PreAuthorize("hasAuthority('employee:update')")
    @RequestMapping("/employee/add")
    public String add() {
        return "employee.add";
    }

    @PreAuthorize("hasAuthority('employee:update')")
    @RequestMapping("/employee/update")
    public String update() {
        return "employee.update";
    }

    @PreAuthorize("hasAuthority('employee:delete')")
    @RequestMapping("/employee/delete")
    public String delete() {
        return "employee.delete";
    }
}