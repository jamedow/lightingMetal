<template>
  <div class="home-page">
    <!-- 所有组件正常使用 -->
    <NavBar />
    <BannerWithCompany />
    <Category />
    <Merchant />
    <Footer />
  </div>
</template>

<script setup lang="ts">
// 🔥 修复核心：标准路径 + 完整.vue后缀 + 文件名大小写完全匹配
import NavBar from '@/components/home/NavBar.vue'
import BannerWithCompany from '@/components/home/BannerWithCompany.vue'
import Category from '@/components/home/Category.vue'
import Merchant from '@/components/home/Merchant.vue'
import Footer from '@/components/home/Footer.vue'
</script>

<style lang="scss">
@use '@/assets/styles/home/index.scss' as *;
</style>