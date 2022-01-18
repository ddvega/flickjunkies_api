package fjservice.service;

import fjdata.model.Genre;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import fjcommon.util.GenreNameIdMap;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
@AllArgsConstructor
public class GenreService
{
    public List<Genre> buildGenreList(JSONArray arr)
    {
        List<Genre> genreList = new ArrayList<>();
        for(Object a : arr)
            genreList.add(Genre.builder().id((Integer)a).name(GenreNameIdMap.findName((Integer) a)).build());
        return genreList;
    }
}
