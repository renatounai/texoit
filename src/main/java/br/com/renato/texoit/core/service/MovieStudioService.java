package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.entity.MovieStudio;
import br.com.renato.texoit.core.repository.MovieStudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieStudioService {
    private final MovieStudioRepository repository;

    public MovieStudioService(final MovieStudioRepository repository) {
        this.repository = repository;
    }

    public void saveAll(final List<MovieStudio> movieStudios) {
        movieStudios.stream()
                .filter(mv -> !repository.existsByMovieAndStudio(mv.getMovie().getTitle(), mv.getStudio().getName()))
                .forEach(repository::save);
    }


}
