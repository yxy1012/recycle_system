<template>
  <div v-if="show">
    <div style="display: inline-block;"><h1 class="title" >根据用户查询订单:</h1></div>
    <el-input v-model="adminrequire.username" placeholder="请输入用户名" style="width: 130px"></el-input>
    <div style="display: inline-block;"><h1 class="title" >根据快递员姓名查询订单:</h1></div>
    <el-input v-model="adminrequire.collectorname" placeholder="请输入快递员" style="width: 120px"></el-input>
    <div style="display: inline-block;"><h1 class="title" >根据日期查询订单：</h1></div>
    <el-select v-model="adminrequire.datepick" placeholder="请选择" style="width: 110px">
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker v-if="this.adminrequire.datepick=='day'" v-model="adminrequire.datebyday" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd" style="width: 190px;"></el-date-picker>
    <el-date-picker v-if="this.adminrequire.datepick=='month'" v-model="adminrequire.datebymonth" type="month" placeholder="选择月" format="yyyy年MM月" value-format="yyyy-MM" style="width: 150px;"></el-date-picker>
    <el-button  icon="el-icon-search" round  @click="findbyrequire" style="width: 80px">查询</el-button>
    <el-button  icon="el-icon-search" round  @click="findall">查询全部</el-button>

    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="tableData" :header-cell-style="{background:'transparent'}">
      <el-table-column prop="donateId" label="捐赠单号" width="80 " align="center">
    </el-table-column>
      <el-table-column prop="helpName" label="捐赠地" width="250" align="center">
      </el-table-column>
      <el-table-column prop="addressDetails" label="用户地址" width="300" align="center">
      </el-table-column>
      <el-table-column prop="scheduledTime" label="预约时间" width="200" align="center">
      </el-table-column>
      <el-table-column prop="userName" label="用户" width="100" align="center">
      </el-table-column>
      <el-table-column label="派送员" width="100" align="center">
        <template slot-scope="scope" >
          <span v-if="scope.row.collectorName!=null">{{scope.row.collectorName}}</span>
          <span v-else style="color: red">未分配</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-button  round style="background-color: #F01414;color: #ffffff " icon="el-icon-edit-outline" @click="edit(scope.row)"></el-button>
          <el-popover placement="left" width="400" trigger="click">
            <div>{{scope.row.donateDetail}}</div>
            <el-button icon="el-icon-more-outline" round slot="reference" ></el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    </div>
    <el-pagination  :background="isBackground" layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
    <el-dialog :append-to-body="true" :visible.sync="editVisible">
      <el-form  :model="editForm" ref="editForm" @submit.prevent.native >
        <el-select v-model="editForm.collectorName" placeholder="请选择" style="width: 500px">
          <el-option v-for="item in collector" :key="item.collectorId" :label="item.collectorName" :value="item.collectorName"></el-option>
        </el-select>
        <div style="height: 30px;"></div>
        <div>
          <el-button @click="closeDialog()">取消</el-button>
          <el-button type="primary" @click="submit()" style="color: black;">确定</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    methods: {
      edit(row){
        this.editForm.donateId=row.donateId
        this.editForm.collectorName=''
        this.editVisible=true
      },
      closeDialog(){
        this.editVisible=false
      },
      submit(){
        const _this=this
        axios.get('http://localhost:8181/donateplacecollector/'+this.editForm.donateId+'/'+this.editForm.collectorName+'').then(function (resp) {
          for(var j=0,len=_this.tableData.length;j<len;j++){
            if(_this.tableData[j].donateId==resp.data){
              _this.tableData[j].collectorName=_this.editForm.collectorName
            }
          }
          if(resp.data!=0){
            _this.$alert('修改派送员成功','消息',{
              confirmButtonText:'确定',
            });
          }
          _this.editVisible=false
        })
      },
      findbyrequire(){
        const _this = this
        axios.post('http://localhost:8181/adminfinddoingdonate/1/7',this.adminrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      findall(){
        const _this=this
        this.adminrequire.username=''
        this.adminrequire.collectorname=''
        this.adminrequire.datebymonth=''
        this.adminrequire.datebyday=''
        this.adminrequire.datepick='day'
        axios.post('http://localhost:8181/adminfinddoingdonate/1/7',this.adminrequire).then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      page (currentPage) {
        const _this = this
        axios.post('http://localhost:8181/adminfinddoingdonate/'+currentPage+'/7',this.adminrequire).then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      }
    },
      created () {
        const _this = this;
        axios.post('http://localhost:8181/adminfinddoingdonate/1/7',this.adminrequire).then(function (resp) {
          _this.tableData = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
          _this.show=true
        })
        axios.get('http://localhost:8181/allCollector').then(function (r) {
          console.log(r)
          _this.collector=r.data
        })
      },
      data () {
        return {
          isBackground: true,
          show:false,
          editVisible:false,
          editForm:{
            donateId:1,
            collectorName:'',
          },
          pageSize: 1,
          total: 1,
          adminrequire:{
            username:'',
            collectorname:'',
            datebyday:'',
            datebymonth:'',
            datepick:'day',
          },
          collector:[
            {
              collectorId:1,
              collectorName:'杨昕语',
            },
            {
              collectorId:2,
              collectorName:'陈南',
            },
            {
              collectorId:3,
              collectorName:'陈小南',
            },
          ],
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
          recycleOrdersDetailVoList: [{
            itemName: '纸板',
            quantity: 10,
            itemPrice: 1,
            sum: '',
          }, {
            itemName: '易拉罐',
            quantity: 20,
            itemPrice: 0.1,
            sum: '',
          }, {
            itemName: '啤酒瓶',
            quantity: 5,
            itemPrice: 1,
            sum: '',
          }, {
            itemName: '旧衣服',
            quantity: 20,
            itemPrice: 0.5,
            sum: '',
          }],
          tableData: [{
            recycleOrderId: 1,
            scheduledTime: '12月15日 下午17：00',
            addressDetails:'',
            helpName:'',
            userName: '陈南',
            addressDetails:'',
            collectorName: '陈南',
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
