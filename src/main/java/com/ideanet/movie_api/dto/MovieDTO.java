package com.ideanet.movie_api.dto;

public record MovieDTO(Long id, String title, String genre, String director, String actors, String year, String runtime,
                       String imdb) {

}
