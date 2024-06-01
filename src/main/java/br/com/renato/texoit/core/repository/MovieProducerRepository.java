package br.com.renato.texoit.core.repository;

import br.com.renato.texoit.core.dto.SummaryItem;
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

    @Query(value = """
                    with winners as (
                                   select
                                       p.name,
                                       min(m.year_release) as min,
                                       max(m.year_release) as max,
                                       max(m.year_release) - min(m.year_release) as "interval"
                                   from movie_producer mp
                                   join producer p on p.id = mp.producer_id
                                   join movie m on m.id = mp.movie_id
                                   where m.winner = true
                                   group by p.name
                                   having count(*) > 1
                               )
                               select
                                   name as "producer",
                                   min as "previouswin",
                                   max as "followingwin",
                                   "interval"
                               from winners
                               where "interval" = (select max("interval") from winners)
                               order by name
            """, nativeQuery = true)
    List<SummaryItem> findSummaryMax();

    @Query(value = """
                    with winners as (
                                   select
                                       p.name,
                                       min(m.year_release) as min,
                                       max(m.year_release) as max,
                                       max(m.year_release) - min(m.year_release) as "interval"
                                   from movie_producer mp
                                   join producer p on p.id = mp.producer_id
                                   join movie m on m.id = mp.movie_id
                                   where m.winner = true
                                   group by p.name
                                   having count(*) > 1
                               )
                               select
                                   name as "producer",
                                   min as "previouswin",
                                   max as "followingwin",
                                   "interval"
                               from winners
                               where "interval" = (select min("interval") from winners)
                               order by name
            """, nativeQuery = true)
    List<SummaryItem> findSummaryMin();
}
