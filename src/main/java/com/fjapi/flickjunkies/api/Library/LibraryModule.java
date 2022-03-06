package com.fjapi.flickjunkies.api.Library;

import com.fjapi.flickjunkies.dto.LibraryDTO;
import com.fjapi.flickjunkies.model.Library;
import com.fjapi.flickjunkies.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/library")
public interface LibraryModule {
    @PostMapping
    String addLibrary(@RequestBody Library library);

    @GetMapping("/all")
    List<LibraryDTO> getAllLibraries();

    @GetMapping("/id/{libraryId}")
    LibraryDTO getLibraryById(@PathVariable("libraryId") Long libraryId);

    @PatchMapping("/{libraryId}/{name}")
    String editLibraryName(@PathVariable("libraryId") Long libraryId, @PathVariable("name") String name);

    @PostMapping("/{libraryId}")
    String addMovieToLibrary(@PathVariable("libraryId") Long libraryId, @RequestBody Movie movie);

    @DeleteMapping("/{libraryId}")
    String deleteLibrary(@PathVariable("libraryId") Long libraryId);

    @DeleteMapping("/{libraryId}/{movieId}")
    String deleteMovieFromLibrary(@PathVariable("libraryId") Long libraryId,
                                  @PathVariable("movieId") Long movieId);
}
