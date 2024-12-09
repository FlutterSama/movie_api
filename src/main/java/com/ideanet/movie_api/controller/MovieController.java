package com.ideanet.movie_api.controller;

import com.ideanet.movie_api.dto.MovieDTO;
import com.ideanet.movie_api.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie/v1")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public PagedModel<EntityModel<MovieDTO>> all(
            @PageableDefault(size = 10, sort = "title", page = 0) Pageable pageable,
            PagedResourcesAssembler<MovieDTO> pagedResourcesAssembler
    ) {
        Page<MovieDTO> movieDTOPage = movieService.getMovies(pageable);

        return pagedResourcesAssembler.toModel(movieDTOPage, movie -> EntityModel.of(movie,
                WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(MovieController.class).one(movie.id()))
                        .withSelfRel()));
    }

    @GetMapping("/{id}")
    public EntityModel<MovieDTO> one(@PathVariable Long id) {
        MovieDTO movie = movieService.getMovieById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        return EntityModel.of(movie,
                WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(MovieController.class).one(id))
                        .withSelfRel(),
                WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(MovieController.class).all(Pageable.unpaged(), null))
                        .withRel("movies"));
    }

    // Exception Handler
    @ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
    public static class MovieNotFoundException extends RuntimeException {
        public MovieNotFoundException(Long id) {
            super("Movie not found with id: " + id);
        }
    }
}
