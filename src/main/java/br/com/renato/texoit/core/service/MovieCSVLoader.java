package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.dto.MovieCSV;
import br.com.renato.texoit.core.entity.*;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieCSVLoader {
    private final ResourceLoader resourceLoader;
    private final ProducerService producerService;
    private final StudioService studioService;
    private final MovieService movieService;
    private final MovieStudioService movieStudioService;
    private final MovieProducerService movieProducerService;

    @Value("${movie.list.file}")
    private String movieFile;

    public MovieCSVLoader(final ResourceLoader resourceLoader,
                          final ProducerService producerService,
                          final StudioService studioService,
                          final MovieService movieService,
                          final MovieStudioService movieStudioService,
                          final MovieProducerService movieProducerService) {
        this.resourceLoader = resourceLoader;
        this.producerService = producerService;
        this.studioService = studioService;
        this.movieService = movieService;
        this.movieStudioService = movieStudioService;
        this.movieProducerService = movieProducerService;
    }

    @PostConstruct
    @Transactional
    public void importMoviesFromCSV() {
        try {
            final Resource resource = resourceLoader.getResource(movieFile);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            final CsvToBean<MovieCSV> csvToBean = new CsvToBeanBuilder<MovieCSV>(reader)
                    .withType(MovieCSV.class)
                    .withSeparator(';')
                    .build();

            final List<MovieCSV> moviesCSV = csvToBean.parse();

            insertProducers(moviesCSV);
            insertStudios(moviesCSV);
            insertMovies(moviesCSV);
            insertMovieStudio(moviesCSV);
            insertMovieProducer(moviesCSV);
        } catch (IOException e) {
            throw new RuntimeException("Error importing movies from CSV file", e);
        }
    }

    private void insertMovieProducer(final List<MovieCSV> moviesCSV) {
        moviesCSV.forEach(m -> {
            final Movie movie = movieService.findByTitle(m.getTitle());
            final String[] names = split(m.getProducers());
            final List<Producer> producers = producerService.findByNames(names);

            final List<MovieProducer> moviesProducer = producers.stream()
                    .map(producer -> new MovieProducer(movie, producer))
                    .toList();
            movieProducerService.saveAll(moviesProducer);
        });
    }

    private void insertMovieStudio(final List<MovieCSV> moviesCSV) {
        moviesCSV.forEach(m -> {
            final Movie movie = movieService.findByTitle(m.getTitle());
            final String[] names = split(m.getStudios());
            final List<Studio> studios = studioService.findByNames(names);

            final List<MovieStudio> moviesStudio = studios.stream()
                    .map(studio -> new MovieStudio(movie, studio))
                    .toList();
            movieStudioService.saveAll(moviesStudio);
        });
    }

    private void insertMovies(final List<MovieCSV> moviesCSV) {
        final List<Movie> movies = moviesCSV.stream()
                .map(Movie::new)
                .toList();
        movieService.saveAll(movies);
    }

    private void insertStudios(final List<MovieCSV> moviesCSV) {
        final List<Studio> studios = moviesCSV.stream()
                .map(MovieCSV::getStudios)
                .map(MovieCSVLoader::split)
                .flatMap(Arrays::stream)
                .distinct()
                .map(Studio::new)
                .toList();
        studioService.saveAll(studios);
    }

    private void insertProducers(final List<MovieCSV> moviesCSV) {
        final List<Producer> producers = moviesCSV.stream()
                .map(MovieCSV::getProducers)
                .map(MovieCSVLoader::split)
                .flatMap(Arrays::stream)
                .distinct()
                .map(Producer::new)
                .toList();
        producerService.saveAll(producers);
    }

    private static String[] split(String text) {
        return text.split(",\\s+and\\s+|,\\s+|\\s+and\\s+");
    }

}
