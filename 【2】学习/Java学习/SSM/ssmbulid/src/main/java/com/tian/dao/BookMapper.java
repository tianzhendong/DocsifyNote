package com.tian.dao;

import com.tian.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Tian
 * @time: 2021-08-15 22:44
 **/
public interface BookMapper {
	//add
	int addBook(Books books);

	//delete
	int deleteBook(@Param("bookID") int id);

	//update
	int updateBook(Books books);

	//select one
	Books selectBookById(@Param("bookID") int id);

	//select all
	List<Books> selectBookAll();
}
