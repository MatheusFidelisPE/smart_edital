package com.llm.llmcommunication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llm.llmcommunication.adapters.EditalClient;
import com.llm.llmcommunication.adapters.LlmClient;
import com.llm.llmcommunication.dto.EditalDTO;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EditalService {

    private final EditalClient editalClient;
    private final LlmClient llmClient;


    public EditalService(EditalClient editalClient, LlmClient llmClient) {
        this.editalClient = editalClient;
        this.llmClient = llmClient;

    }

    public List<EditalDTO> getAllEditais() {
        return editalClient.getAllEditais();
    }

    public byte[] getPdfFile(String title, String agency) throws IOException {
        Resource pdf = editalClient.getPdfFile(title, agency);

        return pdf.getContentAsByteArray();
    }

    public String classifyEditalFile(String title, String agency) throws IOException {
        Resource pdf = editalClient.getPdfFile(title, agency);
        String classifiedEdital = llmClient.classifyEdital("Classifique o documento nesse arquivo em [Documento pessoal, Documento Organizacional ou Documento Residêncial]",pdf.getContentAsByteArray());
        String classification = getClassification(classifiedEdital);
        EditalDTO editalDTO = editalClient.getEdital(agency, title);

        editalClient.updatePdfFile(editalDTO, classification);
        return classification;
    }

    public String chat(String quest, String editalTitle, String agency) throws IOException {
        Resource pdf = editalClient.getPdfFile(editalTitle, agency);
        String prompt = String.format("Responda a seguinte pergunta com base no edital que estou te enviando\nPergunta: %s", quest);
        String body = llmClient.chatEdital(prompt,pdf.getContentAsByteArray());

        return getClassification(body);
    }

    private String getClassification(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(json);

        String classificationValue = rootNode.path("candidates")
                .path(0)
                .path("content")
                .path("parts")
                .path(0)
                .path("text")
                .asText()
                .trim();
        return classificationValue;
    }
}
