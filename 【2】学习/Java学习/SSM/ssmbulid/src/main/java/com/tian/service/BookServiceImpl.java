package com.tian.service;

import com.tian.dao.BookMapper;
import com.tian.pojo.Books;

import java.util.List;

/**
 * @author: Tian
 * @time: 2021-08-15 23:12
 **/
public class BookServiceImpl implements BookService{
	//业务层调用dao层：组合dao层
	private BookMapper bookMapper;

	public void setBookMapper(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	@Override
	public int addBook(Books books) {
		return bookMapper.addBook(books);
	}

	@Override
	public int deleteBook(int id) {
		return bookMapper.deleteBook(id);
	}

	@Override
	public int updateBook(Books books) {
		return bookMapper.updateBook(books);
	}

	@Override
	public Books selectBookById(int id) {
		return bookMapper.selectBookById(id);
	}

	@Override
	public List<Books> selectBookAll() {
		return bookMapper.selectBookAll();
	}
}
