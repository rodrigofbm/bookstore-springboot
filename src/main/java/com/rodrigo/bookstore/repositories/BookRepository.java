package com.rodrigo.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigo.bookstore.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query(value = "SELECT obj FROM Book obj WHERE obj.category.id = :category_id ORDER BY title")
	List<Book> findAllByCategory(@Param("category_id") Integer categoryId);
	
}
