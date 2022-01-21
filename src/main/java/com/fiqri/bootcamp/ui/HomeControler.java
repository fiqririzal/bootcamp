package com.fiqri.bootcamp.ui;

import com.fiqri.bootcamp.dao.DepartmentJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControler {
    @Autowired
    DepartmentJdbcDao DepartmentJdbcDao;

    @GetMapping("/halo")
    public String teshalo(Model model) {
        model.addAttribute("pesan", "Ada Apa sih Kawan");
        model.addAttribute("dept", DepartmentJdbcDao.getListDepartmentPS());

        return "halo";
    }
}
