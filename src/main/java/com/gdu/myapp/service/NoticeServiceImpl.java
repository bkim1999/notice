package com.gdu.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.myapp.dao.NoticeMapper;
import com.gdu.myapp.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
  
  private final NoticeMapper noticeMapper;
  
  @Override
  public int addNotice(NoticeDto noticeDto) {
    return noticeMapper.insert(noticeDto);
  }

  @Override
  public int modifyNotice(NoticeDto noticeDto) {
    return noticeMapper.update(noticeDto);
  }

  @Override
  public int deleteNotice(int noticeNo) {
    return noticeMapper.delete(noticeNo);
  }

  @Transactional(readOnly=true)
  @Override
  public List<NoticeDto> getNoticeList() {
    return noticeMapper.selectList();
  }

  @Transactional(readOnly=true)
  @Override
  public NoticeDto getNoticeByNo(int noticeNo) {
    return noticeMapper.selectNoticeByNo(noticeNo);
  }
  
}
