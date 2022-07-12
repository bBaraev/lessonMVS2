package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.entity.Teacher;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/")
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

   @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }


    @ModelAttribute("courseList")
    public List<Course> findAllCourse(){
        return courseService.getAllCourses();
    }

    @GetMapping("getTeacher")
    public String getTeachers(Model model){
       List<Teacher>teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers",teachers);
        return "teacher/teachers";

    }
    @GetMapping("/addTeacher")
    public String addCourse( Model model){
        model.addAttribute("teacher",new Teacher());
        return "teacher/addTeacher";
    }

    @PostMapping("saveTeacher")
    public String saveTeacher( @RequestParam ("courseId") Long id,@ModelAttribute("teacher") Teacher teacher){
       courseService.getCourseById(id).setTeacher(teacher);
       teacherService.addTeacher(teacher);
       teacher.setCourse(courseService.getCourseById(id));
        return "redirect:/getTeacher?coursId"+id;
    }

    @GetMapping("/updateTeacher")
    public String updateCourse(@RequestParam("teacherId") Long id, Model model){
        Teacher teacher =  teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/updateTeacher";
    }

    @PostMapping("/saveUpdateTeacher")
    public String saveUpdateTeacher(@ModelAttribute("teacher") Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "redirect:/getTeacher";
    }

    @RequestMapping("/deleteTeacher")
    public String deleteCourse(@RequestParam("teacherId") Long id){
        teacherService.deleteTeacher(teacherService.getTeacherById(id));
        return "redirect:/getTeacher";
    }



}
