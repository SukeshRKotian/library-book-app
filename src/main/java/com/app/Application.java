package com.app;

import com.app.domain.Book;
import com.app.domain.Library;
import com.app.service.IService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	private final IService<Library> libraryService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * preload the libraries and books
	 * @param args
	 */
	@Transactional
	@SneakyThrows
	@Override
	public void run(String... args) {
		for(int i=1; i<=50; i++) {
			Library library = new Library();
			library.setName("Public Library Group" + i);
			library.setAddress("Kings Street, CA");
			library.setEmail("contact-"+i+"us@publiclib.com");
			List<Book> books = new ArrayList<>();
			for (int j = 1; j <=100; j++) {
				Book book = new Book();
				book.setTitle("Spring Microservices in Action "+ j);
				book.setAuthor("John Carnell "+ j);
				book.setCoverPhotoURL("https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg");
				book.setIsbnNumber(1617293989L);
				book.setPrice(2776.00 + j);
				book.setLanguage("English");
				book.setGenre("Technology");
				book.setBorrowed(true);
				book.setReturned(false);
				book.setLibrary(library);
				books.add(book);
			}
			library.setBooks(books);
			libraryService.saveOrUpdate(library);
		}
	}
}
