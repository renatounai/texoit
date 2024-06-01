package br.com.renato.texoit.core.repository;

import br.com.renato.texoit.core.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    @Query("""
        select p from Producer p
        where p.name in :names
    """)
    List<Producer> findByNames(String[] names);
}
