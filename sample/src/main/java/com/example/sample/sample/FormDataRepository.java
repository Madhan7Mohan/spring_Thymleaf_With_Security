package com.example.sample.sample;


import org.springframework.data.jpa.repository.JpaRepository;

public interface FormDataRepository extends JpaRepository<FormData,Integer>{

    FormData findByBookName(String bookName);

}
