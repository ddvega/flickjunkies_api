package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.dto.LibraryDTO;
import com.fjapi.flickjunkies.model.Library;
import com.fjapi.flickjunkies.model.Movie;
import com.fjapi.flickjunkies.model.User;
import com.fjapi.flickjunkies.repository.LibraryRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final UserService userService;
    private final MovieService movieService;

    public LibraryService(LibraryRepository libraryRepository, UserService userService, MovieService movieService) {
        this.libraryRepository = libraryRepository;
        this.userService = userService;
        this.movieService = movieService;
    }

    public String addLibrary(Library library) {
        Library savedLibrary = libraryRepository.getLibraryByName(library.getName());
        if (savedLibrary != null)
            return library.getName() + " already exists.";

        library.setUser(userService.getUserFromToken());
        libraryRepository.save(library);
        return library.getName() + " added to user " + library.getUser().getUsername();
    }

    public String deleteLibrary(Long libraryId) {
        Library savedLibrary = libraryRepository.getById(libraryId);
        User user = userService.getUserFromToken();
        if (!savedLibrary.getUser().equals(user)) {
            return "library does not belong to user";
        }
        libraryRepository.delete(savedLibrary);
        return "library deleted";
    }

    public List<LibraryDTO> getAllLibraries() {
        List<LibraryDTO> libs = new ArrayList<>();
        List<Library> library = libraryRepository.findAll();
        library.forEach(lib -> libs.add(LibraryDTO.buildLibraryDTO(lib)));
        return libs;
    }

    public LibraryDTO getLibraryById(Long libraryId) {
        return LibraryDTO.buildLibraryDTO(libraryRepository.findById(libraryId).get());
    }

    public String editLibraryName(Long libraryId, String updatedName) {
        if (updatedName == null || updatedName.equals("")) {
            return "Name cannot be empty";
        }
        Library library = libraryRepository.getLibraryByName(updatedName);
        if (library != null) {
            return "Name already taken. Choose another name.";
        }

        library = libraryRepository.getById(libraryId);
        User user = userService.getUserFromToken();

        if (!library.getUser().equals(user)) {
            return "Library does not belong to current user!";
        }
        library.setName(updatedName);
        libraryRepository.save(library);
        return "Library " + library.getLibraryId() + " updated to " + updatedName + ".";
    }

    @SneakyThrows
    public String addMovieToLibrary(Movie movie, Long libraryId) {
        Library library = libraryRepository.getById(libraryId);
        if (library.getMovies().contains(movie))
            return movie.getTitle() + " already in database.";
        movieService.AddMovie(movie);
        library.addMovie(movie);
        libraryRepository.save(library);
        return "movie added";
    }

    public String deleteMovieFromLibrary(Long libraryId, Long movieId) {
        Library library = libraryRepository.getById(libraryId);
        library.deleteMovie(movieId);
        libraryRepository.save(library);
        return "movie deleted";
    }
}
