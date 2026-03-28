<template>
  <div class="inquiry-form" style="padding: 20px;">
    <el-form :model="mainForm" label-width="100px" ref="mainFormRef">
      <!-- 主表字段 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="询价单号" prop="inquiryNo">
            <el-input v-model="mainForm.inquiryNo" placeholder="请输入"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="供应商ID" prop="supplierId">
            <el-input v-model="mainForm.supplierId" type="number" placeholder="请输入"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="询价日期" prop="inquiryDate">
            <el-date-picker
                v-model="mainForm.inquiryDate"
                type="date"
                placeholder="请选择"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态" prop="status">
            <el-select v-model="mainForm.status" placeholder="请选择">
              <el-option label="草稿" value="0"></el-option>
              <el-option label="已提交" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 明细表子表格 -->
      <el-form-item label="询价明细">
        <el-table :data="itemList" border @row-click="handleRowClick">
          <el-table-column prop="productId" label="产品ID">
            <template #default="scope">
              <el-input v-model="scope.row.productId" type="number" size="small"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="productName" label="产品名称">
            <template #default="scope">
              <el-input v-model="scope.row.productName" size="small"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="quantity" label="数量">
            <template #default="scope">
              <el-input v-model="scope.row.quantity" type="number" size="small" @change="calcItemAmount(scope.row)"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="unitPrice" label="单价">
            <template #default="scope">
              <el-input v-model="scope.row.unitPrice" type="number" size="small" @change="calcItemAmount(scope.row)"></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="amount" label="行金额">
            <template #default="scope">
              <el-input v-model="scope.row.amount" size="small" disabled></el-input>
            </template>
          </el-table-column>
          <el-table-column prop="remark" label="备注">
            <template #default="scope">
              <el-input v-model="scope.row.remark" size="small"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="80">
            <template #default="scope">
              <el-button type="danger" size="small" @click="deleteItem(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="primary" size="small" @click="addItem" class="mt-2">新增明细</el-button>
      </el-form-item>

      <!-- 提交按钮 -->
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage, ElForm } from 'element-plus'
import { addInquiry, getInquiryById, editInquiry } from '@/api/inquiry/inquiry'
import { Inquiry, InquiryItem, InquirySubmitParams } from '@/types/inquiry'

const router = useRouter()
const route = useRoute()
const mainFormRef = ref<ElForm>()

// 主表表单
const mainForm = ref<Inquiry>({
  inquiryNo: '',
  supplierId: 0,
  inquiryDate: '',
  totalAmount: 0,
  status: 0,
  creator: 'admin' // 示例：固定创建人，实际可从登录态获取
})

// 明细表列表
const itemList = ref<InquiryItem[]>([])

// 路由参数（编辑时的ID）
const inquiryId = route.params.id as string

// 新增明细行
const addItem = () => {
  itemList.value.push({
    inquiryId: inquiryId ? Number(inquiryId) : 0,
    productId: 0,
    productName: '',
    quantity: 1,
    unitPrice: 0,
    amount: 0,
    remark: ''
  })
}

// 删除明细行
const deleteItem = (index: number) => {
  itemList.value.splice(index, 1)
  calcTotalAmount()
}

// 计算单行金额
const calcItemAmount = (row: InquiryItem) => {
  row.amount = row.quantity * row.unitPrice
  calcTotalAmount()
}

// 计算总金额
const calcTotalAmount = () => {
  mainForm.value.totalAmount = itemList.value.reduce((sum, item) => sum + item.amount, 0)
}

// 回显数据（编辑场景）
const getDetail = async () => {
  if (!inquiryId) return
  const res = await getInquiryById(Number(inquiryId))
  mainForm.value = res.data.main
  itemList.value = res.data.items
}

// 提交表单
const submitForm = async () => {
  // 表单校验
  if (!mainFormRef.value) return
  await mainFormRef.value.validate()

  // 构造提交参数
  const params: InquirySubmitParams = {
    main: mainForm.value,
    items: itemList.value
  }

  try {
    if (inquiryId) {
      // 编辑
      await editInquiry(params)
      ElMessage.success('编辑成功')
    } else {
      // 新增
      await addInquiry(params)
      ElMessage.success('新增成功')
    }
    router.push('/inquiry/list')
  } catch (error) {
    ElMessage.error('提交失败')
  }
}

// 返回列表页
const goBack = () => {
  router.push('/inquiry/list')
}

// 初始化
onMounted(() => {
  getDetail()
})
</script>

<style scoped lang="scss">
.mt-2 {
  margin-top: 8px;
}
</style>