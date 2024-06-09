package br.com.renato.texoit.core.service;

import br.com.renato.texoit.core.dto.Summary;
import br.com.renato.texoit.core.dto.SummaryItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SummaryService {
    private final MovieProducerService movieProducerService;

    public SummaryService(final MovieProducerService movieProducerService) {
        this.movieProducerService = movieProducerService;
    }

    public Summary findProducersSummaryByInterval() {
        final Map<String, List<Integer>> producerYearsMap = movieProducerService.findWinnersOrderedByYear().stream()
                .collect(Collectors.groupingBy(mp -> mp.getProducer().getName(),
                        Collectors.mapping(mp -> mp.getMovie().getYear(), Collectors.toList())));

        removeProducersWithSingleWin(producerYearsMap);

        int minInterval = Integer.MAX_VALUE;
        int maxInterval = 0;
        for (final Map.Entry<String, List<Integer>> entry : producerYearsMap.entrySet()) {
            final List<Integer> years = entry.getValue();
            for (int i = 0; i < years.size() - 1; i++) {
                final int interval = years.get(i + 1) - years.get(i);
                minInterval = Math.min(minInterval, interval);
                maxInterval = Math.max(maxInterval, interval);
            }
        }

        final List<SummaryItem> summaryMax = new ArrayList<>();
        final List<SummaryItem> summaryMin = new ArrayList<>();
        for (final Map.Entry<String, List<Integer>> entry : producerYearsMap.entrySet()) {
            final List<Integer> years = entry.getValue();
            for (int i = 0; i < years.size() - 1; i++) {
                final int interval = years.get(i + 1) - years.get(i);

                if (interval == minInterval) {
                    summaryMin.add(new SummaryItem(entry, i));
                }

                if (interval == maxInterval) {
                    summaryMax.add(new SummaryItem(entry, i));
                }
            }
        }

        return new Summary(summaryMax, summaryMin);
    }

    private static void removeProducersWithSingleWin(final Map<String, List<Integer>> producerYearsMap) {
        producerYearsMap.entrySet().removeIf(entry -> entry.getValue().size() == 1);
    }
}
