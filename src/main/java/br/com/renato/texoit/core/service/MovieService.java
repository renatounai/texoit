package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.entity.Movie;
import br.com.renato.texoit.core.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void saveAll(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    public Movie findByTitle(final String title) {
        return movieRepository.findByTitle(title);
    }

}
