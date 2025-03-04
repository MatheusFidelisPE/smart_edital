package com.service.edital.edital_service.controller;

import com.service.edital.edital_service.dto.EditalDTO;
import com.service.edital.edital_service.model.Edital;
import com.service.edital.edital_service.repository.EditalRepository;
import jakarta.annotation.Resources;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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

    public Optional<?> createEdital(EditalDTO dto, MultipartFile pdf) throws IOException {
        Edital edital = new Edital().builder()
                .title(dto.title())
                .description(dto.description())
                .pathToPdfFile("")
                .build();

        editalRepository.save(edital);

        return saveEditalPdf(pdf, dto.title());
    }

    public Optional<Resource> getEditalPdfByTitle(String title) throws IOException {
        if (!title.endsWith(".pdf")){
            title = title+".pdf";
        }

        File file = new File(pathToFiles+title);
        var path = Paths.get(file.getAbsolutePath());
        var resource = new ByteArrayResource(Files.readAllBytes(path));
        return Optional.of(resource);


    }

    private Optional<String> saveEditalPdf(MultipartFile pdf, String title) throws IOException {
        // Salvar o arquivo (opcional)
        String uploadDir = pathToFiles;
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs(); // Criar diretório se não existir
        }
        if(!title.endsWith(".pdf")){
            title = title+".pdf";
        }

        String filePath = uploadDir +"/"+ title;
        pdf.transferTo(new File(filePath));

        return Optional.of("Pdf File saved successfully");
    }

    public Optional<List<EditalDTO>> getAllEditais() {
        List<Edital> editais = editalRepository.findAll();
        List<EditalDTO> dtos = editais.stream()
                .map(ed -> new EditalDTO(ed.getTitle(), ed.getDescription()))
                .toList();

        return Optional.of(dtos);

    }
}
