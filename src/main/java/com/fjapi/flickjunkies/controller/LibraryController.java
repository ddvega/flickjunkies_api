package com.fjapi.flickjunkies.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.entity.Library;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/library")
@AllArgsConstructor
public class LibraryController
{
    private final LibraryService libraryService;

    @PostMapping
    public String addLibrary(@RequestBody Library payload)
    {
        return libraryService.addLibrary(payload);
    }

    @PostMapping("/{libraryId}/edit")
    public String editLibraryName(@PathVariable("libraryId") Long libraryId, @RequestBody Map<String, Object> payload)
    {
        return libraryService.editLibraryName(libraryId, payload);
    }

    // @PutMapping("/{libraryId}")
    @PostMapping("/{libraryId}")
    public String addMovieToLibrary(@PathVariable("libraryId") Long libraryId, @RequestBody Movie movie)
    {
        // log.info("Inside addMovieLibrary of LibraryController");
        return libraryService.addMovieToLibrary(movie, libraryId);
    }
}
