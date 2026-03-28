// 询价单主表类型
export interface Inquiry {
    id?: number
    inquiryNo: string
    supplierId: number
    inquiryDate: string // 前端用字符串格式（如YYYY-MM-DD）
    totalAmount: number
    status: number
    creator: string
    createTime?: string
    updateTime?: string
}

// 询价单明细表类型
export interface InquiryItem {
    id?: number
    inquiryId: number
    productId: number
    productName: string
    quantity: number
    unitPrice: number
    amount: number
    remark?: string
}

// 新增/编辑询价单提交参数（主表+明细）
export interface InquirySubmitParams {
    main: Inquiry
    items: InquiryItem[]
}