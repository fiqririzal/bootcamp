package com.fiqri.bootcamp;

import com.fiqri.bootcamp.dao.CategoryRepository;
import com.fiqri.bootcamp.models.Category;
import com.fiqri.bootcamp.models.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootTest
class BootcampApplicationTests {
	@Autowired
	private CategoryRepository categoryRepo;

	@Test
	void testInsert() {
		Category category = new Category(null, 1, "bandung", "bandung");
		category = this.categoryRepo.insert(category);
		System.out.println(category.toString());
	}

	@Test
	void testUpdate() {
		Category category = new Category(26, 1, "bandung", "aku cinta bandung");
		category = this.categoryRepo.update(category);
		System.out.println(category.toString());
	}
}
