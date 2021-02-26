<template>
  <div>
    <h3>无权限访问</h3>
    <el-button @click="$router.go(-2)">返回</el-button>
  </div>

</template>

<script>
  export default {
    name: '404'
  }
</script>

<style scoped>

</style>
