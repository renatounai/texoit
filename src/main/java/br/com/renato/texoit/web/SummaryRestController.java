package br.com.renato.texoit.web;

import br.com.renato.texoit.core.dto.Summary;
import br.com.renato.texoit.core.service.SummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summary")
public class SummaryRestController {
    private final SummaryService summaryService;

    public SummaryRestController(final SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping
    public Summary getSummary() {
        return summaryService.findSummary();
    }

}
