package br.com.renato.texoit.core.dto;

import java.util.List;
import java.util.Map;

public record SummaryItem(
        String producer,
        Integer previousWin,
        Integer followingWin,
        Integer interval
) {

    public SummaryItem(Map.Entry<String, List<Integer>> entry, Integer i) {
        this(entry.getKey(), entry.getValue().get(i), entry.getValue().get(i + 1), entry.getValue().get(i + 1) - entry.getValue().get(i));
    }
}