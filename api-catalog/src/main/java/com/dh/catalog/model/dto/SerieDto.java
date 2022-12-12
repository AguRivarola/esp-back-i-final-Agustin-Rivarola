package com.dh.catalog.model.dto;

import com.dh.catalog.model.SerieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SerieDto extends SerieEntity {

    private String id;
    private String name;
    private String genre;
    private List<SeasonDto> seasons;
}
