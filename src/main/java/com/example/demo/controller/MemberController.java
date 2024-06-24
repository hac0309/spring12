package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController { // 중간 경로 X

    @Autowired
    MemberService service;
    
    // 회원가입 메소드
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody MemberDTO dto) {
        boolean result = service.register(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
        // 201 성공코드와 처리결과 반환
    }
    
    // 회원목록 조회 메소드
    @GetMapping("/member/list")
    public ResponseEntity<List<MemberDTO>> getList() {
        List<MemberDTO> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
        // 200 성공코드와 회원목록 반환
    }
}
