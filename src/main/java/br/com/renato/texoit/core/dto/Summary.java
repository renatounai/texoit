package br.com.renato.texoit.core.dto;

import java.util.List;

public record Summary(List<SummaryItem> max, List<SummaryItem> min) {
}
