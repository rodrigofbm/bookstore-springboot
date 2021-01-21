package com.rodrigo.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rodrigo.bookstore.domain.Category;
import com.rodrigo.bookstore.repositories.CategoryRepository;
import com.rodrigo.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Category findById(Integer id) {
		return categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Category not found"));
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category save(Category category) {
		category.setId(null);

		return categoryRepository.save(category);
	}

	public Category replace(Category category) {
		Category cat = this.findById(category.getId());
		cat.setDescription(category.getDescription());
		cat.setName(category.getName());

		return categoryRepository.save(cat);
	}

	public void delete(Integer id) {
		Category category = this.findById(id);

		try {
			categoryRepository.delete(category);
		} catch (DataIntegrityViolationException e) {
			throw new com.rodrigo.bookstore.services.exceptions.DataIntegrityViolationException(
					"Category can't be deleted. Exists books associated to it.");
		}
	}
}
