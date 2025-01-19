package com.example.MySpringBootApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.MySpringBootApp.repository.CVRepository;
import com.example.MySpringBootApp.entity.CVEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

@RestController
public class CVController {

    @Autowired
    private CVRepository cvRepository;

    // The view will be generated using the following steps:
    // 1. Upload the CV
    @PostMapping("/uploadCV")
    public ResponseEntity<String> uploadCV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a file!", HttpStatus.BAD_REQUEST);
        }

        // You can add logic here to save the file to the server or database

        return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
    }

    // 2. Analyze the CV
    @PostMapping("/analyzeCV")
    public ResponseEntity<String> analyzeCV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a file to analyze!", HttpStatus.BAD_REQUEST);
        }

        // Add logic here to analyze the CV
        // For example, you can use a third-party library or service to parse and analyze the CV content

        return new ResponseEntity<>("CV analyzed successfully!", HttpStatus.OK);
    }

    // 3. Generate the career path matching towards Solution Architect
    @PostMapping("/generateCareerPath")
    public ResponseEntity<String> generateCareerPath(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a file to generate career path!", HttpStatus.BAD_REQUEST);
        }

        // Add logic here to generate the career path
        // For example, you can use predefined rules or a machine learning model to generate the career path

        return new ResponseEntity<>("Career path generated successfully!", HttpStatus.OK);
    }

    // 4. Store the view in the database
    @PostMapping("/storeView")
    public ResponseEntity<String> storeView(@RequestParam("view") String view) {
        // Add logic here to store the view in the database
        CVEntity cvEntity = new CVEntity();
        cvEntity.setView(view);
        cvRepository.save(cvEntity);

        return new ResponseEntity<>("View stored successfully!", HttpStatus.OK);
    }

    // 5. Update the view with the progress made
    @PostMapping("/updateView")
    public ResponseEntity<String> updateView(@RequestParam("id") Long id, @RequestParam("progress") String progress) {
        // Add logic here to update the view with the progress made
        CVEntity cvEntity = cvRepository.findById(id).orElse(null);
        if (cvEntity == null) {
            return new ResponseEntity<>("View not found!", HttpStatus.NOT_FOUND);
        }

        cvEntity.setView(progress);
        cvRepository.save(cvEntity);

        return new ResponseEntity<>("View updated successfully!", HttpStatus.OK);
    }

    // 6. Generate the view in the form of a PDF
    @PostMapping("/generatePDF")
    public ResponseEntity<byte[]> generatePDF(@RequestParam("id") Long id) {
        CVEntity cvEntity = cvRepository.findById(id).orElse(null);
        if (cvEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph(cvEntity.getView()));
            document.close();
        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=view.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    // 7. Generate the view in the form of a web page
    @PostMapping("/generateWebPage")
    public ResponseEntity<String> generateWebPage(@RequestParam("id") Long id) {
        CVEntity cvEntity = cvRepository.findById(id).orElse(null);
        if (cvEntity == null) {
            return new ResponseEntity<>("View not found!", HttpStatus.NOT_FOUND);
        }

        // Add logic here to generate the view in the form of a web page
        // For example, you can return the view content as HTML

        return new ResponseEntity<>(cvEntity.getView(), HttpStatus.OK);
    }
}
