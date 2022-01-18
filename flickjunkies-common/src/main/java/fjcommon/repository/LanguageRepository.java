package fjcommon.repository;

import fjdata.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long>
{
    Language findByLanguageName(String language);
}
