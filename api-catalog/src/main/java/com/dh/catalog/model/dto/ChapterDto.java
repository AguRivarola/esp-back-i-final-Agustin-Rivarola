package com.dh.catalog.model.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto {
    private String id;
    private String name;
    private Integer number;
    private String urlStream;
}
