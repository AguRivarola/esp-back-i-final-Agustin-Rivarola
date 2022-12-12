package com.dh.series.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Chapter {
    private String id;
    private String name;
    private Integer number;
    private String urlStream;

    public Chapter(String name, Integer number, String urlStream) {
        this.name = name;
        this.number = number;
        this.urlStream = urlStream;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", urlStream='" + urlStream + '\'' +
                '}';
    }
}
