package com.fjapi.flickjunkies.controller;

import com.fjapi.flickjunkies.entity.Library;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.service.LibraryService;
import com.fjapi.flickjunkies.toClient.LibraryMovies;
import com.fjapi.flickjunkies.toClient.LibrarySummary;
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
    public List<LibrarySummary> getAllLibraries()
    {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/id/{libraryId}")
    public LibraryMovies getLibraryById (@PathVariable("libraryId") Long libraryId)
    {
        return libraryService.getLibraryById(libraryId);
    }

    // @GetMapping("/{libraryId}")
    @PatchMapping("/{libraryId}")
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

    @DeleteMapping("/{libraryId}/{movieId}")
    public String deleteMovieFromLibrary(@PathVariable("libraryId") Long libraryId,
                                         @PathVariable("movieId") Long movieId )
    {
        return libraryService.deleteMovieFromLibrary(libraryId, movieId);
    }
}
