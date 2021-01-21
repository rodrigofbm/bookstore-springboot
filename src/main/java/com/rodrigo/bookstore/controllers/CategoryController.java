package com.rodrigo.bookstore.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.bookstore.DTOs.CategoryDTO;
import com.rodrigo.bookstore.domain.Category;
import com.rodrigo.bookstore.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id) {
		Category category = categoryService.findById(id);
		CategoryDTO categoryDto = new CategoryDTO(category);
		
		return ResponseEntity.ok(categoryDto);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> list = categoryService.findAll();
		List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok(listDto);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> save(@Valid @RequestBody Category category) {
		Category newCategory = categoryService.save(category);
		CategoryDTO categoryDTO = new CategoryDTO(newCategory);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).body(categoryDTO);
	}
	
	@PutMapping
	public ResponseEntity<CategoryDTO> replace(@RequestBody Category category) {
		Category updatedCategory = categoryService.replace(category);
		CategoryDTO categoryDTO = new CategoryDTO(updatedCategory);
		
		return ResponseEntity.ok(categoryDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
