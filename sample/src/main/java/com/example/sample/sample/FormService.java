package com.example.sample.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    private FormDataRepository formDataRepository;

    public List<FormData> getAllBooks() {
        return formDataRepository.findAll();
    }

    public FormData getBookById(int id) {
        Optional<FormData> formDataOptional = formDataRepository.findById(id);
        return formDataOptional.orElse(null);
    }

    public FormData getBookByName(String name) {
        return formDataRepository.findByBookName(name);
    }

    public FormData saveOrUpdate(FormData formData) {
        return formDataRepository.save(formData);
    }

    public FormData delete(int id) {
        formDataRepository.deleteById(id);
        return null;
    }
}