package com.example.demo.controller;

import com.example.demo.domain.Notice;
import com.example.demo.exception.NoSubstanceException;
import com.example.demo.repository.*;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NoticeController {
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private UserRepository userRepository;
    private NoticeService noticeService;
    private CertifiedService certifiedService;

    public NoticeController() {
        this.noticeService = new NoticeService();
        this.certifiedService = new CertifiedService();
    }
    @PostMapping(value = "/notice")
    public ResponseEntity<List<Notice>> createNoticeRequest(@RequestBody Notice notice, @RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        if(notice==null){
            throw new NoSubstanceException("no Substance");
        }
        return ResponseEntity.ok(noticeService.createNotice(notice,noticeRepository));
    }
    @PutMapping(value = "/notice/{noticeId}")
    public ResponseEntity<List<Notice>> updateNoticeRequest(@RequestBody Notice notice, @RequestHeader Integer loginUserId, @PathVariable Integer noticeId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        return ResponseEntity.ok(noticeService.updateNotice(notice,noticeRepository,noticeId));
    }
    @GetMapping(value = "/notice")
    public ResponseEntity<List<Notice>> readAllNoticeRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(noticeService.readAllNotice(noticeRepository));
    }
    @GetMapping(value = "/notice/{noticeId}")
    public ResponseEntity<Optional<Notice>> readNoticeRequest(@RequestHeader Integer loginUserId, @PathVariable Integer noticeId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(noticeService.readNotice(noticeRepository,noticeId));
    }
    @DeleteMapping(value = "/notice/{noticeId}")
    public ResponseEntity<List<Notice>> deleteNoticeRequest(@RequestHeader Integer loginUserId, @PathVariable Integer noticeId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        return ResponseEntity.ok(noticeService.deleteNotice(noticeId,noticeRepository));
    }
}
