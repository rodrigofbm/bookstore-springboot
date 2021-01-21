package com.rodrigo.bookstore.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.bookstore.DTOs.BookDTO;
import com.rodrigo.bookstore.domain.Book;
import com.rodrigo.bookstore.services.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<BookDTO> save(@RequestParam(value = "category", defaultValue = "0") Integer categoryId, @Valid @RequestBody Book book) {
		BookDTO bookDto = new BookDTO(bookService.create(book, categoryId));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(bookDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BookDTO> findById(@PathVariable Integer id) {
		Book book = bookService.findById(id);
		BookDTO bookDto = new BookDTO(book);
		
		return ResponseEntity.ok(bookDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<BookDTO> delete(@PathVariable Integer id) {
		bookService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * @GetMapping public ResponseEntity<List<BookDTO>> findAll() { List<Book> books
	 * = bookService.findAll(); List<BookDTO> booksDto = books.stream().map(x -> new
	 * BookDTO(x)).collect(Collectors.toList());
	 * 
	 * return ResponseEntity.ok(booksDto); }
	 */
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAllByCategory(@RequestParam(value = "category", defaultValue = "0") Integer categoryId) {
		List<Book> books = bookService.findAllByCategory(categoryId);
		List<BookDTO> booksDto = books.stream().map(x -> new BookDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok(booksDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BookDTO> replace(@PathVariable Integer id, @Valid @RequestParam  Book book) {
		Book replacedBook = bookService.replace(id, book);
		BookDTO bookDto = new BookDTO(replacedBook);
		
		return ResponseEntity.ok(bookDto);
	}
}
