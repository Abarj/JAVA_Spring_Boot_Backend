package quarkus.book.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.book.entity.Book;

@ApplicationScoped
public class  BookRepository implements PanacheRepository<Book> {
}
