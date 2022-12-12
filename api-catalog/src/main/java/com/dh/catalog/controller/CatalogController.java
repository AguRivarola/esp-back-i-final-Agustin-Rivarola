package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.model.dto.CatalogDto;
import com.dh.catalog.model.dto.MovieDto;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/online/{genre}")
	ResponseEntity<CatalogDto> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getCatalogByGenre(genre));
	}
	@GetMapping("/offline/{genre}")
	ResponseEntity<CatalogDto> getGenreOffline(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getCatalogoByGenreOfline(genre));
	}

}
