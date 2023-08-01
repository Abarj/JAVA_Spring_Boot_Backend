package quarkus.genre.infrastructure.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import quarkus.PaginatedResponse;
import quarkus.genre.application.GenreService;
import quarkus.genre.entity.Genre;

@Path("/genres")
@Transactional
public class GenreController {

    @Inject
    private GenreService genreService;

    @POST
    public Genre addGenre(Genre insertedGenre) {
        return genreService.addGenre(insertedGenre);
    }

    @GET
    public PaginatedResponse<Genre> getAllGenres(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("q") String query) {
        return genreService.getAllGenres(page, query);
    }

    @GET
    @Path("/{id}")
    public Genre getGenreById(@PathParam("id") Long id) {
        return genreService.getGenreById(id);
    }

    @PUT
    @Path("/{id}")
    public Genre updateGenre(@PathParam("id") Long id, Genre genre) {
        return genreService.updateGenre(id, genre);
    }

    @DELETE
    @Path("/{id}")
    public  void deleteGenre(@PathParam("id") Long id) {
        genreService.deleteGenre(id);
    }
}
