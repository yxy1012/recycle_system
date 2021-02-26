<template>
  <div>
    <div style="text-align: center; position:absolute;top:20px;z-index: 1;">
      <el-button  @click="toadd" icon="el-icon-plus" ></el-button>
    </div>
    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="tableData" :header-cell-style="{background:'transparent'}">
      <el-table-column prop="helpName" label="名字" width="300" align="center"></el-table-column>
      <el-table-column prop="helpDetail" label="详细信息" width="300" align="center"></el-table-column>
      <el-table-column prop="helpStatus" label="状态" width="300" align="center"></el-table-column>
      <el-table-column  fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-button  round   slot="reference" icon="el-icon-edit-outline"  @click="edit(scope.row)"></el-button>
          <el-button   round slot="reference" icon="el-icon-delete"   @click="dele(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    </div>
    <el-pagination :background="isBackground" layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>
    <el-dialog :append-to-body="true" :visible.sync="editVisible">
      <el-form  :model="editform" :rules="rules" ref="editform" @submit.prevent.native >
        <el-form-item label="修改地址名字"  prop="helpName">
          <el-input v-model="editform.helpName"></el-input>
        </el-form-item>
        <el-form-item label="修改详细信息"  prop="helpDetail">
          <el-input v-model="editform.helpDetail" ></el-input>
        </el-form-item>
        <el-form-item label="修改状态"  prop="helpStatus">
          <el-radio-group v-model="editform.helpStatus">
            <el-radio label="启用" style="width: 75px"><b>启用</b></el-radio>
            <el-radio label="停用" style="width: 75px"><b>停用</b></el-radio>
          </el-radio-group>
        </el-form-item>
        <div>
          <el-button @click="closeDialog()">取消</el-button>
          <el-button type="primary" @click="submit('editForm')" style="color: black;">确定</el-button>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    created () {
      const _this=this
      axios.get('http://localhost:8181/allHelpThePoor/1/2').then(function (resp) {
        console.log(resp)
        _this.tableData=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
      })
    },
    methods:{
      toadd(){
        this.$router.push('/AddHelpThePoor')
      },
      dele(row) {
        const _this=this
        this.$confirm("确定删除该地址?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            axios.delete('http://localhost:8181/deleteHelpThePoor/'+row.helpId+'').then(function (resp) {
              for(var j=0,len=_this.tableData.length;j<len;j++) {
                if(_this.tableData[j].helpId==row.helpId){
                  _this.tableData.splice(j,1)
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

      edit(row){
        this.editVisible=true
        this.editform.helpId=row.helpId
        this.editform.helpName=row.helpName
        this.editform.helpDetail=row.helpDetail
        this.editform.helpStatus=row.helpStatus
      },
      closeDialog(){
        this.editVisible=false
      },
      submit(){
        const _this=this
        axios.get('https://restapi.amap.com/v3/geocode/geo?address='+this.editform.helpName+'&key=8c922d0176df163a311ac3425db373c6').then(function (resp) {
          console.log(resp)
          _this.jingwei=resp.data.geocodes[0].location
          _this.jw=_this.jingwei.split(",")
          _this.editform.longitude=parseFloat(_this.jw[0])
          _this.editform.latitude=parseFloat(_this.jw[1])
          axios.post('http://localhost:8181/updateHelpThePoor',_this.editform).then(function (r) {
            if(r.data){
              for(var j=0,len=_this.tableData.length;j<len;j++){
                if(_this.tableData[j].helpId==_this.editform.helpId){
                  _this.tableData[j].helpName=_this.editform.helpName
                  _this.tableData[j].helpDetail=_this.editform.helpDetail
                  _this.tableData[j].helpStatus=_this.editform.helpStatus
                  _this.tableData[j].latitude=_this.editform.latitude
                  _this.tableData[j].longitude=_this.editform.longitude
                }
              }
            }
          })
        })
        this.editVisible=false
      },
      page(currentPage){
        const _this=this
        axios.get('http://localhost:8181/allHelpThePoor/'+currentPage+'/2').then(function (resp) {
          _this.tableData=resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
    },
    watch:{
      $route(){
        window.location.reload()
      }
    },
    data(){
      return{
        isBackground: true,
        pageSize:1,
        total:1,
        tableData:[{
            helpId:1,
            helpName:1,
            longitude:'',
            latitude:'',
            helpDetail:1,
            helpStatus:'',
        }],
        jingwei:'',
        jw:'',
        editVisible:false,
        editform:{
          helpId:1,
          helpName:1,
          longitude:'',
          latitude:'',
          helpDetail:1,
          helpStatus:'',
        },
        rules:{
          helpName:[
            { required: true, message: '请输入名字', trigger: 'blur' },
          ],
          helpDetail:[
            { required: true, message: '请输入详细信息', trigger: 'blur' },
          ],
          helpStatus:[
            { required: true, message: '请输入状态', trigger: 'blur' },
          ],
        }

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
