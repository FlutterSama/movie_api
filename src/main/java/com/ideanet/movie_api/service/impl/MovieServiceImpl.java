package com.ideanet.movie_api.service.impl;

import com.ideanet.movie_api.domain.Movie;
import com.ideanet.movie_api.dto.MovieDTO;
import com.ideanet.movie_api.mapper.MovieMapper;
import com.ideanet.movie_api.repository.MovieRepository;
import com.ideanet.movie_api.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public Page<MovieDTO> getMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        return movies.map(movieMapper :: movieToMovieDTO);
    }

    @Override
    public Optional<MovieDTO> getMovieById(Long id) {
        return movieRepository.findById(id).map(movieMapper :: movieToMovieDTO);
    }
}
