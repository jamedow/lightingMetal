package com.lighting.metal.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lighting.metal.mapper.SupplierMapper;
import com.lighting.metal.model.entity.Supplier;
import com.lighting.metal.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {}