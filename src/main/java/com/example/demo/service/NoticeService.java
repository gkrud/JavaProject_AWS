package com.example.demo.service;

import com.example.demo.domain.Notice;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.NoticeRepository;

import java.util.List;
import java.util.Optional;

public class NoticeService {
    public List<Notice> createNotice(Notice notice, NoticeRepository noticeRepository) {
        noticeRepository.save(notice);
        return noticeRepository.findAll();
    }

    public List<Notice> updateNotice(Notice notice, NoticeRepository noticeRepository,Integer noticeId) {
        Optional<Notice> newNotice = noticeRepository.findById(noticeId);
        newNotice.get().setNotice(notice.getNoticeContent(),notice.getStartDate(),notice.getEndDate(),notice.getNoticeTitle());
        noticeRepository.save(newNotice.get());
        return noticeRepository.findAll();
    }

    public List<Notice> readAllNotice(NoticeRepository noticeRepository) {
        return noticeRepository.findAll();
    }

    public Optional<Notice> readNotice(NoticeRepository noticeRepository, Integer noticeId) {
        return noticeRepository.findById(noticeId);
    }

    public List<Notice> deleteNotice(Integer noticeId, NoticeRepository noticeRepository) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if(notice.isEmpty()){
            throw new NotFoundException("not found");
        }
        noticeRepository.delete(noticeRepository.findById(noticeId).get());
        return noticeRepository.findAll();
    }
}

