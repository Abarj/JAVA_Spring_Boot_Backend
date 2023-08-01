package quarkus.genre.application;

import quarkus.PaginatedResponse;
import quarkus.genre.entity.Genre;

public interface GenreService {

    Genre addGenre(Genre genre);
    PaginatedResponse<Genre> getAllGenres(int page, String query);
    Genre getGenreById(Long id);
    void deleteGenre(Long id);
    Genre updateGenre(Long id, Genre genre);
}
