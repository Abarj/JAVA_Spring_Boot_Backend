package quarkus.genre.application;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import quarkus.PaginatedResponse;
import quarkus.genre.entity.Genre;
import quarkus.genre.infrastructure.repository.GenreRepository;

import java.util.NoSuchElementException;

@ApplicationScoped
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre addGenre(Genre genre) {
        genreRepository.persist(genre);
        return genre;
    }

    @Override
    public PaginatedResponse<Genre> getAllGenres(int page, String q) {
        var query = genreRepository.findPage(page);

        if (q != null) {
            var nameLike = "%" + q + "%";
            // Filtro dinámico
            query.filter("name.like", Parameters.with("name", nameLike));
        }

        return new PaginatedResponse<>(query);
    }

    @Override
    public Genre getGenreById(Long id) {
        Genre genre = genreRepository.findById(id);

        if(genre != null) {
            return genre;
        }
        throw new NoSuchElementException("No book was found with id: " + id);
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) {
        var updatedGenre = genreRepository.findById(id);

        if (updatedGenre != null) {
            updatedGenre.setName(genre.getName());

            genreRepository.persist(updatedGenre);

            return updatedGenre;
        }
        throw new NoSuchElementException("No genre was found with id: " + id);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
