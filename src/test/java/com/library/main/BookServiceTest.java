package com.library.main;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.library.main.data.BookRepository;
import com.library.main.model.Book;
import com.library.main.model.Publisher;
import com.library.main.service.BookService;
import com.library.main.service.PublisherService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness=Strictness.LENIENT)
public class BookServiceTest {
	
	@Autowired
	@InjectMocks
	private BookService bookService;
	
	@Autowired
	@Mock
	private BookRepository bookRepository;
	
	@Autowired
	@InjectMocks
	private PublisherService publisherService;
	
	@Test
	public void updateBookTest() {

		Book book=new Book();
		book.setId(12);
		book.setName("You are the best wife");
		book.setPrice(170);
		book.setPublisher(new Publisher(2,"Bhumisuta Pradhan",null));

		when(bookRepository.findById(12)).thenReturn(Optional.of(book));
		Assertions.assertEquals(book.getId(),bookRepository.findById(12).get().getId());
		Assertions.assertEquals(book.getName(),bookRepository.findById(12).get().getName());
		Assertions.assertEquals(book.getPrice(),bookRepository.findById(12).get().getPrice());
		Assertions.assertEquals(book.getPublisher(), bookRepository.findById(12).get().getPublisher());
	}
	
	@Test
	public void getAllBookTest() {
		
		Book book1=new Book();
		book1.setId(12);
		book1.setName("You are the best wife");
		book1.setPrice(170);
		book1.setPublisher(new Publisher(2,"Bhumisuta Pradhan",null));
		
		Book book2=new Book();
		book2.setId(13);
		book2.setName("Indian Home Rule");
		book2.setPrice(200);
		book2.setPublisher(new Publisher(3,"Ranjan Pradhan",null));
		
		List<Book> book=new ArrayList<Book>();
		book.add(book1);
		book.add(book2);
		
		when(bookRepository.findAll()).thenReturn(book);
		Assertions.assertEquals(book.get(0).getName(), bookService.getAllBook().get(0).getName());
		Assertions.assertEquals(book.get(0).getId(), bookService.getAllBook().get(0).getId());
		Assertions.assertEquals(book.get(0).getPrice(), bookService.getAllBook().get(0).getPrice());
		Assertions.assertEquals(book.get(0).getPublisher().getName(), bookService.getAllBook().get(0).getPublisher().getName());
	}
	
	
	@Test
	public void getBookByIDTest() {
		
		Book book=new Book();
		book.setId(12);
		book.setName("You are the best wife");
		book.setPrice(170);
		book.setPublisher(new Publisher(2,"Bhumisuta Pradhan",null));
		
		
		when(bookRepository.findById(12)).thenReturn(Optional.of(book));
		Assertions.assertEquals(book.getName(),bookService.getBookByID(12).get().getName());
		Assertions.assertEquals(book.getId(),bookService.getBookByID(12).get().getId());
		Assertions.assertEquals(book.getPrice(),bookService.getBookByID(12).get().getPrice());
		Assertions.assertEquals(book.getPublisher().getName(),bookService.getBookByID(12).get().getPublisher().getName());
	}
	
	@Test
	public void postBookTest() {
		
		Book book=new Book();
		book.setId(12);
		book.setName("You are the best wife");
		book.setPrice(170);
		book.setPublisher(new Publisher(2,"Bhumisuta Pradhan",null));
		
		when(bookService.postBook(book)).thenReturn(book);
		Assertions.assertEquals(book.getName(),bookService.postBook(book).getName());
		Assertions.assertEquals(book.getId(),bookService.postBook(book).getId());
		Assertions.assertEquals(book.getPrice(),bookService.postBook(book).getPrice());
		Assertions.assertEquals(book.getPublisher(),bookService.postBook(book).getPublisher());
	}

}
