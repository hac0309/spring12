package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/board") // 중간경로
public class BoardController {
    
    @Autowired
    BoardService service;

    // 게시물을 등록하는 메소드
    @PostMapping("/register")              // JSON Data -> class 객체로 변환
    public ResponseEntity<Integer> register(@RequestBody BoardDTO dto) {
        int no = service.register(dto);
        return new ResponseEntity<>(no, HttpStatus.CREATED); // CREATED => 201코드
        // 201성공코드 + 새로운 글 번호 반환
    }

    // 게시물 목록을 반환하는 메소드
    @GetMapping("/list")
    public ResponseEntity<List<BoardDTO>> getList() {

        List<BoardDTO> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
        // 200 성공코드 + 게시물 목록
    }
    
    // localhost:8080/board/read?no=1
    // 특정 게시물 정보를 반환하는 메소드
    @GetMapping("/read")                // 게시물번호
    public ResponseEntity<BoardDTO> read(@RequestParam(name = "no") int no) {
        
        BoardDTO dto = service.read(no);
        return new ResponseEntity<>(dto, HttpStatus.OK); // Header(200 OK) / Body(게시물정보)
        // 200 성공코드 + 게시물정보 반환
    }
    
    // 게시물 정보를 수정하는 메소드
    @PutMapping("/modify")       // JSON Data -> 객체
    public ResponseEntity modify(@RequestBody BoardDTO dto) {
        service.modify(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT); // 204 성공코드 반환 + Data X
    }

    // localhost:8080/board/remove?no=1
    // 특정 게시물을 삭제하는 메소드
    @DeleteMapping("/remove")
    public ResponseEntity remove(@RequestParam(name = "no") int no) {
        service.remove(no);
        return new ResponseEntity(HttpStatus.NO_CONTENT); // 204 성공코드 반환
    }
}
