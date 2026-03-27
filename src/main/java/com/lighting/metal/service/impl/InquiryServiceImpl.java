package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.enums.InquiryStatusEnum;
import com.lighting.metal.mapper.InquiryMapper;
import com.lighting.metal.mapper.ProductMapper;
import com.lighting.metal.mapper.SupplierMapper;
import com.lighting.metal.model.dto.InquiryCreateDTO;
import com.lighting.metal.model.entity.Inquiry;
import com.lighting.metal.model.entity.InquiryItem;
import com.lighting.metal.model.entity.Product;
import com.lighting.metal.model.entity.Supplier;
import com.lighting.metal.service.InquiryService;
import com.lighting.metal.service.InquiryItemService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class InquiryServiceImpl extends ServiceImpl<InquiryMapper, Inquiry> implements InquiryService {

    // 注入批量插入服务
    @Resource
    private InquiryItemService inquiryItemService;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createInquiry(InquiryCreateDTO dto) {
        // 1. 生成询价单号
        String inquiryNo = "INQ" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

        // 2. 新建主单
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryNo(inquiryNo);
        inquiry.setUserId(dto.getUserId());
        inquiry.setCountryCode(dto.getCountryCode());
        inquiry.setStatus(InquiryStatusEnum.PENDING.getCode());
        inquiry.setRemark(dto.getRemark());
        this.save(inquiry);

        // 3. 生成明细（带快照）
        List<InquiryItem> itemList = new ArrayList<>();
        for (InquiryCreateDTO.ItemDTO item : dto.getItems()) {
            // 查询商品 + 厂商生成快照
            Product product = productMapper.selectById(item.getProductId());
            Supplier supplier = supplierMapper.selectOne(new LambdaQueryWrapper<Supplier>()
                    .eq(Supplier::getSupplierCode, product.getSupplierCode()));

            InquiryItem inquiryItem = new InquiryItem();
            inquiryItem.setInquiryId(inquiry.getId());
            inquiryItem.setProductId(product.getId());
            inquiryItem.setSupplierCode(supplier.getSupplierCode());

            // 商品快照
            inquiryItem.setProductNameCn(product.getProductNameCn());
            inquiryItem.setProductNameEn(product.getProductNameEn());
            inquiryItem.setCustomsCode(product.getCustomsCode());
            inquiryItem.setCategoryCode(product.getCategoryCode());
            inquiryItem.setMaterialCode(product.getMaterialCode());

            // 厂商快照
            inquiryItem.setSupplierName(supplier.getSupplierName());

            // 业务字段
            inquiryItem.setQuantity(item.getQuantity());
            inquiryItem.setExpectUnitPrice(item.getExpectUnitPrice());
            inquiryItem.setRemark(item.getRemark());

            itemList.add(inquiryItem);
        }

        // ✅ 修复：MP官方批量插入方法（无报错）
        inquiryItemService.saveBatch(itemList);
        return inquiry.getId();
    }
}