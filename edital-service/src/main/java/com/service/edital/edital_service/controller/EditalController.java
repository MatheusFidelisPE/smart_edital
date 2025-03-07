package com.service.edital.edital_service.controller;

import com.service.edital.edital_service.dto.EditalDTO;
import com.service.edital.edital_service.model.Edital;
import com.service.edital.edital_service.repository.EditalRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class EditalController {
    @Value("${path.to.files}")
    private String pathToFiles;

    private final EditalRepository editalRepository;

    @Autowired
    public EditalController(EditalRepository editalRepository) {
        this.editalRepository = editalRepository;
    }

    public Optional<EditalDTO> createEdital(EditalDTO dto, MultipartFile pdf) throws IOException {
        String path = String.format("%s_%s", dto.title(), dto.agency());
        path = path.replaceAll(" ", "-");

        Edital edital = new Edital().builder()
                .title(dto.title())
                .agency(dto.agency())
                .description(dto.description())
                .pathToPdfFile(path)
                .publicationDate(dto.publicationDate())
                .closingDate(dto.closingDate())
                .build();

        editalRepository.save(edital);
        saveEditalPdf(pdf, path);
        return Optional.of(dto);
    }

    public Optional<Resource> getEditalPdfByTitleAndAgency(String title, String agency) throws IOException {

        Optional<Edital> opEdital = editalRepository.findByTitleAndAgency(title, agency);

        Edital ed = opEdital.orElseThrow();
        String fileName = ed.getPathToPdfFile();

        if (!fileName.endsWith(".pdf")) {
            fileName = fileName + ".pdf";
        }

        File file = new File(pathToFiles+fileName);
        var path = Paths.get(file.getAbsolutePath());
        var resource = new ByteArrayResource(Files.readAllBytes(path));
        return Optional.of(resource);
    }

    public Optional<List<EditalDTO>> getAllEditais() {
        List<Edital> editais = editalRepository.findAll();

        List<EditalDTO> dtos = editais.stream()
                .map(ed -> new EditalDTO(ed.getTitle(),
                        ed.getDescription(),
                        ed.getAgency(),
                        ed.getPathToPdfFile(),
                        ed.getPublicationDate(),
                        ed.getClosingDate(),
                        ed.getClassificationTrl()))
                .toList();

        return Optional.of(dtos);

    }

    public Optional<EditalDTO> getEditalByTitleAndAgency(String title, String agency) throws NotFoundException {
        Optional<Edital> edital = editalRepository.findByTitleAndAgency(title, agency);

        if (edital.isEmpty()) {
            throw new com.service.edital.edital_service.utils.NotFoundException("Edital not found");
        }

        EditalDTO dto = new EditalDTO(edital.get().getTitle(),
                edital.get().getDescription(),
                edital.get().getAgency(),
                edital.get().getPathToPdfFile(),
                edital.get().getPublicationDate(),
                edital.get().getClosingDate(),
                edital.get().getClassificationTrl());

        return Optional.of(dto);
    }

    private Optional<String> saveEditalPdf(MultipartFile pdf, String fileName) throws IOException {
        // Salvar o arquivo (opcional)
        String uploadDir = pathToFiles;
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs(); // Criar diretório se não existir
        }
        if(!fileName.endsWith(".pdf")){
            fileName = fileName+".pdf";
        }

        String filePath = uploadDir + fileName;
        pdf.transferTo(new File(filePath));

        return Optional.of("Pdf File saved successfully");
    }

    public List<EditalDTO> getNewers() {
        Optional<List<Edital>> editaisNovos = editalRepository.findnewers();

        if(editaisNovos.isEmpty()){
            return List.of();
        }
        List<EditalDTO> dtos = editaisNovos.get().stream()
                .map(ed -> new EditalDTO(ed.getTitle(),
                        ed.getDescription(),
                        ed.getAgency(),
                        ed.getPathToPdfFile(),
                        ed.getPublicationDate(),
                        ed.getClosingDate(),
                        ed.getClassificationTrl()))
                .toList();

       return dtos;
    }

    public Object updateEdital(EditalDTO editalDTO, String trlValue) {
        Optional<Edital> optEdital = editalRepository.findByTitleAndAgency(editalDTO.title(), editalDTO.agency());

        if(optEdital.isEmpty()){
            throw new NotFoundException();
        }

        Edital edital = optEdital.get();
        edital.setClassificationTrl(trlValue);
        edital.setIsClassified(true);

        editalRepository.save(edital);
        return new EditalDTO(edital.getTitle(),
                edital.getDescription(),
                edital.getAgency(),
                edital.getPathToPdfFile(),
                edital.getPublicationDate(),
                edital.getClosingDate(),
                edital.getClassificationTrl());
    }

    public List<EditalDTO> getRecommendations(String trlValue, String area) {
        Optional<List<Edital>> optEditais = editalRepository.findByTrlValueAndArea(trlValue, area);


        List<EditalDTO> dtos = optEditais.orElseThrow().stream()
                .map(ed -> new EditalDTO(ed.getTitle(),
                        ed.getDescription(),
                        ed.getAgency(),
                        ed.getPathToPdfFile(),
                        ed.getPublicationDate(),
                        ed.getClosingDate(),
                        ed.getClassificationTrl()))
                .toList();

        return dtos;





    }
}
