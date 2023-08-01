package quarkus.book.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import quarkus.book.application.BookService;
import quarkus.book.entity.Book;

import java.util.List;

@Path("/books")
@Transactional
public class BookController {

    @Inject
    private BookService bookService;

    @POST
    public Book addBook(Book insertedBook) {
        return bookService.addBook(insertedBook);
    }

    @GET
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") Long id) {
        return bookService.getBookById(id);
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") Long id, Book book) {
        return bookService.updateBook(id, book);
    }

    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
    }
}
