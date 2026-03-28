package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.mapper.InquiryItemMapper;
import com.lighting.metal.model.entity.InquiryItem;
import com.lighting.metal.service.InquiryItemService;
import org.springframework.stereotype.Service;

@Service
public class InquiryItemServiceImpl extends ServiceImpl<InquiryItemMapper, InquiryItem> implements InquiryItemService {
}