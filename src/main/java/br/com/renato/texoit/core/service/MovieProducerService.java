package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.entity.MovieProducer;
import br.com.renato.texoit.core.repository.MovieProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieProducerService {
    private final MovieProducerRepository repository;

    public MovieProducerService(final MovieProducerRepository repository) {
        this.repository = repository;
    }

    public void saveAll(final List<MovieProducer> movieProducers) {
        movieProducers.stream()
                .filter(mv -> !repository.existsByMovieAndProducer(mv.getMovie().getTitle(), mv.getProducer().getName()))
                .forEach(repository::save);
    }

    public List<MovieProducer> findWinnersOrderedByYear() {
        return repository.findWinnersOrderedByYear();
    }

}
