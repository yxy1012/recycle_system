<template>
  <el-form  :model="editform" :rules="rules" ref="editform" style="width: 500px" >
    <el-form-item label="地址名字"  prop="helpName">
      <el-input v-model="editform.helpName"></el-input>
    </el-form-item>
    <el-form-item label="详细信息"  prop="helpDetail">
      <el-input v-model="editform.helpDetail" :rows="5" type="textarea"></el-input>
    </el-form-item>
    <el-form-item label="状态"  prop="helpStatus">
      <el-radio-group v-model="editform.helpStatus">
        <el-radio label="启用" style="width: 75px;"><b>启用</b></el-radio>
        <el-radio label="停用" style="width: 75px"><b>停用</b></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button @click="submitForm('editform')">提交</el-button>
      <el-button @click="resetForm('editform')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    methods:{
      submitForm(formName) {
        const _this = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.get('https://restapi.amap.com/v3/geocode/geo?address='+this.editform.helpName+'&key=8c922d0176df163a311ac3425db373c6').then(function (resp) {
              _this.jingwei = resp.data.geocodes[0].location
              _this.jw=_this.jingwei.split(",")
              _this.editform.longitude=parseFloat(_this.jw[0])
              _this.editform.latitude=parseFloat(_this.jw[1])
              axios.post('http://localhost:8181/insertHelpThePoor',_this.editform).then(function (r) {
                if(r.data){
                  _this.$alert('添加成功','消息',{
                    confirmButtonText:'确定',
                    callback:action => {
                      _this.$router.push('/HelpThePoorManager')
                    }
                  });
                }
              })
            })
          } else {
            this.$message.error('输入错误，请检查');
            return false;
          }
        });
      },
      resetForm(formName){
        this.$refs[formName].resetFields();
      },
    },
    data(){
      return{
        editform:{
          helpName:'',
          longitude:'',
          latitude:'',
          helpDetail:'',
          helpStatus:'',
        },
        jingwei:'',
        jw:'',
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
  /deep/ .el-textarea__inner{
    background-color: transparent;
  }
</style>
