package com.ideanet.movie_api.service;

import com.ideanet.movie_api.dto.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovieService {

    Page<MovieDTO> getMovies(Pageable pageable);

    Optional<MovieDTO> getMovieById(Long id);
}
