package com.DTOsValidations.service;


import com.DTOsValidations.dto.CreateStudentRequestDTO;
import com.DTOsValidations.dto.CreateStudentResponseDTO;
import com.DTOsValidations.dto.UpdateStudentRequestDTO;
import com.DTOsValidations.dto.UpdateStudentResponseDTO;
import com.DTOsValidations.entity.Student;
import com.DTOsValidations.exception.DuplicateResourceException;
import com.DTOsValidations.exception.ResourceNotFoundException;
import com.DTOsValidations.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO createStudentRequestDTO){
        Student student = mapToEntity(createStudentRequestDTO);
        if (emailExists(student)){
            throw new DuplicateResourceException("Student with Email "+student.getEmail()+" already exists");
        }
        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);

    }
    public CreateStudentResponseDTO getStudent(Long id){
        Student studentResp = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Student with id "+id+" Not Found "));
        return mapToDto(studentResp);

    }

    public List<CreateStudentResponseDTO> getAllStudent(){
        List<Student> studentList = studentRepository.findByDeletedIsFalse();


        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }
    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO updateStudentRequestDTO){
        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Student with id "+id+" Not Found "));


        existingStudent.setName(updateStudentRequestDTO.getName());
        existingStudent.setRollNo(updateStudentRequestDTO.getRollNo());
        existingStudent.setSubject(updateStudentRequestDTO.getSubject());
        existingStudent.setAge(updateStudentRequestDTO.getAge());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);

        return mapToUpdateDto(savedStudent);


    }
    public void deleteStudent(Long id){
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" Not Found "));

        studentRepository.delete(studentToBeDeleted);



    }
    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }
    public void deleteStudentSoftly(Long id){
        Student studentToBeDeleted = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" Not Found "));

        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted);

    }

    private Student mapToEntity(CreateStudentRequestDTO createStudentRequestDTO){
        Student student = new Student();
        student.setName(createStudentRequestDTO.getName());
        student.setAge(createStudentRequestDTO.getAge());
        student.setEmail(createStudentRequestDTO.getEmail());
        student.setRollNo(createStudentRequestDTO.getRollNo());
        student.setSubject(createStudentRequestDTO.getSubject());


        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);
        return student;
    }
    private CreateStudentResponseDTO mapToDto(Student student){
        CreateStudentResponseDTO createStudentResponseDTO = new CreateStudentResponseDTO();
        createStudentResponseDTO.setId(student.getId());
        createStudentResponseDTO.setName(student.getName());
        createStudentResponseDTO.setAge(student.getAge());
        createStudentResponseDTO.setEmail(student.getEmail());
        createStudentResponseDTO.setRollNo(student.getRollNo());
        createStudentResponseDTO.setSubject(student.getSubject());
        createStudentResponseDTO.setMessage("Student Saves Successfully");

        createStudentResponseDTO.setCreatedAt(student.getCreatedAt());
        createStudentResponseDTO.setUpdatedAt(student.getUpdatedAt());


        return createStudentResponseDTO;

    }
    private UpdateStudentResponseDTO mapToUpdateDto(Student student){
        UpdateStudentResponseDTO updateStudentResponseDTO = new UpdateStudentResponseDTO();
        updateStudentResponseDTO.setId(student.getId());
        updateStudentResponseDTO.setName(student.getName());
        updateStudentResponseDTO.setAge(student.getAge());
        updateStudentResponseDTO.setEmail(student.getEmail());
        updateStudentResponseDTO.setRollNo(student.getRollNo());
        updateStudentResponseDTO.setSubject(student.getSubject());
        updateStudentResponseDTO.setMessage("Student Data Updated Successfully");
        updateStudentResponseDTO.setCreatedAt(student.getCreatedAt());
        updateStudentResponseDTO.setUpdatedAt(student.getUpdatedAt());

        return updateStudentResponseDTO;
    }
    private boolean emailExists(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }
}
