<template>
  <div class="all">
  <el-container style="height:750px; border: 1px solid #eee">
    <el-aside width="220px" >
      <el-menu :default-active="this.$route.path" router>
        <el-menu-item index="/adminhomepage">
          <i class="el-icon-s-home"></i>
          <span  slot="title">主页</span>
        </el-menu-item>
        <el-submenu index="3">
          <template slot="title">查看订单</template>
          <el-menu-item-group>
            <el-menu-item index="/adminallinorder" >查看历史订单</el-menu-item>
            <el-menu-item index="/admindoinginorder" >进行中订单</el-menu-item>
            <el-menu-item index="/adminalldonate" >查看历史捐赠</el-menu-item>
            <el-menu-item index="/admindoingdonate" >进行中捐赠</el-menu-item>
            <el-menu-item index="/adminUserManager">查询所有用户</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">管理信息</template>
          <el-menu-item-group>
            <el-menu-item index="/adminCollectorManage">管理派送员</el-menu-item>
            <el-menu-item index="/ItemTypeManage">管理物品种类</el-menu-item>
            <el-menu-item index="/ItemManage">管理物品</el-menu-item>
            <el-menu-item index="/HelpThePoorManager">管理资助地区</el-menu-item>
            <el-menu-item index="/adminecharts">数据统计</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">

        <el-menu  class="el-menu-demo" mode="horizontal" @select="handleSelect"  router style="margin-left: 900px">
          <el-menu-item index="/adminhomepage"><i class="el-icon-s-home" style="font-size: 25px;" ></i></el-menu-item>
          <el-menu-item><i class="el-icon-share" style="font-size: 25px;"></i></el-menu-item>
          <el-submenu index="4">
            <template slot="title"><i class="el-icon-user" style="font-size: 25px ;"></i>{{this.$store.getters.getAdminName}}</template>
            <el-menu-item index="/login" @click="d">退出登录</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
  </div>
</template>
<script>
  export default {
    methods:{
      d(){
        this.$store.commit('setAdminId',null)
        this.$store.commit('setAdminName',null)
        sessionStorage.removeItem('token')
        sessionStorage.removeItem('identity')
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>
  * {
    background-color: transparent;
  }
  /deep/ .el-menu--inline{
    background-color: transparent;
  }
  .all{
    margin: 0;
    width: 100%;
    height: 100vh;
    font-family: "Exo", sans-serif;
    color: #fff;
    background: linear-gradient(-45deg, #ECF5FF, #F2F0FF, #84cfd5, #89c1d5);
    background-size: 400% 400%;
    animation: gradientBG 15s ease infinite;
    position: fixed;
  }
  @keyframes gradientBG {
    0% {
      background-position: 0% 50%;
    }
    50% {
      background-position: 100% 50%;
    }
    100% {
      background-position: 0% 50%;
    }
  }


  input{
    margin-top: 100px;
    z-index: 999;
    position: relative;
  }

</style>
