package com.llm.llmcommunication.adapters;

import com.llm.llmcommunication.dto.EditalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class EditalClient {

    @Autowired
    private final RestTemplate restTemplate;

    @Value("${edital.service.url}")
    private String editalServiceUrl;

    public EditalClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<EditalDTO> getAllEditais() {
        String url = String.format("http://%s/edital/", editalServiceUrl);
        ResponseEntity<List<EditalDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EditalDTO>>() {
                });
        return response.getBody();
    }
    public Resource getPdfFile(String title, String agency){

        String url = String.format("http://%s/edital/pdf?title=%s&agency=%s", editalServiceUrl, title, agency);

        ResponseEntity<Resource> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Resource>() {
                });
        return response.getBody();
    }
    public List<EditalDTO> getNewrsEditais() {
        String url = String.format("http://%s/edital/newers", editalServiceUrl);
        ResponseEntity<List<EditalDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EditalDTO>>() {
                });
        return response.getBody();

    }
    public ResponseEntity<?> updatePdfFile(EditalDTO dto, String trl){
        String url = String.format("http://%s/edital/update/%s", editalServiceUrl, trl);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EditalDTO> request = new HttpEntity<>(dto, httpHeaders);
        ResponseEntity<?> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                Void.class
        );
        return response;
    }
    public EditalDTO getEdital(String agency, String title) {

        String url = String.format("http://%s/edital/find?title=%s&agency=%s", editalServiceUrl, title, agency);
        ResponseEntity<EditalDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EditalDTO>() {
                });

        return response.getBody();
    }

}
