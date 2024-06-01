package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.entity.Producer;
import br.com.renato.texoit.core.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {
    private final ProducerRepository repository;

    public ProducerService(final ProducerRepository repository) {
        this.repository = repository;
    }

    public void saveAll(final List<Producer> producers) {
        repository.saveAll(producers);
    }

    public List<Producer> findByNames(final String[] names) {
        return repository.findByNames(names);
    }
}
