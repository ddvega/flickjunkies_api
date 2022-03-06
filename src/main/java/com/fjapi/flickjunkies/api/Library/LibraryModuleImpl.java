package com.fjapi.flickjunkies.api.Library;

import com.fjapi.flickjunkies.dto.LibraryDTO;
import com.fjapi.flickjunkies.model.Library;
import com.fjapi.flickjunkies.model.Movie;
import com.fjapi.flickjunkies.service.LibraryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryModuleImpl implements LibraryModule {
    private final LibraryService libraryService;

    public LibraryModuleImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public String addLibrary(@RequestBody Library library) {
        return libraryService.addLibrary(library);
    }

    @Override
    public List<LibraryDTO> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @Override
    public LibraryDTO getLibraryById(@PathVariable("libraryId") Long libraryId) {
        return libraryService.getLibraryById(libraryId);
    }

    @Override
    public String editLibraryName(@PathVariable("libraryId") Long libraryId, @PathVariable("name") String name) {
        return libraryService.editLibraryName(libraryId, name);
    }

    @Override
    public String addMovieToLibrary(@PathVariable("libraryId") Long libraryId, @RequestBody Movie movie) {
        return libraryService.addMovieToLibrary(movie, libraryId);
    }

    @Override
    public String deleteLibrary(@PathVariable("libraryId") Long libraryId) {
        return libraryService.deleteLibrary(libraryId);
    }

    @Override
    public String deleteMovieFromLibrary(@PathVariable("libraryId") Long libraryId,
                                         @PathVariable("movieId") Long movieId) {
        return libraryService.deleteMovieFromLibrary(libraryId, movieId);
    }
}
