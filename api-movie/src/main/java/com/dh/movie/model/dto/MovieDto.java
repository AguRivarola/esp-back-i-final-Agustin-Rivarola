package com.dh.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDto {
    private Long id;
    private String name;
    private String genre;
    private String urlStream;

    @Override
    public String toString() {
        return "MovieDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number=" + genre +
                ", urlStream='" + urlStream + '\'' +
                '}';
    }
}
