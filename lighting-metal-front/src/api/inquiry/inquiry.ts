import request from '@/api/index'
import { Inquiry, InquirySubmitParams } from '@/types/inquiry'

// 获取询价单列表
export function getInquiryList(params: any) {
    return request({
        url: '/inquiry/list',
        method: 'get',
        params
    })
}

// 新增询价单（主表+明细）
export function addInquiry(data: InquirySubmitParams) {
    return request({
        url: '/inquiry/add',
        method: 'post',
        data
    })
}

// 根据ID查询询价单（含明细）
export function getInquiryById(id: number) {
    return request({
        url: `/inquiry/${id}`,
        method: 'get'
    })
}

// 编辑询价单
export function editInquiry(data: InquirySubmitParams) {
    return request({
        url: '/inquiry/edit',
        method: 'put',
        data
    })
}

// 删除询价单
export function deleteInquiry(id: number) {
    return request({
        url: `/inquiry/${id}`,
        method: 'delete'
    })
}