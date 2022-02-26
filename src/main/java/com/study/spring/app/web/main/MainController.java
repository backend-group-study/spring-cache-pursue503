package com.study.spring.app.web.main;

import com.study.spring.app.domain.CacheData;
import com.study.spring.app.domain.entity.MemberDTO;
import com.study.spring.app.domain.entity.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private final MemberService memberService;

    @PostMapping("/save")
    public Long saveUser(@RequestBody MemberDTO memberDTO) {
        return memberService.userSave(memberDTO);
    }

    @GetMapping("/find/{id}")
    public MemberDTO findMember(@PathVariable("id") Long id) {
        return memberService.getDto(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody MemberDTO memberDTO) {
        memberService.updateUser(memberDTO);
        memberService.deleteCache(memberDTO.getId());
    }

}
