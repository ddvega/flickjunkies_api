package fjcommon.repository;

import fjdata.model.Library;
import fjdata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long>
{
    Library getLibraryByName(String name);
    void removeAllByUserEquals(User user);
}
