package com.example.sample.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class RestFormController {

    @Autowired
    private FormService formService;

    @Autowired
    private FormDataRepository formDataRepository;

    @GetMapping("/formdata")
    public List<FormData> getAllFormData() {
        return formService.getAllBooks();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<FormData> getFormDataById(@PathVariable Integer id) {
        FormData formData = formDataRepository.findById(id).orElse(null);
        if (formData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(formData);
    }

    @GetMapping("/form/name/{name}")
    public ResponseEntity<FormData> getFormDataByName(@PathVariable String name) {
        FormData formData = formDataRepository.findByBookName(name);
        if (formData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(formData);
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<?> deleteFormDataById(@PathVariable Integer id) {
        formService.delete(id);
        return ResponseEntity.ok().build();
    }


//    @PutMapping("/form/{id}")
//    public ResponseEntity<?> updateFormData(@PathVariable Integer id, @RequestBody FormData formData) {
//        formData.setId(id);
//        formDataRepository.save(formData);
//        return ResponseEntity.ok().build();
//    }
}