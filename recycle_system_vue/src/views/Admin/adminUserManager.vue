<template>
  <div v-if="show">
    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="tableData" :header-cell-style="{background:'transparent'}">
      <el-table-column prop="userId" label="用户id" width="250" align="center">
      </el-table-column>
      <el-table-column prop="userName" label="用户名" width="250" align="center">
      </el-table-column>
      <el-table-column prop="userRealname" label="姓名" width="250" align="center">
      </el-table-column>
      <el-table-column prop="phone" label="电话"  align="center">
      </el-table-column>
    </el-table>
    </div>
    <el-pagination :background="isBackground"  layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>

<script>
  export default {
    // methods:{
    //   page(currentPage){
    //     const _this = this
    //     axios.get('http://localhost:8181/alluser/'+currentPage+'/8').then(function (resp) {
    //       _this.tableData=resp.data.list
    //       _this.pageSize = resp.data.pageSize
    //       _this.total = resp.data.total
    //     })
    //   },
    // },
    // created () {
    //   const _this = this
    //   axios.get('http://localhost:8181/alluser/1/8').then(function (resp) {
    //     _this.tableData=resp.data.list
    //     _this.pageSize = resp.data.pageSize
    //     _this.total = resp.data.total
    //     _this.show=true
    //   })
    // },
    data(){
      return{
        isBackground: true,
        show:false,
        total:1,
        pageSize:1,
        tableData: [{
          userId: '',
          userName: '',
          userRealname: '',
          phone: '',
        }]
      }
   }
  }
</script>

<style scoped>
  * {
     background-color: transparent;
   }
  /deep/ .el-input__inner{
    background-color: transparent;
  }
  .user_skills /deep/  .el-table, .el-table__expanded-cell {
    background-color: transparent;
  }
  .user_skills /deep/ .el-table tr {
    background-color: transparent!important;
  }
  .user_skills /deep/  .el-table--enable-row-transition .el-table__body td, .el-table .cell{
    background-color: transparent;
  }
  /deep/ .el-pagination.is-background .el-pager li:not(.disabled) {
    color: rgb(147,153,159);
    background-color: transparent;
  }
  /deep/ .el-pagination.is-background  .btn-next{
    background-color: transparent;
  }
  /deep/ .el-pagination.is-background  .btn-prev{
    background-color: transparent;
  }
  /deep/ .btn-prev{
    background-color: transparent;
  }
</style>

