package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;
@Controller
@RequestMapping("/")
public class StudentController {

    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public StudentController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }
    @ModelAttribute("groupList")
    public List<Group> findAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping("/getStudent")
    public String getAllStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "student/students";

    }
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "student/addStudent";
    }
    @PostMapping("/saveStudent")
    public String saveStudent( @ModelAttribute("student")Student student){
        studentService.addStudent(student);
        return "redirect:/getStudent";

        }

    @GetMapping("updateStudent")
    public String updateStudent(@RequestParam("studentId") Long id , Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "student/updateStudent";
    }
    @PostMapping("/saveUpdateStudent")
    public String saveUpdateStudent( @ModelAttribute("student") Student student){
       studentService.updateStudent(student);
       return "redirect:/getStudent";
    }
    @RequestMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("studentId") Long id ){
        studentService.deleteStudent(studentService.getStudentById(id));
        return "redirect:/getStudent";
    }

}
