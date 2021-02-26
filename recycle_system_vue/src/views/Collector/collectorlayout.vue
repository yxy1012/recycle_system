<template>
  <div class="all">
  <el-container style="height:750px;">
    <el-aside width="220px" >
      <el-menu :default-active="this.$route.path" router>
        <el-menu-item index="/collectorhomepage">
          <i class="el-icon-s-home"></i>
          <span  slot="title">主页</span>
        </el-menu-item>
        <el-submenu index="2">
          <template slot="title">确认信息</template>
          <el-menu-item-group>
            <el-menu-item index="/collectordoinginorder" >进行中订单</el-menu-item>
            <el-menu-item index="/collectordoingdonate" >进行中捐赠</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">查看信息</template>
          <el-menu-item-group>
            <el-menu-item index="/collectorallinorder" >查看历史订单</el-menu-item>
            <el-menu-item index="/collectoralldonate" >查看历史捐赠</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">设置</template>
          <el-menu-item-group>
            <el-menu-item index="/collectoralterinfo">个人中心</el-menu-item>
            <el-menu-item index="/collectorecharts">数据统计</el-menu-item>
            <el-menu-item index="/login" @click="d">退出登录</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">

        <el-menu  class="el-menu-demo" mode="horizontal" @select="handleSelect"  router  style="margin-left: 900px">
          <el-menu-item index="/collectorhomepage"><i class="el-icon-s-home" style="font-size: 25px;" ></i></el-menu-item>
          <el-menu-item><i class="el-icon-share" style="font-size: 25px;"></i></el-menu-item>
          <el-menu-item index="/collectoralterinfo"><i class="el-icon-set-up" style="font-size: 25px;"></i></el-menu-item>
          <el-submenu index="4">
            <template slot="title"><i class="el-icon-user" style="font-size: 25px ;"></i>{{this.$store.getters.getCollectorName}}</template>
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
        this.$store.commit('setCollectorId',null)
        this.$store.commit('setCollectorName',null)
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
    background: linear-gradient(-45deg, #ff727a, #F2F0FF, #d4a1d5, #d5c5cb);
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
