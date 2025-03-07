package com.service.edital.edital_service.MVCController;

import com.service.edital.edital_service.dto.EditalDTO;
import com.service.edital.edital_service.fachada.Facade;
import jakarta.ws.rs.NotFoundException;
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
            EditalDTO dtoSaved = facade.createEdital(editalDTODados, editalDTOPdf);
            return new ResponseEntity<>(dtoSaved, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping(value="/", produces = "application/json")
    public ResponseEntity<?> getEdital() {
        List<EditalDTO> dtos = facade.getAllEditais();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping(value="/find", produces = "application/json")
    public ResponseEntity<EditalDTO> getEditalByTitleAndAgency(@RequestParam(name = "title", required = true) String title, @RequestParam(name = "agency", required = true) String agency) {
        EditalDTO dto;
        try{
            dto = facade.getEditalByTitleAndAgency(title, agency);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> getEditalPdf(@RequestParam String title, @RequestParam String agency) {
        Resource pdfFile;
        try {
            pdfFile = facade.getEditalPdfByTitle(title, agency);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfFile);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/newers")
    public ResponseEntity<?> getNewers() {
        List<EditalDTO> dtos = facade.getNewers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    // Para o llmcommunication atualizar
    @PutMapping(value = "update/{trl}")
    public ResponseEntity<?> updateEdital(@RequestBody EditalDTO editalDTO, @PathVariable("trl") String trlValue){
        return ResponseEntity.ok(facade.updateTrlEdital(editalDTO, trlValue));
    }

    @GetMapping(value = "/recommendation")
    public ResponseEntity<List<EditalDTO>> getRecommendations(@RequestParam("trl") String trlValue, @RequestParam("area") String area){
        return ResponseEntity.ok(facade.getRecommendations(trlValue, area));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }


}
