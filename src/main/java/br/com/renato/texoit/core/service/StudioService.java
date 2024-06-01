package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.entity.Studio;
import br.com.renato.texoit.core.repository.StudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioService {
    private final StudioRepository repository;

    public StudioService(final StudioRepository repository) {
        this.repository = repository;
    }

    public void saveAll(final List<Studio> studios) {
        repository.saveAll(studios);
    }

    public List<Studio> findByNames(final String[] names) {
        return repository.findByName(names);
    }
}
