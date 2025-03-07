package com.llm.llmcommunication.rotinas;

import com.llm.llmcommunication.adapters.EditalClient;
import com.llm.llmcommunication.dto.EditalDTO;
import com.llm.llmcommunication.service.EditalService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerEdital {

    private final EditalService editalService;
    private final EditalClient editalClient;

    public SchedulerEdital(EditalService editalService, EditalClient editalClient) {
        this.editalService = editalService;
        this.editalClient = editalClient;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void classifyEdital() {
        List<EditalDTO> novosEditais = editalClient.getNewrsEditais();

        for (EditalDTO edital : novosEditais) {
            try {
                String classificationValue = editalService.classifyEditalFile(edital.title(), edital.agency());
                editalClient.updatePdfFile(edital, classificationValue);
            } catch (Exception e) {
                System.out.println("Erro ao classificar edital: " + edital.title());
            }
        }
    }


}
