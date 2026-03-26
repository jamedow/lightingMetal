package com.lighting.metal.model.dto.base;

import lombok.Data;

/**
 * 全局通用分页DTO (所有列表分页共用，无业务代码)
 */
@Data
public class BasePageDTO {
    /**
     * 当前页码，默认1
     */
    private Long current = 1L;

    /**
     * 每页条数，默认10
     */
    private Long size = 10L;
}
