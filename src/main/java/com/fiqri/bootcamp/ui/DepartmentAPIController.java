package com.fiqri.bootcamp.ui;

import com.fiqri.bootcamp.dao.DepartmentJdbcDao;
import com.fiqri.bootcamp.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentAPIController {

    @Autowired
    private DepartmentJdbcDao dept;

    @GetMapping("/findById/{depId}")
    public ResponseEntity<Department> findById(@PathVariable("depId") Integer id) {
        try {
            Department dep = dept.findById(id);
            return ResponseEntity.ok(dep);
        } catch (EmptyResultDataAccessException ertdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/list")
    public List<Department> list() {
        return this.dept.list();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Department dep) {
        dep = this.dept.insert(dep);
        if (dep.getId() == null) {
            return ResponseEntity.internalServerError().body("....");
        } else {
            return ResponseEntity.ok(dep);
        }
    }
}
