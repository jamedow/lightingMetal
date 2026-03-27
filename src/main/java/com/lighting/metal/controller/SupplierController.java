package com.lighting.metal.controller;

import com.lighting.metal.model.entity.Supplier;
import com.lighting.metal.model.vo.Result;
import com.lighting.metal.service.SupplierService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // 查询所有厂商
    @GetMapping("/list")
    public Result<List<Supplier>> list() {
        return Result.success(supplierService.list());
    }

    // 新增厂商
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody Supplier supplier) {
        return Result.success(supplierService.save(supplier));
    }
}