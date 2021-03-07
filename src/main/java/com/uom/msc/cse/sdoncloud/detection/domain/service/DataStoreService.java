package com.uom.msc.cse.sdoncloud.detection.domain.service;

import com.uom.msc.cse.sdoncloud.detection.domain.entities.Student;
import com.uom.msc.cse.sdoncloud.detection.external.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataStoreService {

    @Autowired
    StudentRepository studentRepository;

    @Bean
    public void saveStudent(){
        Student s = new Student("00002","Pasindu");
        studentRepository.save(s);

        Optional<Student> student = studentRepository.findById("00001");
        if(student.isPresent()){
            System.out.println(student.get().getName());
        }else {
            System.out.println("Empty Student");
        }

    }
}
