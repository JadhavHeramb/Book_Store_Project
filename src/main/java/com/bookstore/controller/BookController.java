package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.Book;
import com.bookstore.entity.MyCollection;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import com.bookstore.service.MyCollectionService;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private MyCollectionService myCollectionService;

	@GetMapping("/home") // will render to html view page in template folder
	public String home() {
		return "home";
	}

	@GetMapping("/book-register") // will render to html view page in template folder
	public String bookRegister() {
		return "book-register";
	}

	@GetMapping("/available-books") // will render to html view page in template folder. Usage of Thymelead
	public ModelAndView availableBooks() {
		List<Book> list = bookService.getAllBooks();
		return new ModelAndView("available-books", "listOfBooks", list);
	}

	@PostMapping("/savebook") // Will store the book info as in book entity
	public String addBook(@ModelAttribute Book b) // to Binding and exposing the view
	{
		bookService.saveBook(b); // call method in service
		return "redirect:/available-books"; // After registering a book user is re-directed to available-books page
	}

	@GetMapping("/my-collection") // Changes made in method to display my collection after saving books into
									// collection
	public String myCollection(Model model) {
		List<MyCollection> list = myCollectionService.getAllBooks();
		model.addAttribute("listOfBooks", list);
		return "my-collection";
	}

	
	  @GetMapping("/mycollection/{id}") // Method to save book by calling method from book service by ID and storing it in mycollection
	  public String SaveBookToCollection(@PathVariable int id) 
	  {
	  Book b = bookService.getBookById(id); MyCollection mc = new
	  MyCollection(b.getBookId(), b.getBookName(), b.getAuthorName(),
	  b.getBookPrice()); myCollectionService.saveBookToMyCollection(mc); return
	  "redirect:/my-collection";
	  
	  }
	  
	  
	  @RequestMapping("/mycollections/{id}") // Method to delete book by calling method from book service by ID and deleting it from my mycollection 
	  public String DeleteBookOfMyCollection(@PathVariable int id){
	  myCollectionService.deleteBookByID(id); return "redirect:/my-collection";
	   
	  }
	  
	 
}
