package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.enums.InquiryStatusEnum;
import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.mapper.InquiryMapper;
import com.lighting.metal.mapper.ProductMapper;
import com.lighting.metal.mapper.SupplierMapper;
import com.lighting.metal.model.dto.CartToInquiryDTO;
import com.lighting.metal.model.dto.InquiryCreateDTO;
import com.lighting.metal.model.entity.*;
import com.lighting.metal.service.CartService;
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

     // 注入所需Service
    @Resource
    private CartService cartService;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private SupplierMapper supplierMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createInquiryFromCart(CartToInquiryDTO dto) {
        // 1. 查询购物车
        List<Cart> cartList = cartService.getCartList(dto.getUserId());
        if (cartList.isEmpty()) {
            throw new BusinessException("购物车为空，无法创建询价单");
        }

        // 2. 创建询价主单（复用原有表）
        String inquiryNo = "INQ" + System.currentTimeMillis();
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryNo(inquiryNo);
        inquiry.setUserId(dto.getUserId());
        inquiry.setCountryCode(dto.getCountryCode());
        inquiry.setStatus(InquiryStatusEnum.PENDING.getCode());
        inquiry.setRemark(dto.getRemark());
        this.save(inquiry);

        // 3. 遍历购物车 → 查询商品 → 生成询价明细快照
        List<InquiryItem> itemList = cartList.stream().map(cart -> {
            // 实时查询商品+商户，生成永久快照
            Product product = productMapper.selectById(cart.getProductId());
            Supplier supplier = supplierMapper.selectOne(
                    new LambdaQueryWrapper<Supplier>().eq(Supplier::getSupplierCode, product.getSupplierCode())
            );

            InquiryItem item = new InquiryItem();
            item.setInquiryId(inquiry.getId());
            item.setProductId(product.getId());
            item.setSupplierCode(supplier.getSupplierCode());

            // ===================== 快照固化（仅询价单保留） =====================
            item.setProductNameCn(product.getProductNameCn());
            item.setProductNameEn(product.getProductNameEn());
            item.setCustomsCode(product.getCustomsCode());
            item.setCategoryCode(product.getCategoryCode());
            item.setMaterialCode(product.getMaterialCode());
            item.setSupplierName(supplier.getSupplierName());

            item.setQuantity(cart.getQuantity());
            return item;
        }).toList();

        // 4. 批量保存明细
        inquiryItemService.saveBatch(itemList);

        // 5. 清空购物车
        cartService.clearCartByUser(dto.getUserId());

        return inquiry.getId();
    }
}