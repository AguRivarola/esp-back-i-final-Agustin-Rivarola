package com.dh.catalog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDto {
    private List<SerieDto> serieDtoList = new ArrayList<>();
    private List<MovieDto> movieDtoList = new ArrayList<>();
}
