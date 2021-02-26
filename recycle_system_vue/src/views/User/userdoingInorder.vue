<template>
  <div v-if="show">
    <div style="display: inline-block;margin-right: 6px;"><h1 class="title" style="width: 90%">根据快递员姓名查询订单:</h1></div>
    <el-input v-model="userrequire.collectorname" placeholder="请输入快递员姓名" style="width: 200px"></el-input>
    <div style="display: inline-block;margin-right: 6px;"><h1 class="title" >根据日期查询订单：</h1></div>
    <el-select v-model="userrequire.datepick" placeholder="请选择">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.userrequire.datepick=='day'" v-model="userrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd"></el-date-picker>
    <el-date-picker v-if="this.userrequire.datepick=='month'" v-model="userrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM"></el-date-picker>
    <el-button   icon="el-icon-search"   @click="findbycellectorname">查询</el-button>
    <el-button   icon="el-icon-search"  @click="findall">查询全部</el-button>

    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="tableData" :header-cell-style="{background:'transparent'}">
      <el-table-column prop="recycleOrderId" label="订单号" width="80" align="center">
      </el-table-column>
      <el-table-column prop="addressDetails" label="地址" width="300" align="center">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="200" align="center">
      </el-table-column>
      <el-table-column prop="totalAmount" label="总价" width="100" align="center">
      </el-table-column>
      <el-table-column label="配送员" width="140" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.collectorName!=null">{{scope.row.collectorName}}</span>
          <span v-else>未分配</span>
        </template>
      </el-table-column>
      <el-table-column  label="联系电话" width="200" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.phone!=null">{{scope.row.phone}}</span>
          <span v-else>未分配</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="250" align="center">
        <template slot-scope="scope">
          <el-popover placement="left" width="400" trigger="click">
            <el-table :data="recycleOrdersDetailVoList">
              <el-table-column width="100" prop="itemName" label="物品"></el-table-column>
              <el-table-column width="100" prop="quantity" label="数量(斤)"></el-table-column>
              <el-table-column width="100" prop="itemPrice" label="单价(元/斤)"></el-table-column>
              <el-table-column width="100" prop="sum" label="总价(元)"></el-table-column>
            </el-table>
            <el-button   icon="el-icon-more-outline" round  slot="reference" @click="getDetail(scope.row.recycleOrderId)" ></el-button>
          </el-popover>
          <el-button  icon="el-icon-edit-outline" round @click="jumpUpdate(scope.row.recycleOrderId)" style="margin-left: 8px;"></el-button>
          <el-button  icon="el-icon-delete" round @click="Delete(scope.row.recycleOrderId)"></el-button>
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
      Delete(recycleOrderId){
        const _this=this
        this.$confirm("确定删除该订单?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            axios.delete('http://localhost:8181/userdeleteorder/'+recycleOrderId+'').then(function (resp) {
              if(resp.data){
                _this.$alert('回收订单删除成功','消息',{
                  confirmButtonText:'确定',
                })
                for(var j=0,len=_this.tableData.length;j<len;j++){
                  if(_this.tableData[j].recycleOrderId==recycleOrderId){
                    _this.tableData.splice(j,1)
                  }
                }
              }
            })
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });

      },
      jumpUpdate(recycleOrderId){
        this.$router.push({
          path: "/userupdateorder",
          query: {recycleOrderId: recycleOrderId }
        });
      },
      findbycellectorname(){
        const _this=this
          axios.post('http://localhost:8181/userfindAllDoingOrders/1/7',this.userrequire).then(function (resp) {
            _this.tableData=resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
      },
      findall(){
        const _this=this
        this.userrequire.collectorname=''
        this.userrequire.datebymonth=''
        this.userrequire.datebyday=''
        this.userrequire.datepick='day'
        axios.post('http://localhost:8181/userfindAllDoingOrders/1/7',this.userrequire).then(function (resp) {
          console.log(resp)
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      getDetail(recycleOrderId){
        const _this = this
        axios.get('http://localhost:8181/OrdersDetail/'+recycleOrderId+'').then(function (resp) {
          _this.recycleOrdersDetailVoList=resp.data
        })
      },
      page(currentPage){
        const _this = this
          axios.post('http://localhost:8181/userfindAllDoingOrders/'+currentPage+'/7',this.userrequire).then(function (resp) {
            _this.tableData = resp.data.list
            _this.pageSize = resp.data.pageSize
            _this.total = resp.data.total
          })
      }
    },
    watch:{
      $route(){
        window.location.reload()
      }
    },
    created () {
      const _this=this;
      axios.post('http://localhost:8181/userfindAllDoingOrders/1/7',this.userrequire).then(function (resp) {
        console.log(resp)
        _this.show=true
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
      })
    },
    data(){
      return{
        isBackground: true,
        show:false,
        pageSize:1,
        total:1,
        userrequire:{
          id:this.$store.getters.getUserId,
          collectorname:'',
          datebyday:'',
          datebymonth:'',
          datepick:'day',//判断是按月查询还是按日查询
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
        recycleOrdersDetailVoList:[{
          itemName: '纸板',
          quantity: 10,
          itemPrice: 1,
          sum:'',
        }, {
          itemName: '易拉罐',
          quantity: 20,
          itemPrice: 0.1,
          sum:'',
        }, {
          itemName: '啤酒瓶',
          quantity: 5,
          itemPrice: 1,
          sum:'',
        }, {
          itemName: '旧衣服',
          quantity: 20,
          itemPrice: 0.5,
          sum:'',
        }],
        tableData: [{
          recycleOrderId: 1,
          scheduledTime: '12月15日 下午17：00',
          collectorName: '陈南',
          totalAmount:'',
          addressDetails:'',
          phone:13615787610,
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
    font-size: 15px;
    font-weight: 700;
    height: 26px;
    line-height: 26px;
    padding-left: 10px;
    margin-left: 12PX;
    color: rgb(147,153,159);
  }
</style>
