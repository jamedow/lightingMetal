<template>
  <!-- 后台管理系统基础布局 -->
  <el-container style="height: 100vh; border: 1px solid #eee">
    <!-- 侧边栏 -->
    <el-aside style="background-color: rgb(238, 241, 246)" width="200px">
      <el-menu
          class="el-menu-vertical-demo"
          default-active="1"
          router
      >
        <el-menu-item index="/inquiry/list">
          <i class="el-icon-document"></i>
          <span slot="title">询价单管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <!-- 右侧内容区 -->
    <el-container>
      <!-- 头部 -->
      <el-header style="text-align: right; font-size: 12px">
        <div> Lighting Metal 后台管理</div>
      </el-header>

      <!-- 主内容（路由页面渲染在这里） -->
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
// 布局逻辑（空即可，基础布局无需额外逻辑）
</script>

<style scoped>
.el-header {
  background-color: #fff;
  color: #333;
  line-height: 60px;
  border-bottom: 1px solid #e6e6e6;
}

.el-aside {
  color: #333;
}
</style>