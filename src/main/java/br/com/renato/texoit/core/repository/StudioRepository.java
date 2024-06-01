package br.com.renato.texoit.core.repository;

import br.com.renato.texoit.core.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer>{

    @Query("""
        select s from Studio s
        where s.name in :names
    """)
    List<Studio> findByName(String[] names);
}
