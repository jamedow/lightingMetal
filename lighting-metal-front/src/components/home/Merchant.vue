<template>
  <div class="merchant-section">
    <h2 class="title">供应链实力</h2>
    <!-- 🔥 核心：轮播组件 一屏一组 节省首屏空间 -->
    <el-carousel
        height="560px"
        arrow="hover"
        indicator-position="outside"
        loop
        autoplay
        :interval="4000"
        class="supply-carousel"
    >
      <el-carousel-item v-for="(item, idx) in merchantList" :key="idx">
        <div class="merchant-card">
          <!-- 生产实力 -->
          <div class="merchant-production">
            <h4>
              <span class="icon icon-production"></span>
              生产实力
            </h4>
            <div class="production-images">
              <img v-for="(img, index) in item.production" :key="index" :src="img" alt="生产线" />
            </div>
          </div>

          <!-- 爆款商品 -->
          <div class="merchant-goods">
            <h4>
              <span class="icon icon-goods"></span>
              爆款商品
            </h4>
            <div class="goods-grid">
              <div class="goods-item" v-for="good in item.goods" :key="good.id">
                <img :src="`https://picsum.photos/200/200?random=${good.id}`" alt="商品" />
                <span class="tag" :class="good.tag">{{ good.tag }}</span>
                <p class="goods-name">{{ good.name }}</p>
                <span class="goods-price">{{ good.price }}</span>
              </div>
            </div>
          </div>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup lang="ts">
import { useHomeData } from '@/composables/useHomeData'
const { merchantList } = useHomeData()
</script>

<style scoped>
/* 整体区域 */
.merchant-section {
  padding: 50px 10%;
  background: #fff;
}
.title {
  text-align: center;
  font-size: 28px;
  margin-bottom: 30px;
  font-weight: 600;
  color: #1a1a1a;
}

/* 轮播容器优化 */
.supply-carousel {
  --el-carousel-indicator-color: #ddd;
  --el-carousel-indicator-active-color: #2d8cf0;
  max-width: 1400px;
  margin: 0 auto;
}

/* 产品卡片 - 极简高级 */
.merchant-card {
  border: 1px solid #f0f0f0;
  border-radius: 20px;
  padding: 36px;
  background: #ffffff;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.04);
  height: 100%;
  box-sizing: border-box;
}

/* 标题样式 */
.merchant-production h4,
.merchant-goods h4 {
  font-size: 20px;
  color: #1a1a1a;
  margin: 0 0 20px;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 12px;
}

/* 你选定的 ✨ 轻奢极简图标 */
.icon {
  display: inline-block;
  width: 20px;
  height: 20px;
  position: relative;
}
.icon-production {
  background: #e6f0ff;
  border-radius: 3px;
}
.icon-production::before {
  content: "";
  position: absolute;
  width: 8px;
  height: 6px;
  background: #2d8cf0;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.icon-goods {
  width: 6px;
  height: 6px;
  background: #ff4d4f;
  border-radius: 50%;
  box-shadow: 0 0 0 3px #ffe7e7;
}

/* 生产线图片 */
.production-images {
  display: flex;
  gap: 24px;
  margin-bottom: 32px;
}
.production-images img {
  width: 48%;
  height: 220px;
  object-fit: cover;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.06);
}

/* 商品网格 */
.goods-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}
.goods-item {
  background: #fafafa;
  border-radius: 12px;
  padding: 16px;
  text-align: center;
  border: none;
  transition: all 0.2s ease;
  position: relative;
}
.goods-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.04);
}
.goods-item img {
  width: 100%;
  height: 140px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 12px;
}

/* 商品标签 */
.tag {
  position: absolute;
  top: 20px;
  right: 20px;
  padding: 3px 10px;
  border-radius: 6px;
  font-size: 12px;
  font-weight: 500;
  color: #fff;
}
.tag.爆款 { background: #ff4d4f; }
.tag.热销 { background: #fa8c16; }
.tag.现货 { background: #00b96b; }
.tag.定制 { background: #722ed1; }
.tag.新品 { background: #0094ff; }

.goods-name {
  font-size: 14px;
  color: #333;
  margin: 0 0 8px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.goods-price {
  font-size: 16px;
  color: #ff4d4f;
  font-weight: 600;
}
</style>