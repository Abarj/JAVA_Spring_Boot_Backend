package quarkus.genre.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.genre.entity.Genre;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {

    public PanacheQuery<Genre> findPage(int page) {
        Page p = new Page(page -1, 5);
        var query = findAll(Sort.descending("createdAt"));
        query.page(p);
        return query;
    }
}
