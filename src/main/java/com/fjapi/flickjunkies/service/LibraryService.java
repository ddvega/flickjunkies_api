package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.entity.Library;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.entity.User;
import com.fjapi.flickjunkies.repository.LibraryRepository;
import com.fjapi.flickjunkies.repository.UserRepository;
import com.fjapi.flickjunkies.toClient.LibraryMovies;
import com.fjapi.flickjunkies.toClient.LibrarySummary;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Service
public class LibraryService
{

    private final LibraryRepository libraryRepository;

    private final UserService userService;

    public String addLibrary(Library library)
    {
        Library savedLibrary = libraryRepository.getLibraryByName(library.getName());
        if (savedLibrary != null)
            return library.getName() + " already exists.";

        library.setUser(userService.getUserFromToken());
        libraryRepository.save(library);
        return library.getName() + " added to user " + library.getUser().getUsername();
    }

    public String deleteLibrary(Long libraryId)
    {
        Library savedLibrary = libraryRepository.getById(libraryId);
        User user = userService.getUserFromToken();
        if (!savedLibrary.getUser().equals(user))
        {
            return "library does not belong to user";
        }
        libraryRepository.delete(savedLibrary);
        return "library deleted";
    }

//    public void deleteAllUsersLibraries(Long id)
//    {
//        libraryRepository.deleteLibrariesByUser_IdContains(id);
//    }

    public List<LibrarySummary> getAllLibraries()
    {
        List<LibrarySummary> libs = new ArrayList<>();
        List<Library> library = libraryRepository.findAll();
        for (Library l : library)
        {
            libs.add(LibrarySummary.builder()
                    .libraryId(l.getLibraryId())
                    .name(l.getName())
                    .username(l.getUser().getUsername())
                    .count(l.getCount()).build());
        }
        return libs;
    }

    public LibraryMovies getLibraryById(Long libraryId)
    {
        Library library = libraryRepository.findById(libraryId).get();
        System.out.println(library.getMovies());
        return LibraryMovies.builder()
                .libraryId(library.getLibraryId())
                .name(library.getName())
                .username(library.getUser().getUsername())
                .userId(library.getUser().getId())
                .movieCount(library.getCount())
                .movies(library.getMovies())
                .build();
    }

    public String editLibraryName(Long libraryId, String updatedName)
    {
        if (updatedName == null || updatedName.equals(""))
        {
            return "Name cannot be empty";
        }
        Library library = libraryRepository.getLibraryByName(updatedName);
        if (library != null)
        {
            return "Name already taken. Choose another name.";
        }

        library = libraryRepository.getById(libraryId);
        User user = userService.getUserFromToken();

        if (!library.getUser().equals(user))
        {
            return "Library does not belong to current user!";
        }
        library.setName(updatedName);
        libraryRepository.save(library);
        return "Library " + library.getLibraryId() + " updated to " + updatedName + ".";
    }

    public String addMovieToLibrary(Movie movie, Long libraryId)
    {
        Library library = libraryRepository.getById(libraryId);
        if (library.getMovies().contains(movie))
            return movie.getTitle() + " already in database.";
        library.addMovie(movie);
        libraryRepository.save(library);
        return "movie added";
    }

    public String deleteMovieFromLibrary(Long libraryId, Long movieId)
    {
        Library library = libraryRepository.getById(libraryId);
        library.deleteMovie(movieId);
        libraryRepository.save(library);
        return "movie deleted";
    }
}
