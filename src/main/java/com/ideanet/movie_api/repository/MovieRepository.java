package com.ideanet.movie_api.repository;

import com.ideanet.movie_api.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
