package com.fjapi.flickjunkies.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fjapi.flickjunkies.entity.Library;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.service.LibraryService;
import com.fjapi.flickjunkies.util.LibraryObj;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost:3000/")
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

//    @PostMapping("/{libraryId}/edit")
//    public String editLibraryName(@PathVariable("libraryId") Long libraryId, @RequestBody Map<String, Object> payload)
//    {
//        return libraryService.editLibraryName(libraryId, payload);
//    }
    @GetMapping("/all")
    public List<LibraryObj> getAllLibraries()
    {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/{libraryId}")
    public String editLibraryName(@PathVariable("libraryId")Long libraryId, @RequestParam String name)
    {
        return libraryService.editLibraryName(libraryId, name);
    }

    // @PutMapping("/{libraryId}")
    @PostMapping("/{libraryId}")
    public String addMovieToLibrary(@PathVariable("libraryId") Long libraryId, @RequestBody Movie movie)
    {
        // log.info("Inside addMovieLibrary of LibraryController");
        return libraryService.addMovieToLibrary(movie, libraryId);
    }
}
