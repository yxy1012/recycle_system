<template>
  <div>
    <div class="user_skills" style="margin-top:20px;" >
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>个人信息</span>
    </div>
    <div class="text item">
      <div style="margin: 20px">
      <div>ID：{{userinfo.collectorPersonId}}</div><div style="position: relative; top:-18px;left:600px">用户名：{{userinfo.userName}}</div>
      </div>
      <div style="margin: 20px">
      <div>真实姓名：{{userinfo.collectorName}}</div><div style="position: relative; top:-18px;left:600px">手机号：{{userinfo.phone}}</div>
        <div style="position: relative; top:-38px;left:-425px"><el-button style="float: right; padding: 3px 0"  icon="el-icon-edit-outline" @click="modifyPhone(userinfo.phone)"></el-button></div>
      </div>
      <div style="margin: 20px">
        <div>站点名字：{{userinfo.siteName}}<span><el-button style=" padding: 3px 0"  icon="el-icon-edit-outline" @click="modifySite(userinfo.siteName)"></el-button></span></div>
<!--        <div style="position: relative; top:-20px;left:-1035px"><el-button style="float: right; padding: 3px 0"  icon="el-icon-edit-outline" @click="modifySite(siteName)"></el-button></div>-->
      </div>
    </div>
  </el-card>
    <el-dialog :append-to-body="true" :visible.sync="editPhoneVisible">
      <el-form  :model="editForm" :rules="rules" ref="editForm" @submit.prevent.native >
        <el-form-item label="修改手机号码"  prop="phone2">
          <el-input v-model="editForm.phone2" @keyup.enter.native="sumbitEditPhone('editForm')"></el-input>
        </el-form-item>
        <div>
          <el-button @click="closePhoneDialog()">取消</el-button>
          <el-button type="primary" @click="sumbitEditPhone('editForm')" style="color: black;">确定</el-button>
        </div>
      </el-form>
    </el-dialog>

    <el-dialog :append-to-body="true" :visible.sync="editSiteVisible">
      <el-form  :model="siteForm"  ref="siteForm" @submit.prevent.native >
        <el-form-item label="修改站点名称"  prop="siteName2">
          <el-input v-model="siteForm.siteName2" @keyup.enter.native="sumbitEditSite()"></el-input>
        </el-form-item>
        <div>
          <el-button @click="closeSiteDialog()">取消</el-button>
          <el-button type="primary" @click="sumbitEditSite()" style="color: black;">确定</el-button>
        </div>
      </el-form>
    </el-dialog>
    </div>
  </div>
</template>

<script>
  let _index;
  export default {

    created () {
      const _this=this
      axios.get('http://localhost:8181/collectorInfo/'+this.$store.getters.getCollectorId+'').then(function (resp) {
        console.log(resp)
        _this.userinfo=resp.data
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
          collectorPersonId:this.$store.getters.getCollectorId,
        userinfo:{
          userName:'yxy',
          collectorName:'杨昕语',
          phone:1,
          siteId:2,
          siteName:'',
        },
          editForm: {
            phone2:''
          },
          siteForm:{},
          jingwei:'',
          jw:'',
          editPhoneVisible:false,
          rules:{
            phone2:[
              { required: true, message: '请输入密码', trigger: 'blur' },
              { min: 11, max: 11, message: '长度需为11', trigger: 'blur' },
              { validator: letterRule, trigger: 'blur' }
            ]
          },
        editSiteVisible:false,
        siteForm:{
            siteName2:''
        }
      }
    },
    methods: {
      modifyPhone(phone) {
        //初始化editForm中的phone2
        this.editForm.phone2=phone
        this.editPhoneVisible = true

      },
      sumbitEditPhone(formName){
        //改变editForm中的phone2
        const _this=this
        this.$refs[formName].validate((valid) => {//检验手机
          if (valid) {
            this.userinfo.phone = this.editForm.phone2;
            axios.post('http://localhost:8181/collectorupdatePhone/'+this.collectorPersonId+'/'+this.userinfo.phone+'').then(function (resp) {
              console.log(resp)
            })
            this.editPhoneVisible = false;
          }else {
            _this.$alert('手机输入格式错误','提示')
          }
          }
        )
      },
      closePhoneDialog(){
        this.editPhoneVisible = false;
      },

      modifySite(siteName){
        this.siteForm.siteName2=siteName
        this.editSiteVisible = true
      },
      sumbitEditSite(){
        const _this=this
        this.userinfo.siteName=this.siteForm.siteName2
        this.siteForm.siteName=this.userinfo.siteName
        this.siteForm.siteId=this.userinfo.siteId
        axios.get('https://restapi.amap.com/v3/geocode/geo?address='+this.siteForm.siteName+'&key=8c922d0176df163a311ac3425db373c6').then(function (resp) {
          console.log(resp)
          _this.jingwei=resp.data.geocodes[0].location
          _this.jw=_this.jingwei.split(",")
          _this.siteForm.longitude=parseFloat(_this.jw[0])
          _this.siteForm.latitude=parseFloat(_this.jw[1])
          axios.post('http://localhost:8181/updateSite',_this.siteForm).then(function (r) {
            console.log(r)
          })
        })
        this.editSiteVisible = false;

      },
      closeSiteDialog(){
        this.editSiteVisible = false;
      }
    }

  }
</script>

<style scoped>

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
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 1270px;
  }
</style>
