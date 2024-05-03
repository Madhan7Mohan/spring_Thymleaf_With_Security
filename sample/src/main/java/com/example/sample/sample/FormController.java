package com.example.sample.sample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FormController {

    @Autowired
    private FormDataRepository formDataRepository;



    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("formData", new FormData());
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute FormData formData, Model model) {
        formDataRepository.save(formData);

        model.addAttribute("successMessage", "Data saved successfully!");
        return "redirect:/form";
    }
    @GetMapping("/form/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<FormData> formDataOptional = formDataRepository.findById(id);
        if (formDataOptional.isPresent()) {
            model.addAttribute("formData", formDataOptional.get());
            return "update-form";
        } else {
            model.addAttribute("errorMessage", "No details found for ID: " + id);
            return "error";
        }
    }


    @PostMapping("/form/{id}")
    public String updateFormData(@PathVariable Integer id, @ModelAttribute FormData formData, Model model) {
        Optional<FormData> existingFormDataOptional = formDataRepository.findById(id);
        if (existingFormDataOptional.isPresent()) {
            FormData existingFormData = existingFormDataOptional.get();

            existingFormData.setBookName(formData.getBookName());
            existingFormData.setAuthorName(formData.getAuthorName());
            existingFormData.setCountOfPages(formData.getCountOfPages());
            existingFormData.setCost(formData.getCost());
            formDataRepository.save(existingFormData);
            model.addAttribute("successMessage", "Data updated successfully!");
            return "redirect:/form/{id}";
        } else {
            model.addAttribute("errorMessage", "No details found for ID: " + id);
            return "error";
        }
    }


}

