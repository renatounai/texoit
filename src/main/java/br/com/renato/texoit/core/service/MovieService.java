package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.entity.Movie;
import br.com.renato.texoit.core.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /***
     * This method will find the producers with the biggest interval between two awards and
     * the producers with the smallest interval between two awards.
     */
    public void findProducers() {
        final List<Movie> winners = movieRepository.findWinners();
        Map<String, Integer> minYear = new HashMap<>();
        Map<String, Integer> maxYear = new HashMap<>();

        for (Movie winner : winners) {

        }

    }


}
