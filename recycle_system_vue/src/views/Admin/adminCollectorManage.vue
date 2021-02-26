<template>
  <div>
    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="tableData" :header-cell-style="{background:'transparent'}">
      <el-table-column align="center" prop="collectorPersonId" label="编号" width="70"></el-table-column>
      <el-table-column align="center" prop="collectorName" label="派送员姓名" width="170"></el-table-column>
      <el-table-column align="center" prop="userName" label="用户名" width="170"></el-table-column>
      <el-table-column align="center" prop="phone" label="电话号码" width="200"></el-table-column>
      <el-table-column align="center" prop="idcardNumber" label="身份证号" width="200"></el-table-column>
      <el-table-column align="center" prop="siteName" label="站点名称" width="300"></el-table-column>
      <el-table-column align="center" prop="userStatus" label="是否启用" >
        <template v-slot="scope">
          <el-switch v-model="scope.row.userStatus"
                     active-color="#13ce66"
                     active-value="T"
                     inactive-value="F"
                     @change="changeState(scope.row.collectorPersonId,scope.row.userStatus)"
          ></el-switch>
        </template>
      </el-table-column>
    </el-table>
    </div>
  </div>
</template>

<script>
  export default {
    created () {
      const _this=this
      axios.get('http://localhost:8181/allCollectorstatus').then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data
      })
    },
    methods:{
      changeState(id,status){
        const _this = this
        axios.post('http://localhost:8181/changeStatus/'+id+'/'+status).then(function (resp) {
          console.log(resp)
        })
      }
    },
    data(){
      return{
        tableData:[{
          collectorPersonId:1,
          collectorName:'1',
          userName:'1',
          phone:'1',
          idcardNumber:'1',
          siteName:'1',
          userStatus:'1'
        }],
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
  .title{
    font-size: 13px;
    font-weight: 700;
    height: 26px;
    line-height: 26px;
    padding-left: 10px;
    margin-left: 12PX;
    color: rgb(147,153,159);
  }
</style>
