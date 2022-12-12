package com.dh.series.model.dto;

import com.dh.series.model.Season;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SerieDto {
    private String id;
    private String name;
    private String genre;
    private List<Season> seasons;

    @Override
    public String toString() {
        return "SeriesDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", seasons=" + seasons +
                '}';
    }
}
