package com.dh.series.model.dto;

import com.dh.series.model.Chapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SeasonDto {

    private String id;
    private Integer seasonNumber;
    private List<Chapter> chapters;

    @Override
    public String toString() {
        return "SeasonDTO{" +
                "id='" + id + '\'' +
                ", seasonNumber=" + seasonNumber +
                ", chapters=" + chapters +
                '}';
    }
}
