package com.archivsoft.controller;

import com.archivsoft.dto.MainDto;
import com.archivsoft.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    private MainService mainService;

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model){
        MainDto mainDto = mainService.getPost(no);
        model.addAttribute("mainDto", mainDto);
        return "/main/detail.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model){
        MainDto mainDto = mainService.getPost(no);
        model.addAttribute("mainDto", mainDto);
        return "/main/update.html";
    }

//    @PutMapping("/post/edit/{no}")
    @RequestMapping(value = "/post/edit/{no}", method = RequestMethod.POST)
    public String update(MainDto maindto){
        mainService.savePost(maindto);
        return "redirect:/";
    }

//    @DeleteMapping("/post/{no}")`
    @RequestMapping(value="/post/{no}" , method =RequestMethod.POST)
    public String delete(@PathVariable("no") Long no){
        mainService.deletePost(no);
        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model){
        List<MainDto> MainList = mainService.getMianList();

        model.addAttribute("mainList", MainList);
        return "main/list.html";
    }

    @GetMapping("/post")
    public String write(){
        return "main/write.html";
    }

    @PostMapping("/post")
    public String write(MainDto mainDto){
        mainService.savePost(mainDto);

        return "redirect:/";
    }
}
