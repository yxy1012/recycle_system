<template>
  <div v-if="show">
    <div style="display: inline-block;margin-right: 6px;"><h1 class="title" style="width: 90%">根据快递员姓名查询订单:</h1></div>
    <el-input v-model="collectorrequire.username" placeholder="请输入用户名" style="width: 200px"></el-input>
    <div style="display: inline-block;margin-right: 6px;"><h1 class="title" >根据日期查询订单：</h1></div>
    <el-select v-model="collectorrequire.datepick" placeholder="请选择">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.collectorrequire.datepick=='day'" v-model="collectorrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
    <el-date-picker v-if="this.collectorrequire.datepick=='month'" v-model="collectorrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM"></el-date-picker>
    <el-button  icon="el-icon-search"  @click="findbyusername">查询</el-button>
    <el-button icon="el-icon-search"   @click="findall">查询全部</el-button>

    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="tableData" :header-cell-style="{background:'transparent'}">
      <el-table-column prop="donateId" label="捐赠单号" width="80" align="center">
      </el-table-column>
      <el-table-column prop="helpName" label="捐赠地" width="250" align="center">
      </el-table-column>
      <el-table-column prop="addressDetails" label="用户地址" width="300" align="center">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="200" align="center">
      </el-table-column>
      <el-table-column prop="userName" label="用户" width="100" align="center">
      </el-table-column>
      <el-table-column prop="phone" label="联系电话" width="150" align="center">
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-popover placement="left" width="400" trigger="click">
            <div>{{scope.row.donateDetail}}</div>
            <el-button  icon="el-icon-more-outline" round slot="reference" ></el-button>
          </el-popover>
          <el-button   icon="el-icon-check"  circle  @click="finish(scope.row.donateId)"></el-button>
          <el-button  icon="el-icon-close"  circle @click="deletedonate(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    </div>
    <el-pagination :background="isBackground"  layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
  </div>
</template>

<script>
  export default {
    methods:{
      deletedonate(row){
        const _this=this
        this.$confirm("确定申请取消此订单?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            axios.get('http://localhost:8181/deleteDonateCollector/'+row.donateId+'').then(function (resp) {
              if(resp.data){
                for(var j=0,len=_this.tableData.length;j<len;j++){
                  if(_this.tableData[j].donateId==row.donateId){
                    _this.tableData.splice(j,1)
                  }
                }
              }
            })
            this.$message({
              type: "success",
              message: "申请取消此订单成功!"
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消"
            });
          });
      },
      findbyusername () {
        const _this = this
        axios.post('http://localhost:8181/collectorfinddoingdonate/1/7',this.collectorrequire).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      findall(){
        const _this=this
        this.collectorrequire.username=''
        this.collectorrequire.datebymonth=''
        this.collectorrequire.datebyday=''
        this.collectorrequire.datepick='day'
        axios.post('http://localhost:8181/collectorfinddoingdonate/1/7',this.collectorrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      page(currentPage){
        const _this = this
        axios.post('http://localhost:8181/collectorfinddoingdonate/'+currentPage+'/7',_this.collectorrequire).then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      finish(doanteId){
        const _this=this
        this.donationform.finishedTime=this.currentTime
        this.donationform.donateId=doanteId
        axios.post('http://localhost:8181/confirmdonation',this.donationform).then(function (resp) {
          console.log(resp)
          if(resp.data){
            _this.$alert('捐赠完成','消息',{
              confirmButtonText:'确定',
              callback:action => {
                _this.$router.push('/collectoralldonate')
              }
            })
          }
        })
      }
    },
    created () {
      const _this=this;
      this.timer = setInterval(function() {
        _this.currentTime = //修改数据date
          new Date().getFullYear() +
          "-" +
          (new Date().getMonth() + 1) +
          "-" +
          new Date().getDate() +
          " " +
          new Date().getHours() +
          ":" +
          new Date().getMinutes() +
          ":" +
          new Date().getSeconds();
      }, 0);
      axios.post('http://localhost:8181/collectorfinddoingdonate/1/7',this.collectorrequire).then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
        _this.show=true
      })
    },
    data(){
      return{
        isBackground: true,
        show:false,
        currentTime:'', // 获取当前时间
        pageSize:1,
        total:1,
        collectorrequire:{
          id:this.$store.getters.getCollectorId,
          username:'',
          datebyday:'',
          datebymonth:'',
          datepick:'day',
        },
        options:[
          {
            value:'day',
            label:'按日查询',
          },
          {
            value:'month',
            label:'按月查询',
          }
        ],
        donationform:{
          donateId:1,
          finishedTime:'',
        },
        tableData: [{
          donateId: 1,
          scheduledTime: '12月15日 下午17：00',
          finishedTime: '12月15日 下午17：10',
          userName: '陈南',
          helpName: '',
          addressDetails:"",
          phone: 13615787610,
        },
          {
            donateId: 2,
            scheduledTime: '12月15日 下午17：00',
            finishedTime:'12月15日 下午17：10',
            userName: '陈南',
            helpName:'',
            addressDetails:'',
            phone:13615787610,
        }]
      }
    }
  }
</script>

<style scoped>
  /deep/ .el-input__inner{
    background-color: transparent;
  }
  * {
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
    font-size: 15px;
    font-weight: 700;
    height: 26px;
    line-height: 26px;
    padding-left: 10px;
    margin-left: 12PX;
    color: rgb(147,153,159);
  }
</style>
