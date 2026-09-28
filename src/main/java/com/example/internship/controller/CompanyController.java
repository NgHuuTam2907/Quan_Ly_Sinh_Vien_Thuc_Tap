package com.example.internship.controller;

import com.example.internship.model.Company;
import com.example.internship.repository.CompanyRepository;
import com.example.internship.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping
    public String listCompanies(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("companies", companyRepository.findByNameContainingIgnoreCaseOrIndustryContainingIgnoreCase(keyword, keyword));
        } else {
            model.addAttribute("companies", companyRepository.findAll());
        }
        model.addAttribute("newCompany", new Company());
        return "company-manage";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute("newCompany") Company company, RedirectAttributes redirectAttributes) {
        if (company.getName() == null || company.getName().isEmpty() || company.getIndustry() == null || company.getIndustry().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Thông tin doanh nghiệp không hợp lệ!");
            return "redirect:/admin/companies";
        }
        companyRepository.save(company);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm doanh nghiệp thành công!");
        return "redirect:/admin/companies";
    }

    @PostMapping("/update")
    public String updateCompany(@ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        companyRepository.save(company);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin thành công!");
        return "redirect:/admin/companies";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean hasRegistrations = registrationRepository.existsByCompanyId(id);
        if (hasRegistrations) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cảnh báo: Không thể xóa do Doanh nghiệp đang có sinh viên đăng ký thực tập!");
            return "redirect:/admin/companies";
        }

        companyRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa doanh nghiệp thành công!");
        return "redirect:/admin/companies";
    }
}
