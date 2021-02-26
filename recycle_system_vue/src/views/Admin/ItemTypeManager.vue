<template>
  <div>
    <div style="text-align: center; position:absolute;top:20px;z-index: 1;">
      <el-button  @click="additemTypeName" icon="el-icon-plus" ></el-button>
    </div>
    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="ItemType" :header-cell-style="{background:'transparent'}">
      <el-table-column prop="itemTypeId" label="Id" width="300" align="center"></el-table-column>
      <el-table-column prop="itemTypeName" label="名字" width="300" align="center"></el-table-column>
      <el-table-column  fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-button  round slot="reference" icon="el-icon-edit-outline"  @click="editItemTypeName(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    </div>
    <el-dialog title="编辑物品种类" :append-to-body="true" :visible.sync="editdialogFormVisible">
      <el-form :model="editform" @submit.native.prevent>
        <el-form-item label="物品种类" :label-width="formLabelWidth">
          <el-input v-model="editform.itemTypeName" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary"  @click="update" style="color: black;">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="新增物品种类"  :append-to-body="true" :visible.sync="adddialogFormVisible">
      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :model="addform" @submit.native.prevent>
        <el-form-item label="物品种类" :label-width="formLabelWidth">
          <el-input v-model="addform.itemTypeName" auto-complete="off" @keyup.enter.native="add"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="canceladd">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary"  @click="add" style="color: black;">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  export default {
    created () {
      const _this =this
      axios.get('http://localhost:8181/allItemType').then(function (resp) {
        _this.ItemType=resp.data
      })
    },
    methods:{
      editItemTypeName(row){
        this.editform.itemTypeName=row.itemTypeName
        this.editform.itemTypeId=row.itemTypeId
        this.editdialogFormVisible=true
      },
      update(){
        const _this=this
        this.editdialogFormVisible=false
        axios.post('http://localhost:8181/updateItemType',this.editform).then(function (resp) {
          for(var j=0,len=_this.ItemType.length;j<len;j++){
            if(_this.ItemType[j].itemTypeId==_this.editform.itemTypeId){
              _this.ItemType[j].itemTypeName=_this.editform.itemTypeName
            }
          }
        })
      },
      cancel(){
        this.editdialogFormVisible=false
      },
      additemTypeName(){
        this.adddialogFormVisible=true
        this.addform.itemTypeName=''
      },
      add(){
        const _this=this
        this.adddialogFormVisible=false
        axios.post('http://localhost:8181/insertItemType',this.addform).then(function (resp) {
          _this.ItemType.push(resp.data);
        })
      },
      canceladd(){
        this.adddialogFormVisible=false
      }
    },
    data(){
      return {
        ItemType:[{
          itemTypeId:'1',
          itemTypeName:'123',
        }],
        editform:{
          itemTypeId:'',
          itemTypeName:'',
        },
        formLabelWidth: "80px",
        editdialogFormVisible:false,
        adddialogFormVisible:false,
        addform:{
          itemTypeName:'',
        },
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
