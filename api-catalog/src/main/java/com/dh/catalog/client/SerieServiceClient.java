package com.dh.catalog.client;

import com.dh.catalog.model.dto.SerieDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="api-serie")
public interface SerieServiceClient {

	@GetMapping("/api/v1/serie/{genre}")
	ResponseEntity<List<SerieDto>> getMovieByGenre(@PathVariable (value = "genre") String genre);



}
