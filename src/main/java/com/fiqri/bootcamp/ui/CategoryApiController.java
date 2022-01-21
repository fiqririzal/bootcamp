package com.fiqri.bootcamp.ui;

import com.fiqri.bootcamp.dao.CategoryRepository;
import com.fiqri.bootcamp.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping ("/api/categories")
public class CategoryApiController {

    @Autowired
    CategoryRepository categoryrepo;

    @GetMapping("")
    public List<Category> list() {
        return this.categoryrepo.list();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category>
        getById(@PathVariable Integer id){
        try{
            Category category = this.categoryrepo.findById((id));
            return ResponseEntity.ok(category);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.noContent().build();
        }
    } @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Category category) throws SQLException {
        category = this.categoryrepo.insert(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category category) throws SQLException {
        category = this.categoryrepo.update(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws SQLException {
        this.categoryrepo.delete(id);
        return ResponseEntity.ok("Delete Success");
    }

    @GetMapping("/department")
    public List<Category> listDepart(){
        return this.categoryrepo.list();
    }
}

