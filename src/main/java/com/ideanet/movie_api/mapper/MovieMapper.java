package com.ideanet.movie_api.mapper;

import com.ideanet.movie_api.domain.Movie;
import com.ideanet.movie_api.dto.MovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    Movie movieDTOToMovie(MovieDTO movieDTO);

    MovieDTO movieToMovieDTO(Movie movie);
}
