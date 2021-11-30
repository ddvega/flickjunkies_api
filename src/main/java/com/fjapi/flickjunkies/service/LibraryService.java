package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.entity.Library;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.entity.User;
import com.fjapi.flickjunkies.repository.LibraryRepository;
import com.fjapi.flickjunkies.repository.UserRepository;
import com.fjapi.flickjunkies.util.LibraryObj;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Service
public class LibraryService
{

    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;

    public String addLibrary(Library library)
    {
        Library savedLibrary = libraryRepository.getLibraryByName(library.getName());
        if (savedLibrary != null)
            return library.getName() + " already exists.";

        User user = userRepository.findUserByUsername(library.getUser().getUsername());
        library.setUser(user);
        libraryRepository.save(library);
        return library.getName() + " added to user " + user.getUsername();
    }

    public List<LibraryObj> getAllLibraries()
    {
        List<LibraryObj> libs = new ArrayList<>();
        List<Library> library = libraryRepository.findAll();
        for (Library l : library)
        {
            libs.add(LibraryObj.builder().libraryId(l.getLibraryId()).name(l.getName()).username(l.getUser().getUsername()).build());
        }
        return libs;
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
}
