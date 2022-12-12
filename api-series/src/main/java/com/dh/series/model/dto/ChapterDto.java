package com.dh.series.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChapterDto {
    private String id;
    private String name;
    private Integer number;
    private String urlStream;


    @Override
    public String toString() {
        return "ChapterDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", urlStream='" + urlStream + '\'' +
                '}';
    }
}
