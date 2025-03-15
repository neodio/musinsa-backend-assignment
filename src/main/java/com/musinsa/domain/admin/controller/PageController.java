package com.musinsa.domain.admin.controller;

import com.musinsa.domain.admin.service.AdminMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final AdminMenuService adminMenuService;

    @GetMapping(path = {"/"})
    public ModelAndView index() {
        return new ModelAndView("/index")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main")
                ;
    }

    @GetMapping("/pages/category")
    public ModelAndView category() {
        return new ModelAndView("/pages/category")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "category")
                ;
    }

    @GetMapping("/pages/categoryAdd")
    public ModelAndView categoryAdd() {
        return new ModelAndView("/pages/categoryAdd")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "category")
                ;
    }

    @GetMapping("/pages/categoryEdit/{categoryId}")
    public ModelAndView categoryEdit(@PathVariable Long categoryId) {
        return new ModelAndView("/pages/categoryEdit")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "category")
                .addObject("categoryId", categoryId)
                ;
    }

    @GetMapping("/pages/order")
    public ModelAndView order() {
        return new ModelAndView("/pages/order")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "order")
                ;
    }

    @GetMapping("/pages/item")
    public ModelAndView item() {
        return new ModelAndView("/pages/item")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "item")
                ;
    }
}
