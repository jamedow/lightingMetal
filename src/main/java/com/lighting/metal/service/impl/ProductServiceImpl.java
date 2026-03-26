package com.lighting.metal.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.exception.BusinessException;
import com.lighting.metal.mapper.ProductMapper;
import com.lighting.metal.model.dto.ProductAddDTO;
import com.lighting.metal.model.entity.Product;
import com.lighting.metal.model.vo.ProductVO;
import com.lighting.metal.service.ProductService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品业务实现
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    // 五金商品固定前缀（无需表字段）
    private static final String HW_PREFIX = "HW";

    /**
     * 新增跨境五金商品
     */
    @Override
    public boolean addProduct(ProductAddDTO dto) {
        // 1. 生成商品编号（HW + 雪花ID）
        String productNo = generateProductNo();

        // 2. 转换DTO到实体
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setProductNo(productNo);
        product.setStatus(1); // 默认上架

        // 3. 保存商品
        return save(product);
    }

    /**
     * 生成商品编号（独立方法，便于维护和扩展）
     * @return 格式化的商品编号
     */
    private String generateProductNo() {
        // 步骤1：13位毫秒级时间戳（核心唯一标识，每毫秒不同）
        long timestamp = System.currentTimeMillis();
        String timestampStr = String.valueOf(timestamp);

        // 步骤2：3位随机数（补零，避免同一毫秒内重复）
        int randomNum = RandomUtils.nextInt(1, 999);
        String randomStr = StringUtils.leftPad(String.valueOf(randomNum), 3, "0");

        // 步骤3：拼接最终编号（前缀+时间戳+随机数）
        return StringUtils.join(HW_PREFIX, timestampStr, randomStr);
    }

    /**
     * 查询上架商品列表
     */
    @Override
    public List<ProductVO> listOnSaleProducts() {
        // 查询上架商品
        List<Product> productList = lambdaQuery()
                .eq(Product::getStatus, 1)
                .list();

        // 转换为VO
        return productList.stream().map(product -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(product, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 根据ID查询商品
     */
    @Override
    public ProductVO getProductById(Long id) {
        Product product = getById(id);
        if (product == null) {
            throw new BusinessException("商品不存在");
        }
        ProductVO vo = new ProductVO();
        BeanUtils.copyProperties(product, vo);
        return vo;
    }
}