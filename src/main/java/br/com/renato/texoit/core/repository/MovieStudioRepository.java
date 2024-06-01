package br.com.renato.texoit.core.repository;

import br.com.renato.texoit.core.entity.MovieStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieStudioRepository extends JpaRepository<MovieStudio, Integer> {

    @Query("""
        select exists(
            select ms from MovieStudio ms
            where ms.movie.title = :title
            and ms.studio.name = :studio
        )
    """)
    boolean existsByMovieAndStudio(final String title, final String studio);
}
