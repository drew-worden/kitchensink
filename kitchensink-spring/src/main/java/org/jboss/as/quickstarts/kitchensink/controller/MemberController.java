package org.jboss.as.quickstarts.kitchensink.controller;

import com.mongodb.MongoWriteException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberCreateDTO;
import org.jboss.as.quickstarts.kitchensink.dtos.members.MemberResponseDTO;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/dashboard")
public class MemberController {
    private final MemberRegistration memberRegistration;

    public MemberController(MemberRegistration memberRegistration) {
        this.memberRegistration = memberRegistration;
    }

    @GetMapping
    public String getDashboard(Model model) {
        Iterable<MemberResponseDTO> members = memberRegistration.getAllMembers();
        model.addAttribute("members", members);
        return "dashboard";
    }

    @GetMapping("/new")
    public String showMemberForm(Model model) {
        model.addAttribute("member", new MemberCreateDTO());
        return "member-form";
    }

    @PostMapping("/new")
    public String registerMember(@Valid @ModelAttribute MemberCreateDTO memberCreateDTO,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            log.info("Validation errors found: {}", result.getAllErrors());
            return "member-form";
        }

        try {
            memberRegistration.register(memberCreateDTO);
            return "redirect:/dashboard";
        } catch (MongoWriteException e) {
            log.error("Database write error: {}", e.getMessage());
            if (e.getError().getCode() == 11000) {
                model.addAttribute("error", "A member with this email already exists.");
            } else {
                model.addAttribute("error", "Database error: " + e.getMessage());
            }
            // Re-add the member object to the model to preserve form data
            model.addAttribute("member", memberCreateDTO);
            return "member-form";
        } catch (Exception e) {
            log.error("Unexpected error registering member: {}", e.getMessage());
            model.addAttribute("error", "Failed to create member: " + e.getMessage());
            model.addAttribute("member", memberCreateDTO);
            return "member-form";
        }
    }

    @GetMapping("/{id}")
    public String getMemberById(@PathVariable String id, Model model) {
        log.info("Attempting to retrieve member with ID: {}", id);
        if (id == null || id.trim().isEmpty()) {
            log.warn("Received null or empty ID");
            model.addAttribute("error", "Invalid member ID");
            return "dashboard";
        }
        try {
            MemberResponseDTO member = memberRegistration.getMemberById(id);
            if (member == null) {
                log.warn("No member found for ID: {}", id);
                model.addAttribute("error", "No member exists with ID: " + id);
                return "dashboard";
            }
            log.info("Member retrieved: id={}, name={}, email={}, phoneNumber={}",
                    member.getId(), member.getName(), member.getEmail(), member.getPhoneNumber());
            model.addAttribute("member", member);
            return "member-detail";
        } catch (Exception e) {
            log.error("Error retrieving member with ID {}: {}", id, e.getMessage(), e);
            model.addAttribute("error", "Member not found: " + e.getMessage());
            return "dashboard";
        }
    }
}