package com.service.edital.edital_service.MVCController;

import com.service.edital.edital_service.dto.EditalDTO;
import com.service.edital.edital_service.fachada.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/edital")
public class Controller {

    @Autowired
    private Facade facade;

    @PostMapping(value = "/", consumes = "multipart/form-data" , produces = "application/json")
        public ResponseEntity<?> createEdital(@ModelAttribute EditalDTO editalDTODados,
                                          @RequestParam("pdf") MultipartFile editalDTOPdf) {

        try {
            facade.createEdital(editalDTODados, editalDTOPdf);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping(value="/", produces = "application/json")
    public ResponseEntity<?> getEdital() {
        List<EditalDTO> dtos = facade.getAllEditais();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value="/{id}", produces = "application/json")
    public void getEditalById() {
        // TODO
    }
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> getEditalPdf(@RequestParam String title) {
        Resource pdfFile;
        try {
            pdfFile = facade.getEditalPdfByTitle(title);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfFile);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
