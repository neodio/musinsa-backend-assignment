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

    @GetMapping("/pages/brand")
    public ModelAndView brand() {
        return new ModelAndView("/pages/brand")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "brand")
                ;
    }

    @GetMapping("/pages/brandAdd")
    public ModelAndView brandAdd() {
        return new ModelAndView("/pages/brandAdd")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "brand")
                ;
    }

    @GetMapping("/pages/brandEdit/{brandId}")
    public ModelAndView brandEdit(@PathVariable Long brandId) {
        return new ModelAndView("/pages/brandEdit")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "brand")
                .addObject("brandId", brandId)
                ;
    }

    @GetMapping("/pages/product")
    public ModelAndView product() {
        return new ModelAndView("/pages/product")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "product")
                ;
    }

    @GetMapping("/pages/productAdd")
    public ModelAndView productAdd() {
        return new ModelAndView("/pages/productAdd")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "product")
                ;
    }

    @GetMapping("/pages/productEdit/{productId}")
    public ModelAndView productEdit(@PathVariable Long productId) {
        return new ModelAndView("/pages/productEdit")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "product")
                .addObject("productId", productId)
                ;
    }

    @GetMapping("/pages/categoryLowest")
    public ModelAndView categoryLowest() {
        return new ModelAndView("/pages/categoryLowest")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "product")
                ;
    }

    @GetMapping("/pages/brandLowest")
    public ModelAndView brandLowest() {
        return new ModelAndView("/pages/brandLowest")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "product")
                ;
    }

    @GetMapping("/pages/minMaxProduct")
    public ModelAndView minMaxProduct() {
        return new ModelAndView("/pages/minMaxProduct")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "product")
                ;
    }
}
