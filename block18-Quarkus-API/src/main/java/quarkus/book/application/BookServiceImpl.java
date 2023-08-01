package quarkus.book.application;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import quarkus.book.entity.Book;
import quarkus.book.infrastructure.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book insertedBook) {
        bookRepository.persist(insertedBook);
        return insertedBook;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.listAll(Sort.by("pubDate", Sort.Direction.Descending));
    }

    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id);

        if(book != null) {
            return book;
        }
        throw new NoSuchElementException("No book was found with id: " + id);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        var updatedBook = bookRepository.findById(id);

        if (updatedBook != null) {
            updatedBook.setTitle(book.getTitle());
            updatedBook.setGenre(book.getGenre());
            updatedBook.setPubDate(book.getPubDate());
            updatedBook.setNumPages(book.getNumPages());
            updatedBook.setDescription(book.getDescription());

            bookRepository.persist(updatedBook);

            return updatedBook;
        }
        throw new NoSuchElementException("No book was found with id: " + id);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
