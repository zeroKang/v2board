package org.zerock.board1.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board1.dto.BoardDTO;
import org.zerock.board1.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@AllArgsConstructor
public class BoardController {

    private BoardService service;

    @GetMapping("/list")
    public void list(@PageableDefault(page = 0, size=10, sort = "bno", direction = Sort.Direction.DESC)Pageable pageable, Model model){

        log.info(pageable);

        model.addAttribute("data", service.getPage(pageable));

    }

    @GetMapping("/register")
    public void register(){
        log.info("GET register.............");
    }

    @PostMapping("/register")
    public String register(BoardDTO dto, RedirectAttributes redirectAttributes){

        log.info("Post register..." + dto);

        service.register(dto);

        redirectAttributes.addFlashAttribute("result","success");

        return "redirect:/board/list";

    }

    @GetMapping("/read")
    public void read(Long bno, Pageable pageable, Model model){
        log.info("Get read.." + bno);

        model.addAttribute("pageable", pageable);
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){

        log.info("Post remove...." + bno);

        service.remove(bno);

        redirectAttributes.addFlashAttribute("result","success");

        return "redirect:/board/list";
    }



    @GetMapping("/modify")
    public void modify(Long bno, Pageable pageable,Model model){
        log.info("Get modify.." + bno);

        model.addAttribute("pageable", pageable);
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, RedirectAttributes redirectAttributes){

        log.info("Post modify...." + dto);

        service.modify(dto);

        redirectAttributes.addFlashAttribute("result","success");

        return "redirect:/board/list";

    }


}















