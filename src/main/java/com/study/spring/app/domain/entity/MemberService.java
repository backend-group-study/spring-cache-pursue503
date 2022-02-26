package com.study.spring.app.domain.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private static final MemberDTO DTO = new MemberDTO();

    @Cacheable(cacheNames = "userData", key = "#id")
    public MemberDTO getDto(Long id) {
        log.info("============== 캐시 데이터가 존재 하지 않습니다 ======");
        return cacheMemberDTO(id);
    }

    @CachePut(cacheNames = "userData" , key = "#id")
    public MemberDTO cacheMemberDTO(Long id) {
        log.info("======= 캐시 데이터 업데이트 ========");

        Member member = memberRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new MemberDTO(member);
    }

    @CacheEvict(cacheNames = "userData", key = "#id")
    public boolean deleteCache(Long id) {
        log.info("========= 캐시 삭제 {} =======", id);
        return true;
    }

    @Transactional
    public Long userSave(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO.toEntity()).getId();
    }

    @Transactional
    public void updateUser(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new RuntimeException(""));
        member.setAge(memberDTO.getAge());
        member.setName(member.getName());
    }

}
