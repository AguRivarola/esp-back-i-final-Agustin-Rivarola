package com.dh.catalog.client;

import com.dh.catalog.model.dto.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="api-movie")
public interface MovieServiceClient {

	@GetMapping("/api/v1/movies/{genre}")
	ResponseEntity<List<MovieDto>> getMovieByGenre(@PathVariable (value = "genre") String genre);



}
