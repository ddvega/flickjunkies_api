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
        // User user = userRepository.getUserByStringId(library.getUser().getStringId());
        User user = userRepository.getById(library.getUser().getId());
        library.setUser(user);
        libraryRepository.save(library);
        return "library added";
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
        library.addMovie(movie);
        libraryRepository.save(library);
        return "movie added";
    }
}
