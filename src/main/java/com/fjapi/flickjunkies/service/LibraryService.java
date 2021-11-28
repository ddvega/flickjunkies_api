package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.entity.Library;
import com.fjapi.flickjunkies.entity.Movie;
import com.fjapi.flickjunkies.entity.User;
import com.fjapi.flickjunkies.repository.LibraryRepository;
import com.fjapi.flickjunkies.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        if ( savedLibrary != null)
            return library.getName() + " already exists.";

        // User user = userRepository.getUserByStringId(library.getUser().getStringId());
        User user = userRepository.findUserByUsername(library.getUser().getUsername());
        library.setUser(user);


        libraryRepository.save(library);
        return library.getName() + " added to user " + user.getUsername();
    }

    public String editLibraryName(Long libraryId, Map<String, Object> payload)
    {
        if(payload.containsKey("name"))
        {
            Library library = libraryRepository.getById(libraryId);
            library.setName((payload.get("name").toString()));
            libraryRepository.save(library);
            return "library " + library.getName() + "(" + library.getLibraryId() + ") added to db.";
        }
        return null;

    }

    public String addMovieToLibrary(Movie movie, Long libraryId)
    {
        Library library = libraryRepository.getById(libraryId);
        if ( library.getMovies().contains(movie))
            return movie.getTitle() + " already in database.";
        library.addMovie(movie);
        libraryRepository.save(library);
        return "movie added";
    }
}
