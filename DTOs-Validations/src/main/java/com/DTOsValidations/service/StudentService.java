package com.DTOsValidations.service;


import com.DTOsValidations.dto.CreateStudentRequestDTO;
import com.DTOsValidations.dto.CreateStudentResponseDTO;
import com.DTOsValidations.dto.UpdateStudentRequestDTO;
import com.DTOsValidations.dto.UpdateStudentResponseDTO;
import com.DTOsValidations.entity.Student;
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

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);

    }
    public CreateStudentResponseDTO getStudent(Long id){
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);
        if (studentResp.isPresent()){
            return mapToDto(studentResp.get());
        }
        return null;
    }

    public List<CreateStudentResponseDTO> getAllStudent(){
        List<Student> studentList = studentRepository.findByDeletedIsFalse();


        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }
    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO updateStudentRequestDTO){
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()){
            return null;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setName(updateStudentRequestDTO.getName());
        studentToSave.setRollNo(updateStudentRequestDTO.getRollNo());
        studentToSave.setSubject(updateStudentRequestDTO.getSubject());
        studentToSave.setAge(updateStudentRequestDTO.getAge());
        studentToSave.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(studentToSave);

        return mapToUpdateDto(savedStudent);


    }
    public Boolean deleteStudent(Long id){
        Boolean isStudent = studentRepository.existsById(id);

        if (!isStudent){
            return false;
        }
        studentRepository.deleteById(id);

        return  true;

    }
    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }
    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()){
            return  false;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);

        studentRepository.save(studentToSave);

        return true;
    }

    private Student mapToEntity(CreateStudentRequestDTO createStudentRequestDTO){
        Student student = new Student();
        student.setName(createStudentRequestDTO.getName());
        student.setAge(createStudentRequestDTO.getAge());
        student.setEmail(createStudentRequestDTO.getEmail());
        student.setRollNo(createStudentRequestDTO.getRollNo());
        student.setSubject(createStudentRequestDTO.getSubject());

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
}
