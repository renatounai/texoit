package br.com.renato.texoit.core.repository;

import br.com.renato.texoit.core.entity.MovieProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Integer>{

    @Query("""
        select exists(
            select mp from MovieProducer mp
            where mp.movie.title = :title
            and mp.producer.name = :producer
        )
    """)
    boolean existsByMovieAndProducer(String title, String producer);

    @Query("""
    select mp from MovieProducer mp
    where mp.movie.winner = true
    order by mp.movie.year
    """)
    List<MovieProducer> findWinnersOrderedByYear();

}
