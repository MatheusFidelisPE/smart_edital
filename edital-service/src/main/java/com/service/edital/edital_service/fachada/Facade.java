package com.service.edital.edital_service.fachada;

import com.service.edital.edital_service.controller.EditalController;
import com.service.edital.edital_service.dto.EditalDTO;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Component
public class Facade {

    private final EditalController controllerEdital;

    @Autowired
    public Facade(EditalController controllerEdital) {
        this.controllerEdital = controllerEdital;
    }
    public void createEdital(EditalDTO dados, MultipartFile pdf) throws IOException {
        controllerEdital.createEdital(dados, pdf);
    }

    public void getEdital() {
        // TODO
    }

    public void getEditalById() {
        // TODO
    }

    public Resource getEditalPdfByTitle(String title, String agency) throws IOException {
        Optional<Resource> file = controllerEdital.getEditalPdfByTitleAndAgency(title, agency);

        return file.orElseThrow();
    }

    public List<EditalDTO> getAllEditais() {
        return controllerEdital.getAllEditais().orElseThrow();

    }

    public EditalDTO getEditalByTitleAndAgency(String title, String agency) throws NotFoundException {
        Optional<EditalDTO> edital = controllerEdital.getEditalByTitleAndAgency(title, agency);
        return edital.get();
    }

    public List<EditalDTO> getNewers() {
        return controllerEdital.getNewers();
    }

    public Object updateTrlEdital(EditalDTO editalDTO, String trlValue) {
        return controllerEdital.updateEdital(editalDTO, trlValue);
    }
}
