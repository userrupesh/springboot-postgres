package com.example.PostgresDb.Controller;

import com.example.PostgresDb.Entity.Student;
import com.example.PostgresDb.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepo studentrepo;
    @PostMapping("/api/students")
    public  ResponseEntity<Student> saveStudent(@RequestBody Student studentDetails){
        return new ResponseEntity<>(studentrepo.save(studentDetails), HttpStatus.CREATED);


    }
    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentrepo.findAll(),HttpStatus.OK);

    }
    @GetMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> studentDetails = studentrepo.findById(id);
        if (studentDetails.isPresent())
        {
            return new ResponseEntity<>(studentDetails.get(),HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student>updateStudent(@PathVariable long id,@RequestBody Student stud) {
        Optional<Student> studentDetails = studentrepo.findById(id);
        if (studentDetails.isPresent()) {
            studentDetails.get().setStudentName(stud.getStudentName());
            studentDetails.get().setStudentEmail(stud.getStudentEmail());
            return new ResponseEntity<>(studentrepo.save(studentDetails.get()), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        Optional<Student> studentDetails = studentrepo.findById(id);
        if (studentDetails.isPresent())
        {
            studentrepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
