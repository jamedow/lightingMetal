<template>
  <!-- 全局导航栏：所有页面都显示 -->
  <NavBar/>
  <!-- 页面内容 -->
  <router-view/>
</template>

<script setup>
// 全局导入导航栏
import NavBar from './components/home/NavBar.vue'
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>