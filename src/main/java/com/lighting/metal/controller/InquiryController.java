package com.lighting.metal.controller;

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

    /**
     * 海外客户创建询价单（B2B核心）
     */
    @PostMapping("/create")
    public Result<Long> createInquiry(@Valid @RequestBody InquiryCreateDTO dto) {
        return Result.success(inquiryService.createInquiry(dto));
    }
}