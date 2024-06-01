package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.dto.Summary;
import br.com.renato.texoit.core.dto.SummaryItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService {
    private final MovieProducerService movieProducerService;

    public SummaryService(final MovieProducerService movieProducerService) {
        this.movieProducerService = movieProducerService;
    }

    public Summary findSummary() {
        final List<SummaryItem> max = movieProducerService.getProducersWithMaxInterval();
        final List<SummaryItem> min = movieProducerService.getProducersWithMinInterval();
        return new Summary(max, min);
    }
}
