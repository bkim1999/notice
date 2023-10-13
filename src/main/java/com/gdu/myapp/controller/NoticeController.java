package com.gdu.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.myapp.dto.NoticeDto;
import com.gdu.myapp.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/notice")
@RequiredArgsConstructor
@Slf4j
@Controller
public class NoticeController {
  
  private final NoticeService noticeService;
  
  @RequestMapping(value="/list.do", method=RequestMethod.GET)
  public String list(Model model) {
    List<NoticeDto> noticeList = noticeService.getNoticeList();
    model.addAttribute("noticeList", noticeList);
    log.info("{}", noticeList);
    return "notice/list";
  }
  
  @RequestMapping(value="/write.do", method=RequestMethod.GET)
  public String write() {
    return "notice/write";
  }
  
  @RequestMapping(value="/add.do", method=RequestMethod.POST)
  public String add(NoticeDto noticeDto, RedirectAttributes redirectAttributes) {

    System.out.println(noticeDto);
    int addResult = noticeService.addNotice(noticeDto);
    redirectAttributes.addFlashAttribute("addResult", addResult);
    log.info("{}", addResult);
    return "redirect:/notice/list.do";
  }
  
  @RequestMapping(value="/detail.do", method=RequestMethod.GET)
  public String detail(@RequestParam("noticeNo") int noticeNo, Model model) {
    NoticeDto noticeDto = noticeService.getNoticeByNo(noticeNo);
    model.addAttribute("notice", noticeDto);
    log.info("{}", noticeDto);
    return "notice/detail";
  }
  
  @RequestMapping(value="/modify.do", method=RequestMethod.POST)
  public String modify(NoticeDto noticeDto, RedirectAttributes redirectAttributes) {
    int modifyResult = noticeService.modifyNotice(noticeDto);
    redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
    log.info("{}", modifyResult);
    return "redirect:/notice/detail.do?noticeNo=" + noticeDto.getNoticeNo();
  }
  
  @RequestMapping(value="/delete.do", method=RequestMethod.POST)
  public String delete(@RequestParam("noticeNo") int noticeNo, RedirectAttributes redirectAttributes) {
    int deleteResult = noticeService.deleteNotice(noticeNo);
    redirectAttributes.addFlashAttribute("deleteResult", deleteResult);
    log.info("{}", deleteResult);
    return "redirect:/notice/list.do";
  }
  
}
