<template>
  <!-- 路由出口：所有页面都在这里渲染 -->
  <router-view/>
</template>

<script lang="ts" setup>
// 空即可，无需逻辑
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body, html, #app {
  width: 100%;
  height: 100%;
}
</style>