package com.llm.llmcommunication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.llm.llmcommunication.dto.EditalDTO;
import com.llm.llmcommunication.adapters.EditalClient;
import com.llm.llmcommunication.rotinas.SchedulerEdital;
import com.llm.llmcommunication.service.EditalService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Facade {

    private final EditalService editalService;
    private final SchedulerEdital schedulerEdital;

    public Facade(EditalService editalService, SchedulerEdital schedulerEdital) {
        this.editalService = editalService;
        this.schedulerEdital = schedulerEdital;
    }

    public List<EditalDTO> getAllEditais() {
        return editalService.getAllEditais();
    }

    public byte[] getPdfFile(String s, String s1) throws IOException {
        return editalService.getPdfFile(s, s1);

    }

    public String classifyEdital(String title, String agency) {
        try {
            return editalService.classifyEditalFile(title, agency);
        } catch (IOException e) {
            return "Sem classificação";
        }
    }

    public Boolean testNew() {
        schedulerEdital.classifyEdital();
        return true;
    }

    public String chat(String quest, String editalTitle, String agency) throws IOException {
        return editalService.chat(quest, editalTitle, agency);
    }
}
