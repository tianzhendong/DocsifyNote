package com.tian.controller;

import com.tian.pojo.Books;
import com.tian.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: Tian
 * @time: 2021-08-16 01:19
 **/
@Controller
@RequestMapping("/book")
public class BookController {
	//controller层调用service层
	@Autowired
	@Qualifier("BookServiceImpl")
	private BookService bookService;

	//查询全部书籍，并返回书籍展示页面
	@RequestMapping("/allBook")
	public String selectAllBook(Model model) {
		List<Books> books = bookService.selectBookAll();
		model.addAttribute("list", books);
		return "allBook";
	}

	//	跳转到添加书籍界面
	@RequestMapping("/toAddBook")
	public String toAddBook() {
		return "addBook";
	}

	//添加书籍请求
	@RequestMapping("/addBook")
	public String addBook(Books book) {
		bookService.addBook(book);
		return "redirect:/book/allBook";
	}

	//跳转到修改请求页面
	@RequestMapping("/toUpdateBook/{bookId}")
	public String toUpdateBook(@PathVariable("bookId") int id, Model model) {
		Books books = bookService.selectBookById(id);
		model.addAttribute("bookSelected", books);
		return "updateBook";
	}

	//修改书籍
	@RequestMapping("/updateBook")
	public String updateBook(Books books) {
		bookService.updateBook(books);
		return "redirect:/book/allBook";
	}

	//删除书籍
	@RequestMapping("/deleteBook/{bookId}")
	public String deleteBook(@PathVariable("bookId") int id) {
		bookService.deleteBook(id);
		return "redirect:/book/allBook";
	}
}
