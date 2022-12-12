package com.dh.catalog.client;

import com.dh.catalog.config.LoadBalancerConfig;
import com.dh.catalog.model.MovieEntity;
import com.dh.catalog.model.dto.MovieDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

@FeignClient(name="api-movie")
@LoadBalancerClient(name = "api-movie",configuration = LoadBalancerConfig.class)
public interface MovieServiceClient {

	@GetMapping("/api/v1/movies/{genre}")
	ResponseEntity<List<MovieDto>> getMovieByGenre(@PathVariable (value = "genre") String genre);

	@GetMapping("/movie")
	List<MovieEntity> getAll();
}
