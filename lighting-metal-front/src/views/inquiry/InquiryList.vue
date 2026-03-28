<template>
  <div class="inquiry-list">
    <!-- 查询表单 -->
    <el-form :inline="true" :model="queryForm" class="mb-4">
      <el-form-item label="询价单号">
        <el-input v-model="queryForm.inquiryNo" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="供应商ID">
        <el-input v-model="queryForm.supplierId" type="number" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryForm.status" placeholder="请选择">
          <el-option label="草稿" value="0"></el-option>
          <el-option label="已提交" value="1"></el-option>
          <el-option label="已报价" value="2"></el-option>
          <el-option label="已关闭" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList">查询</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮 -->
    <el-button type="primary" @click="toAdd">新增询价单</el-button>

    <!-- 列表表格 -->
    <el-table :data="tableData" border stripe>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="inquiryNo" label="询价单号"></el-table-column>
      <el-table-column prop="supplierId" label="供应商ID"></el-table-column>
      <el-table-column prop="inquiryDate" label="询价日期"></el-table-column>
      <el-table-column prop="totalAmount" label="总金额"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <el-tag :type="getStatusTagType(scope.row.status)">
            {{ getStatusText(scope.row.status) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="creator" label="创建人"></el-table-column>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button type="primary" size="small" @click="toEdit(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :total="total"
        layout="prev, pager, next, jumper, ->, total"
        @size-change="getList"
        @current-change="getList"
        class="mt-4"
    >
    </el-pagination>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { getInquiryList, deleteInquiry } from '@/api/inquiry/inquiry'
import { Inquiry } from '@/types/inquiry'

const router = useRouter()

// 查询表单
const queryForm = ref({
  inquiryNo: '',
  supplierId: '',
  status: ''
})

// 分页参数
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)

// 表格数据
const tableData = ref<Inquiry[]>([])

// 获取列表数据
const getList = async () => {
  const params = {
    ...queryForm.value,
    pageNum: pageNum.value,
    pageSize: pageSize.value
  }
  const res = await getInquiryList(params)
  tableData.value = res.data.list
  total.value = res.data.total
}

// 重置查询
const resetQuery = () => {
  queryForm.value = {
    inquiryNo: '',
    supplierId: '',
    status: ''
  }
  getList()
}

// 跳转到新增页
const toAdd = () => {
  router.push({ path: '/inquiry/form' })
}

// 跳转到编辑页
const toEdit = (id: number) => {
  router.push({ path: `/inquiry/form/${id}` })
}

// 删除询价单
const handleDelete = async (id: number) => {
  const confirm = await ElMessageBox.confirm('确定要删除该询价单吗？', '提示', {
    type: 'warning'
  })
  if (confirm) {
    await deleteInquiry(id)
    ElMessage.success('删除成功')
    getList()
  }
}

// 状态文本转换
const getStatusText = (status: number) => {
  const map = {
    0: '草稿',
    1: '已提交',
    2: '已报价',
    3: '已关闭'
  }
  return map[status] || '未知'
}

// 状态标签类型
const getStatusTagType = (status: number) => {
  const map = {
    0: 'info',
    1: 'primary',
    2: 'success',
    3: 'danger'
  }
  return map[status] || 'info'
}

// 初始化加载列表
onMounted(() => {
  getList()
})
</script>

<style scoped lang="scss">
.inquiry-list {
  padding: 20px;
  .mb-4 {
    margin-bottom: 16px;
  }
  .mt-4 {
    margin-top: 16px;
    text-align: right;
  }
}
</style>