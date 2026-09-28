package com.example.internship.controller;

import com.example.internship.model.Company;
import com.example.internship.model.Registration;
import com.example.internship.repository.CompanyRepository;
import com.example.internship.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/register")
    public String showRegisterPage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Company> companies;
        if (keyword != null && !keyword.trim().isEmpty()) {
            companies = companyRepository.findByNameContainingIgnoreCaseOrIndustryContainingIgnoreCase(keyword, keyword);
        } else {
            companies = companyRepository.findAll();
        }
        
        model.addAttribute("companies", companies);
        model.addAttribute("registration", new Registration());
        model.addAttribute("keyword", keyword);
        return "student-register";
    }

    @PostMapping("/register")
    public String handleRegistration(@ModelAttribute Registration registration, RedirectAttributes redirectAttributes) {
        if (registration.getStudentId() == null || registration.getStudentId().isEmpty() || registration.getCompanyId() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Thông tin nhập không hợp lệ, vui lòng điền đầy đủ!");
            return "redirect:/student/register";
        }

        List<Registration> pendingList = registrationRepository.findByStudentIdAndStatus(registration.getStudentId(), "Chờ duyệt");
        if (!pendingList.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cảnh báo: Bạn đang có một đăng ký ở trạng thái 'Chờ duyệt', không thể tạo thêm!");
            return "redirect:/student/register";
        }

        registration.setStatus("Chờ duyệt");
        registrationRepository.save(registration);

        redirectAttributes.addFlashAttribute("successMessage", "Gửi đăng ký thực tập thành công! Trạng thái: Chờ duyệt.");
        return "redirect:/student/register";
    }
}
