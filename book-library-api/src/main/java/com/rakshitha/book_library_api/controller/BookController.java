package com.rakshitha.book_library_api.controller;

import com.rakshitha.book_library_api.model.Book;
import com.rakshitha.book_library_api.repository.BookRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book replaceBook(@PathVariable Long id, @RequestBody Book bookDetails){
        Book book=bookRepository.findById(id).orElse(null);
            if(book!=null){
                book.setTitle(bookDetails.getTitle());
                book.setAuthor(bookDetails.getAuthor());
                book.setIsbn(bookDetails.getIsbn());
                return bookRepository.save(book);
            }
            return null;

        }

    @PatchMapping("/{id}")
    public Book updateBook(@PathVariable Long id , @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            if(bookDetails.getTitle()!=null) {
                book.setTitle(bookDetails.getTitle());
            }
            if(bookDetails.getAuthor()!=null) {
                book.setAuthor(bookDetails.getAuthor());
            }
            if(bookDetails.getIsbn()!=null) {
                book.setIsbn(bookDetails.getIsbn());
            }
            return bookRepository.save(book);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        bookRepository.deleteById(id);
        return " Book Deleted By id: " + id;
    }


}



