package com.icia.member.Controller;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm(){
        return "memberPages/memberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "redirectURI", defaultValue = "/member/mypage") String redirectURI,
                        Model model) {
        model.addAttribute("redirectURI", redirectURI);
        return "memberPages/memberLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session,
                        @RequestParam("redirectURI") String redirectURI) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
//            return "memberPages/memberMain";
            // 사용자가 로그인 성공하면, 직전에 요청한 페이지로 이동시킴.
            // 별도로 요청한 페이지가 없다면 정상적으로 myPage로 이동시킴.(redirect:/member/mypage)
            return "redirect:" + redirectURI;
        } else {
            return "memberPages/memberLogin";
        }
    }

    @GetMapping("/mypage")
    public String myPage() {
        return "memberPages/memberMain";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginEmail");
        return "redirect:/";
    }

    @GetMapping("/memberlist")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList",memberDTOList);
        return "memberPages/memberList";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
