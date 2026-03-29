<template>
  <!-- 轮播版：3屏一体化 Banner + 公司介绍 -->
  <el-carousel
      height="600px"
      arrow="always"
      indicator-position="outside"
      class="banner-carousel"
  >
    <!-- 轮播第1屏：企业实力 · 一站式采购 -->
    <el-carousel-item>
      <div class="carousel-item">
        <div class="content-box">
          <h1>Lighting Metal<br/>国内领先的一站式五金采购服务商</h1>
          <p class="slogan">源头直采 · 品质严控 · 高效交付 · 全程服务</p>
          <div class="desc">
            我们深耕五金供应链领域，整合全国上百家优质源头工厂，
            专注为企业、工程、经销商提供
            <span>五金零件、五金工具、紧固件、工业配件</span>
            全品类一站式采购服务。
          </div>
          <el-button type="primary" size="large" class="action-btn">
            立即咨询采购
          </el-button>
        </div>
        <div class="image-box">
          <img src="https://picsum.photos/seed/hardware-factory/600/400" alt="五金工厂" class="img-main">
          <img src="https://picsum.photos/seed/hardware-line/300/200" alt="生产线" class="img-sub1">
        </div>
      </div>
    </el-carousel-item>

    <!-- 轮播第2屏：供应链优势 · 源头工厂 -->
    <el-carousel-item>
      <div class="carousel-item">
        <div class="content-box">
          <h1>全国源头工厂直供<br/>去掉中间环节，让利客户</h1>
          <p class="slogan">千家工厂合作 · 万种现货库存 · 价格极具优势</p>
          <div class="desc">
            我们严选国内头部五金生产基地，建立直供合作体系，
            无中间商加价，确保价格优势。
            覆盖标准件、工具、配件全品类，<span>现货秒发、批量定制、工程专供</span>，
            满足各类大中小批量采购需求。
          </div>
          <el-button type="primary" size="large" class="action-btn">
            查看合作工厂
          </el-button>
        </div>
        <div class="image-box">
          <img src="https://picsum.photos/seed/hardware-warehouse/600/400" alt="五金仓库" class="img-main">
          <img src="https://picsum.photos/seed/hardware-supplier/300/200" alt="供应商" class="img-sub2">
        </div>
      </div>
    </el-carousel-item>

    <!-- 轮播第3屏：品质保障 · 服务承诺 -->
    <el-carousel-item>
      <div class="carousel-item">
        <div class="content-box">
          <h1>严苛品控体系<br/>让您采购无忧，合作放心</h1>
          <p class="slogan">全检出货 · 售后保障 · 物流极速 · 终身合作</p>
          <div class="desc">
            从原料检测到生产加工，从成品检验到物流配送，
            我们执行全流程标准化品控。
            <span>7天无理由退换、1对1专属客服、全国物流直达</span>，
            用专业与责任，成为您最稳定的五金供货合作伙伴！
          </div>
          <el-button type="primary" size="large" class="action-btn">
            了解品质保障
          </el-button>
        </div>
        <div class="image-box">
          <img src="https://picsum.photos/seed/hardware-quality/600/400" alt="品质检测" class="img-main">
          <img src="https://picsum.photos/seed/hardware-service/300/200" alt="售后服务" class="img-sub1">
        </div>
      </div>
    </el-carousel-item>
  </el-carousel>
</template>

<style scoped>
/* 轮播容器 */
.banner-carousel {
  --el-carousel-indicator-button-color: #ccc;
  --el-carousel-indicator-active-color: #2d8cf0;
}
/* 单屏布局 */
.carousel-item {
  width: 100%;
  height: 600px;
  padding: 0 10%;
  background: linear-gradient(to right, #f8f9fc, #eef2f5);
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 40px;
}

/* 左侧文案 */
.content-box {
  flex: 1;
  min-width: 500px;
}
.content-box h1 {
  font-size: 36px;
  color: #222;
  line-height: 1.4;
  margin-bottom: 16px;
  font-weight: 700;
}
.slogan {
  font-size: 18px;
  color: #2d8cf0;
  font-weight: 600;
  margin-bottom: 20px;
}
.desc {
  font-size: 16px;
  color: #555;
  line-height: 1.8;
  margin-bottom: 16px;
}
.desc span {
  color: #2d8cf0;
  font-weight: 600;
}
.action-btn {
  margin-top: 20px;
  padding: 10px 30px;
  border-radius: 6px;
}

/* 右侧图片组合 */
.image-box {
  flex: 1;
  min-width: 500px;
  position: relative;
  height: 400px;
}
.img-main {
  width: 600px;
  height: 400px;
  object-fit: cover;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0,0,0,0.1);
  position: absolute;
  z-index: 2;
}
.img-sub1 {
  width: 220px;
  height: 140px;
  object-fit: cover;
  border-radius: 8px;
  position: absolute;
  bottom: -20px;
  left: -30px;
  z-index: 3;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}
.img-sub2 {
  width: 220px;
  height: 140px;
  object-fit: cover;
  border-radius: 8px;
  position: absolute;
  top: -20px;
  right: -20px;
  z-index: 3;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}
</style>