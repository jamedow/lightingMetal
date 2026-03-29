<template>
  <div class="production-section">
    <h2 class="title">生产实力</h2>
    <div class="images">
      <img v-for="img in productionImages" :key="img" :src="img">
    </div>
  </div>
</template>
<script setup lang="ts">
import { useHomeData } from '@/composables/useHomeData'
const { productionImages } = useHomeData()
</script>
<style scoped>
.production-section { padding: 60px 10%; background: #f9f9f9; }
.images { display: flex; gap: 20px; flex-wrap: wrap; justify-content: center; }
img { width: 400px; height: 300px; border-radius: 10px; }
</style>