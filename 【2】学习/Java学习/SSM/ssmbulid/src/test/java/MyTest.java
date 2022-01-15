import com.tian.pojo.Books;
import com.tian.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author: Tian
 * @time: 2021-08-16 01:42
 **/
public class MyTest {
	@Test
	public void testBookService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookServiceImpl = context.getBean("BookServiceImpl", BookService.class);
		List<Books> books = bookServiceImpl.selectBookAll();
		for (Books book : books) {
			System.out.println(book);
		}
		//Books books = bookServiceImpl.selectBookById(2);
		//System.out.println(books);
		//int i = bookServiceImpl.deleteBook(2);
		//System.out.println(i);
	}
}
