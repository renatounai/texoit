package br.com.renato.texoit.core.repository;

import br.com.renato.texoit.core.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

    @Query("""
        select m from Movie m
        where m.winner = true
    """)
    List<Movie> findWinners();

    Movie findByTitle(final String title);
}
