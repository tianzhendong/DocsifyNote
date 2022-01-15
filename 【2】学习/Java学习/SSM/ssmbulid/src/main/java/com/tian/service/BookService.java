package com.tian.service;

import com.tian.pojo.Books;

import java.util.List;

/**
 * @author: Tian
 * @time: 2021-08-15 23:11
 **/
public interface BookService {
	//add
	int addBook(Books books);

	//delete
	int deleteBook(int id);

	//update
	int updateBook(Books books);

	//select one
	Books selectBookById(int id);

	//select all
	List<Books> selectBookAll();
}
