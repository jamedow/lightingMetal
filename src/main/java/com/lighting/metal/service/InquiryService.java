package com.lighting.metal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lighting.metal.model.dto.CartToInquiryDTO;
import com.lighting.metal.model.dto.InquiryCreateDTO;
import com.lighting.metal.model.entity.Inquiry;

public interface InquiryService extends IService<Inquiry> {
    Long createInquiry(InquiryCreateDTO dto);
    // 购物车一键生成询价单
    Long createInquiryFromCart(CartToInquiryDTO dto);
}