package com.lighting.metal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.enums.ProductTypeEnum;
import com.lighting.metal.enums.ProductMaterialEnum;
import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.mapper.ProductMapper;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.dto.ProductQueryDTO;
import com.lighting.metal.model.dto.ProductUpdateDTO;
import com.lighting.metal.model.entity.Product;
import com.lighting.metal.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private static final String PRODUCT_PREFIX = "HW";

    // 新增商品
    @Override
    public boolean addProduct(ProductAddDTO dto) {
        // ========== 枚举使用：校验前端传入的编码是否合法 ==========
        if (ProductTypeEnum.getByCode(dto.getCategoryCode()) == null) {
            throw new BusinessException("非法的商品品类编码");
        }
        if (ProductMaterialEnum.getByCode(dto.getMaterialCode()) == null) {
            throw new BusinessException("非法的商品材质编码");
        }

        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setProductNo(generateProductNo(dto));
        product.setStatus(1);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        return save(product);
    }

    // 修改商品
    @Override
    public boolean updateProduct(ProductUpdateDTO dto) {
        Product product = getById(dto.getId());
        if (product == null) {
            throw new BusinessException("商品不存在");
        }
        BeanUtils.copyProperties(dto, product);
        product.setUpdateTime(LocalDateTime.now());
        return updateById(product);
    }

    // 分页查询
    @Override
    public IPage<Product> pageList(ProductQueryDTO dto) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        // 商品名称模糊查询
        wrapper.like(StringUtils.isNotBlank(dto.getProductNameCn()), Product::getProductNameCn, dto.getProductNameCn());
        // 按分类ID筛选（核心：客户前端选分类自动过滤）
        wrapper.eq(dto.getCategoryId() != null, Product::getCategoryId, dto.getCategoryId());
        // 状态筛选
        wrapper.eq(dto.getStatus() != null, Product::getStatus, dto.getStatus());
        // 按创建时间倒序
        wrapper.orderByDesc(Product::getCreateTime);

        Page<Product> page = new Page<>(dto.getCurrent(), dto.getSize());
        return page(page, wrapper);
    }

    // ===================== 全新商品编号生成 =====================
    /**
     * 规则：LW + 品类(2) + 材质(2) + 厂商(3) + 规格(3) + 序列号(4)
     */
    private String generateProductNo(ProductAddDTO dto) {
        // 1. 固定前缀：雷霆五金
        final String PREFIX = "LW";

        // 2. 品类编码（2位）
        String categoryCode = dto.getCategoryCode();

        // 3. 材质编码（2位）
        String materialCode = dto.getMaterialCode();

        // 4. 厂商编码（3位，必传）
        String supplierCode = dto.getSupplierCode();

        // 5. 规格编码（取海关编码后3位，固定3位）
        String customsCode = dto.getCustomsCode();
        String specCode = customsCode.length() >= 3
                ? customsCode.substring(customsCode.length() - 3)
                : StringUtils.leftPad(customsCode, 3, "0");

        // 6. 获取4位自增序列号（核心：同厂商+品类+材质 自增）
        int nextSeq = getNextSequence(supplierCode, categoryCode, materialCode);
        String sequence = StringUtils.leftPad(String.valueOf(nextSeq), 4, "0");

        // 拼接最终18位编号
        return PREFIX + categoryCode + materialCode + supplierCode + specCode + sequence;
    }

    /**
     * 【完整实现】查询同厂商、同品类、同材质下的最大序列号 +1
     * 无数据时返回 1
     */
    private int getNextSequence(String supplierCode, String categoryCode, String materialCode) {
        // 1. 构造查询条件：匹配 厂商+品类+材质
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getSupplierCode, supplierCode)
                .eq(Product::getCategoryCode, categoryCode)
                .eq(Product::getMaterialCode, materialCode)
                // 按编号倒序，取最大的那一条
                .orderByDesc(Product::getProductNo)
                .last("LIMIT 1");

        // 2. 查询数据库最新商品
        Product lastProduct = this.getOne(queryWrapper);

        // 3. 无数据 → 返回1（默认起始值）
        if (lastProduct == null || StringUtils.isBlank(lastProduct.getProductNo())) {
            return 1;
        }

        // 4. 有数据 → 解析最后4位序列号 +1
        String productNo = lastProduct.getProductNo();
        // 编号总长度18位，最后4位是序列号
        String seqStr = productNo.substring(productNo.length() - 4);
        // 转数字 +1
        return Integer.parseInt(seqStr) + 1;
    }
}