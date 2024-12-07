package com.ideanet.movie_api.service;

import com.ideanet.movie_api.dto.MovieDTO;
import org.springframework.data.domain.Page;

public interface MovieService {

    Page<MovieDTO> getMovies(int pageNo, int size, String sort);
}
