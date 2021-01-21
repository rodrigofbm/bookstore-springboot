package com.rodrigo.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.bookstore.domain.Book;
import com.rodrigo.bookstore.domain.Category;
import com.rodrigo.bookstore.repositories.BookRepository;
import com.rodrigo.bookstore.repositories.CategoryRepository;

@Service
public class DbService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BookRepository bookRepository;
	
	public void databaseInstance() {
		Category c1 = new Category(null, "Infomática", "Livros de TI");
		Category c2 = new Category(null, "Ficção Científica", "Livros de Ficção Científica");
		Category c3 = new Category(null, "Biografia", "Livro de Biografias");
		
		Book l1 = new Book(null, "Clean Code", "Robert Martin", "Lorem Ipsum", c1);
		Book l2 = new Book(null, "Engenharia de Software", "Luis V.", "Lorem Ipsum", c1);
		Book l3 = new Book(null, "The Time Machin", "H.G Wells", "Lorem Ipsum", c2);
		Book l4 = new Book(null, "The War of The Worlds", "H.G Wells", "Lorem Ipsum", c2);
		Book l5 = new Book(null, "I, Roboot", "Isaac Asimov", "Lorem Ipsum", c2);
		
		c1.getBooks().addAll(Arrays.asList(l1));
		
		categoryRepository.saveAll(Arrays.asList(c1,c2, c3));
		bookRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5));
	}
}
