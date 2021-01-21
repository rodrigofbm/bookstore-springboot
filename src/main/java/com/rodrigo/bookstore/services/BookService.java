package com.rodrigo.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.bookstore.domain.Book;
import com.rodrigo.bookstore.domain.Category;
import com.rodrigo.bookstore.repositories.BookRepository;
import com.rodrigo.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryService categoryService;
	
	public Book findById(Integer id) {
		return bookRepository.findById(id)
					.orElseThrow(() -> new ObjectNotFoundException("Book not found"));
	}
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public List<Book> findAllByCategory(Integer categoryId) {
		categoryService.findById(categoryId);
		
		return bookRepository.findAllByCategory(categoryId);
	}

	public Book replace(Integer id, Book replacedBook) {
		Book savedBook =  findById(id);
		savedBook.setAuthorName(replacedBook.getAuthorName());
		savedBook.setText(replacedBook.getText());
		savedBook.setTitle(replacedBook.getTitle());
		
		
		return bookRepository.save(savedBook);
	}

	public Book create(Book book, Integer categoryId) {
		Category category = categoryService.findById(categoryId);
		book.setCategory(category);
		book.setId(null);
		
		return bookRepository.save(book);	
	}

	public void delete(Integer id) {
		Book book = findById(id);
		
		bookRepository.delete(book);
	}
}
