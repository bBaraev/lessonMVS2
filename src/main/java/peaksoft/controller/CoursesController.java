package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.List;
@Controller
public class CoursesController {

    private final CompanyService companyService;
    private final CourseService coursesService;
    private final GroupService groupService;

    @Autowired
    public CoursesController(CompanyService companyService, CourseService coursesService,GroupService groupService) {
        this.companyService = companyService;
        this.coursesService = coursesService;
        this.groupService=groupService;
    }


    @ModelAttribute("companyList")
    public List<Company>getAllCompany(){
        return companyService.getAllCompanies();
    }


    @GetMapping("/getCourses")
    public String getAllCourses( Model model){
        model.addAttribute("courses",coursesService.getAllCourses());
        return "courses/courses";

    }


    @GetMapping("/addCourse")
    public String addCourse( Model model){
        model.addAttribute("course",new Course());
        return "courses/addCourse";
    }

    @PostMapping("saveCourse")
    public String saveCourse(@RequestParam("companyId") Long id,@ModelAttribute("course") Course course){
        companyService.getCompanyById(id).getCourses().add(course);
        course.setCompany(companyService.getCompanyById(id));
        coursesService.addCourse(course);
        return "redirect:/getCourses?companyId="+id;
    }

    @GetMapping("/updateCourse")
    public String updateCourse(@RequestParam("courseId") Long id, Model model){
        Course course =  coursesService.getCourseById(id);
        model.addAttribute("course", course);
        return "courses/updateCourse";
    }

    @PostMapping("/saveUpdateCourse")
    public String saveUpdateCourse(@RequestParam("companyId") Long id,@ModelAttribute("course") Course course){
        course.setCompany(companyService.getCompanyById(id));
        coursesService.updateCourse(course);
        return "redirect:/getCourses?companyId="+id;
    }

    @RequestMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") Long id, @RequestParam("companyId") Long id2){
        coursesService.deleteCourse(coursesService.getCourseById(id));
        return "redirect:/getCourses?companyId="+id2;
    }
    @GetMapping("/findBy/{groupId}")
    public String getGroups(@PathVariable("groupId")Long id,Model model){
        List<Course>courses=coursesService.findByGroupId(id);
        model.addAttribute("getBiGroup",courses);
        model.addAttribute("groupId",id);
        return "courses/getByGroup";
    }
}
