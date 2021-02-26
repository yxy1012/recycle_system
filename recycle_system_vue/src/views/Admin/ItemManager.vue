<template>
  <div>
    <div style="text-align: center; position:absolute;top:20px;z-index: 1;">
      <el-button  @click="add" icon="el-icon-plus" ></el-button>
    </div>
    <div class="user_skills" style="margin-top:20px;" >
    <el-table :data="itemList" :header-cell-style="{background:'transparent'}">
      <el-table-column label="序号" width="150" align="center">
        <template slot-scope="scope">
          <span>{{scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="itemTypeName" label="种类" width="200" align="center">
      </el-table-column>
      <el-table-column prop="itemName" label="物品" width="200" align="center">
      </el-table-column>
      <el-table-column prop="itemPrice" label="单价（元/斤）" width="200" align="center">
      </el-table-column>
      <el-table-column  label="废品图片" width="200" align="center" >
        <template slot-scope="scope">
          <el-image style="width: 30px; height: 30px" :src="scope.row.itemPic" :preview-src-list="[scope.row.itemPic]">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column  fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-button   round slot="reference" icon="el-icon-edit-outline"   @click="modifyData(scope.$index, scope.row)"></el-button>
          <el-button   round icon="el-icon-delete" slot="reference" @click="handleDelete(scope.$index, scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    </div>
    <el-pagination  :background="isBackground" layout="total, prev, pager, next, jumper" :page-size="pageSize" :total="total" @current-change="page"></el-pagination>

    <el-dialog :append-to-body="true" :visible.sync="dialogFormVisible"  title="新增废品种类">
      <!-- 在el-dialog中进行嵌套el-form实现弹出表格的效果 -->
      <el-form :model="form" :rules="rules2" ref="form" @submit.native.prevent>
        <el-form-item label="种类" :label-width="formLabelWidth" prop="itemTypeId">
          <!--          <el-input v-model="form.type" auto-complete="off" @keyup.enter.native="update"></el-input>-->
          <el-select v-model="form.itemTypeId" placeholder="请选择种类" >
            <el-option v-for='item in option' :key="item.itemTypeId" :value='item.itemTypeId' :label='item.itemTypeName'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物品" :label-width="formLabelWidth" prop="itemName">
          <el-input v-model="form.itemName" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
        <el-form-item label="单价" :label-width="formLabelWidth" prop="itemPrice">
          <el-input v-model.number="form.itemPrice" auto-complete="off" @keyup.enter.native="update"></el-input>
        </el-form-item>
        <el-form-item label="废品图片" :label-width="formLabelWidth" prop="itemPic">
          <el-input v-model="form.itemPic" autocomplete="off" placeholder="图片 URL"></el-input>
          <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <!-- 设置触发更新的方法 -->
        <el-button type="primary"  @click="update('form')" style="color: black;">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog  :append-to-body="true" :visible.sync="centerDialogVisible" title="修改废品信息">
      <el-form  :model="editForm"  :rules="rules" ref="editForm" @submit.native.prevent>
        <el-form-item label="种类" :label-width="formLabelWidth">
          <el-select v-model="editForm.alttype" placeholder="请选择种类">
            <el-option v-for='item in option' :key="item.itemTypeId" :value='item.itemTypeId' :label='item.itemTypeName'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物品" prop="altitem">
          <el-input v-model="editForm.altitem"  @keyup.enter.native="submitEditRow()"></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="altprice">
          <el-input v-model.number="editForm.altprice"  @keyup.enter.native="submitEditRow()"></el-input>
        </el-form-item>
        <el-form-item label="废品图片" prop="altPic">
          <el-input v-model="editForm.altPic" autocomplete="off" placeholder="图片 URL"></el-input>
          <img-upload @onUpload="altImg" ref="imgUpload"></img-upload>
        </el-form-item>
      </el-form>
      <div>
        <el-button @click="closeDialog()">取消</el-button>
        <el-button type="primary"  @click="submitEditRow('editForm')" style="color: black;">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

  import ImgUpload from './ImgUpload'
  let _index,itemTypeName;
  export default {
    components: {ImgUpload},
    created () {
      const _this=this
      axios.get('http://localhost:8181/allItem/1/7').then(function (resp) {
        console.log(resp)
        _this.itemList=resp.data.list
        _this.pageSize = resp.data.pageSize
        _this.total = resp.data.total
        _this.show=true
      })
      axios.get('http://localhost:8181/allItemType').then(function (resp) {
        _this.option=resp.data
      })
    },

    data() {
      let letterRule = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('输入内容不能为空'));
        } else{
          callback();
        }
      };
      return {
        total:1,
        pageSize:1,
        isBackground: true,
        option:[{
          itemTypeId:1,
          itemTypeName:'钢铁'
        },
          {
            itemTypeId:2,
            itemTypeName:'电子设备'
          },
        ],
        rules:{
          alttype:[
            { required: true, message: '请输入物品种类', trigger: 'blur' },
          ],
          altitem:[
            { required: true, message: '请输入物品名', trigger: 'blur' },
          ],
          altprice:[
            { required: true, message: '请输入单价', trigger: 'blur' },
            { type: 'number', message: '单价必须为数字值 ' }
          ],
          altPic:[
            { required: true, message: '请输入图片', trigger: 'blur' },
          ],
        },
        rules2:{
          itemTypeId:[
            { required: true, message: '请输入物品种类', trigger: 'blur' },
          ],
          itemName:[
            { required: true, message: '请输入物品名', trigger: 'blur' },
          ],
          itemPrice:[
            { required: true, message: '请输入单价', trigger: 'blur' },
            { type: 'number', message: '单价必须为数字值 ' }
          ],
          itemPic:[
            { required: true, message: '请输入图片', trigger: 'blur' },
          ],
        },
        itemList: [{
          itemTypeId:1,
          itemTypeName:'塑料',
          itemId:1,
          itemName:'塑料瓶',
          itemPrice:5,
          itemPic:"",
        }],
        centerDialogVisible: false,
        dialogFormVisible: false,
        formLabelWidth: "80px",
        // 设置form用于进行添加的时候绑定值
        form: {
          itemTypeId:'',
          itemName:'',
          itemPrice:'',
          itemPic:''
        },
        value6: "",
        currentPage3: 1,
        currentIndex: "",
        editDialogVisible: false,
        editForm: {
          alttype:'',
          altitem:'',
          altprice:'',
          altPic:''
        },
      }
    },
    methods: {
      page (currentPage) {
        const _this = this
        axios.get('http://localhost:8181/allItem/'+currentPage+'/7').then(function (resp) {
          console.log(resp)
          _this.itemList = resp.data.list
          _this.pageSize = resp.data.pageSize
          _this.total = resp.data.total
        })
      },
      handleDelete (index, row) {
        // 设置类似于console类型的功能
        this.$confirm("确定删除该废品?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            // 移除对应索引位置的数据，可以对row进行设置向后台请求删除数据
            axios.post('http://localhost:8181/deleteItem',row).then(function (resp){
              console.log(resp);
            })
            this.itemList.splice(index, 1);
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
      modifyData(index, row) {
        this.centerDialogVisible = true
        this.editForm.alttype = row.itemTypeName;
        itemTypeName=row.itemTypeName;
        this.editForm.altitem = row.itemName;
        this.editForm.altprice = row.itemPrice;
        this.editForm.altPic = row.itemPic;//重置对象
        _index = index;
      },
      submitEditRow(formName) {
        let editData = _index;
        const _this=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (itemTypeName !== this.editForm.alttype) {
              this.itemList[editData].itemTypeId = this.editForm.alttype;
            }
            this.itemList[editData].itemName = this.editForm.altitem;
            this.itemList[editData].itemPrice = this.editForm.altprice;
            this.itemList[editData].itemPic = this.editForm.altPic;
            axios.post('http://localhost:8181/updateItem', this.itemList[editData]).then(function (resp) {
              console.log(resp);
            })
            this.centerDialogVisible = false;
            location.reload()
          }else {
            _this.$alert('输入格式错误','提示')
          }
        })
      },
      closeDialog(){
        this.centerDialogVisible=false
        console.log("editfrom",this.editForm)
      },
      add() {
        this.form = {
          itemTypeId: "",
          itemName: "",
          itemPrice: "",
          itemPic: ""
        };
        //   设置点击按钮之后进行显示对话框
        this.dialogFormVisible = true;
      },
      update(formName) {
        const _this=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('http://localhost:8181/insertItem', this.form).then(function (resp) {
              console.log(resp);
              // this.itemList.push(this.form);
            })
            this.dialogFormVisible = false;
            location.reload()
          }else {
            _this.$alert('输入格式错误','提示')
          }
        })
      },
      cancel() {
        // 取消的时候直接设置对话框不可见即可
        this.dialogFormVisible = false;
      },


      uploadImg () {
        this.form.itemPic= this.$refs.imgUpload.url
      },
      altImg(){
        this.editForm.altPic= this.$refs.imgUpload.url
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

  /deep/ .el-pagination.is-background .el-pager li:not(.disabled){
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
