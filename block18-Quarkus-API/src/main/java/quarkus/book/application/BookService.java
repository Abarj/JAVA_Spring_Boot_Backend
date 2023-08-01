package quarkus.book.application;

import quarkus.book.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book addBook(Book book);
    void deleteBook(Long id);
    Book updateBook(Long id, Book book);
}
