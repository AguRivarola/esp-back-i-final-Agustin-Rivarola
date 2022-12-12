package com.dh.series.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class Season {
    private String id;
    private Integer seasonNumber;
    private List<Chapter> chapters;

    public Season(Integer seasonNumber, List<Chapter> chapters) {
        this.seasonNumber = seasonNumber;
        this.chapters = chapters;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id='" + id + '\'' +
                ", seasonNumber=" + seasonNumber +
                ", chapters=" + chapters +
                '}';
    }
}
