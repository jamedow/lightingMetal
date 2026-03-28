package com.lighting.metal.controller;

import com.lighting.metal.model.dto.CartToInquiryDTO;
import com.lighting.metal.model.dto.InquiryCreateDTO;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.service.InquiryService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {

    @Resource
    private InquiryService inquiryService;

    @PostMapping("/create/from-cart")
    public Result<Long> createFromCart(@Valid @RequestBody CartToInquiryDTO dto) {
        return Result.success(inquiryService.createInquiryFromCart(dto));
    }
}