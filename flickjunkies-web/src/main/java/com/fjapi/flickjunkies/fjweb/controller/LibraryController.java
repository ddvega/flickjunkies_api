package com.fjapi.flickjunkies.fjweb.controller;


import com.fjapi.flickjunkies.fjcore.entity.Library;
import com.fjapi.flickjunkies.fjcore.entity.Movie;
import com.fjapi.flickjunkies.fjcore.service.LibraryService;
import com.fjapi.flickjunkies.fjcore.toClient.LibraryMovies;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/library")
@AllArgsConstructor
public class LibraryController
{
    private final LibraryService libraryService;

    @PostMapping
    public String addLibrary(@RequestBody Library library)
    {
        return libraryService.addLibrary(library);
    }

    //    @PostMapping("/{libraryId}/edit")
//    public String editLibraryName(@PathVariable("libraryId") Long libraryId, @RequestBody Map<String, Object> payload)
//    {
//        return libraryService.editLibraryName(libraryId, payload);
//    }
    @GetMapping("/all")
    public List<Library> getAllLibraries()
    {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/id/{libraryId}")
    public LibraryMovies getLibraryById(@PathVariable("libraryId") Long libraryId)
    {
        return libraryService.getLibraryById(libraryId);
    }

    // @GetMapping("/{libraryId}")
    @PatchMapping("/{libraryId}/{name}")
    public String editLibraryName(@PathVariable("libraryId") Long libraryId, @PathVariable("name") String name)
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

    // @GetMapping("/{libraryId}")
    @DeleteMapping("/{libraryId}")
    public String deleteLibrary(@PathVariable("libraryId") Long libraryId)
    {
        return libraryService.deleteLibrary(libraryId);
    }

    @DeleteMapping("/{libraryId}/{movieId}")
    public String deleteMovieFromLibrary(@PathVariable("libraryId") Long libraryId,
                                         @PathVariable("movieId") Long movieId)
    {
        return libraryService.deleteMovieFromLibrary(libraryId, movieId);
    }
}
