<template>
  <div class="category-section">
    <h2 class="title">商品分类</h2>
    <div class="category-list">
      <!-- 五金零件 → CSS螺丝图标 -->
      <div class="category-item category-part">
        <div class="category-content">
          <div class="css-icon screw-icon"></div>
          <h3>{{ categoryList[0].name }}</h3>
          <p>{{ categoryList[0].desc }}</p>
        </div>
      </div>
      <!-- 五金工具 → CSS扳手图标 -->
      <div class="category-item category-tool">
        <div class="category-content">
          <div class="css-icon wrench-icon"></div>
          <h3>{{ categoryList[1].name }}</h3>
          <p>{{ categoryList[1].desc }}</p>
        </div>
      </div>
      <!-- 紧固件 → CSS螺栓图标 -->
      <div class="category-item category-fastener">
        <div class="category-content">
          <div class="css-icon bolt-icon"></div>
          <h3>{{ categoryList[2].name }}</h3>
          <p>{{ categoryList[2].desc }}</p>
        </div>
      </div>
      <!-- 五金配件 → CSS齿轮图标 -->
      <div class="category-item category-accessory">
        <div class="category-content">
          <div class="css-icon gear-icon"></div>
          <h3>{{ categoryList[3].name }}</h3>
          <p>{{ categoryList[3].desc }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useHomeData } from '@/composables/useHomeData'
const { categoryList } = useHomeData()
</script>

<style scoped>
/* 基础布局 */
.category-section {
  padding: 60px 10%;
  background: #f5f7fa;
}
.category-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: space-between;
}
.category-item {
  width: 22%;
  height: 180px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  cursor: pointer;
}
.category-item:hover {
  transform: translateY(-6px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
}
.category-content {
  text-align: center;
}
.category-content h3 {
  font-size: 18px;
  margin: 10px 0 4px;
  font-weight: 600;
}
.category-content p {
  font-size: 13px;
  opacity: 0.9;
}

/* ===================== 纯CSS手绘五金图标 ===================== */
.css-icon {
  margin: 0 auto 8px;
  position: relative;
}

/* 1. 螺丝图标 → 五金零件 */
.screw-icon {
  width: 36px;
  height: 36px;
}
.screw-icon::before {
  content: "";
  position: absolute;
  width: 14px;
  height: 24px;
  background: #fff;
  border-radius: 2px;
  left: 11px;
  top: 6px;
}
.screw-icon::after {
  content: "";
  position: absolute;
  width: 20px;
  height: 8px;
  background: #fff;
  border-radius: 4px;
  left: 8px;
  top: 0;
}

/* 2. 扳手图标 → 五金工具 */
.wrench-icon {
  width: 36px;
  height: 36px;
}
.wrench-icon::before {
  content: "";
  position: absolute;
  width: 32px;
  height: 8px;
  background: #fff;
  border-radius: 4px;
  top: 14px;
  transform: rotate(-45deg);
}
.wrench-icon::after {
  content: "";
  position: absolute;
  width: 16px;
  height: 16px;
  border: 6px solid #fff;
  border-radius: 50%;
  top: 2px;
  left: 2px;
}

/* 3. 螺栓图标 → 紧固件 */
.bolt-icon {
  width: 36px;
  height: 36px;
}
.bolt-icon::before {
  content: "";
  position: absolute;
  width: 20px;
  height: 20px;
  background: #fff;
  clip-path: polygon(50% 0%, 100% 25%, 100% 75%, 50% 100%, 0% 75%, 0% 25%);
  top: 0;
  left: 8px;
}
.bolt-icon::after {
  content: "";
  position: absolute;
  width: 10px;
  height: 16px;
  background: #fff;
  left: 13px;
  top: 20px;
}

/* 4. 齿轮图标 → 五金配件 */
.gear-icon {
  width: 36px;
  height: 36px;
  background: #fff;
  border-radius: 50%;
  position: relative;
}
.gear-icon::before {
  content: "";
  position: absolute;
  width: 20px;
  height: 20px;
  background: #2d8cf0;
  border-radius: 50%;
  top: 8px;
  left: 8px;
}

/* ===================== 分类渐变背景（金属工业风） ===================== */
.category-part {
  background: linear-gradient(135deg, #4e73df 0%, #224abe 100%);
}
.category-tool {
  background: linear-gradient(135deg, #f6c23e 0%, #ffa900 100%);
}
.category-fastener {
  background: linear-gradient(135deg, #1cc88a 0%, #0ea570 100%);
}
.category-accessory {
  background: linear-gradient(135deg, #e74a3b 0%, #c23321 100%);
}
</style>