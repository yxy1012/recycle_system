<template>
  <div class="login">
    <vue-particles class="login-bg" color='#39AFFD' :particleOpacity="0.7" :particlesNumber="100" shapeType="circle" :particleSize="4" linesColor="#8DD1FE"
      :linesWidth="1" :lineLinked="true" :lineOpacity="0.4" :linesDistance="150" :moveSpeed="3" :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="push"
    >
    </vue-particles>
    <div class="login-box">
      <div class="login-box-title">
        用户注册
      </div>
      <div class="login-box-from">
        <el-form :model="registerForm" :rules="rules" ref="registerForm"  class="demo-ruleForm">
          <el-form-item prop="userName">
            <el-input v-model="registerForm.userName" placeholder="请输入用户名" size="medium">
              <el-button slot="prepend" icon="el-icon-user"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="userRealname">
            <el-input v-model="registerForm.userRealname" placeholder="请输入真实姓名" size="medium">
              <el-button slot="prepend" icon="el-icon-user"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="registerForm.password"  placeholder="请输入密码" size="medium" >
              <el-button slot="prepend" icon="el-icon-key"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="repassword">
            <el-input v-model="registerForm.repassword"  placeholder="请重复密码" size="medium" >
              <el-button slot="prepend" icon="el-icon-key"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="phone">
            <el-input v-model="registerForm.phone"  placeholder="请输入电话号码" size="medium" @keyup.enter.native="submitForm('registerForm')">
              <el-button slot="prepend" icon="el-icon-phone"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="medium"  style="width:100%" @click="submitForm('registerForm')" >注册</el-button>
          </el-form-item>

        </el-form>
      </div>
    </div>
  </div>

</template>

<script>
  export default {
    data(){
      let letterRule = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('输入内容不能为空'));
        } else{
          callback();
        }
      };
      let validateRePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码！'))
        } else if (value !== this.registerForm.password) {
          callback(new Error('两次输入密码不一致！'))
        }
        callback()
      };
      return {
        registerForm:{  // 登陆表单
          userName: '',
          userRealname: '',
          password: '',
          repassword:'',
          phone:'',
        },
        rules:{  //登陆验证规则
          userName:[
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
            { validator: letterRule, trigger: 'blur' }
          ],
          userRealname:[
            { required: true, message: '请输入真实姓名', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
            { validator: letterRule, trigger: 'blur' }
          ],
          phone:[
            { required: true, message: '请输入电话号码', trigger: 'blur' },
            { min: 11, max: 11, message: '长度为11个数字', trigger: 'blur' },
            { validator: letterRule, trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' },
            { validator: letterRule, trigger: 'blur' }
          ],
          repassword: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' },
            { validator: validateRePassword, trigger: 'blur' }
          ]
        }
      }
    },
    methods:{
      submitForm(formName){
        const _this=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
              axios.post('http://localhost:8181/registeruser',this.registerForm).then(function (resp) {
                console.log(resp)
                if(resp.data){
                  _this.$alert('注册成功','消息',{
                    confirmButtonText:'确定',
                    callback:action => {
                      _this.$router.push('/login')
                    }
                  });
                }
                else {
                  _this.$alert('用户名已存在');
                  _this.registerForm.userName='用户名已存在，请重新输入用户名'
                }
              })
          }else{
            _this.$alert('注册信息填写错误','提示');
          }
        });
      },

    }
  }
</script>

<style scoped>
  .login{
    width: 100%;
    height: 100%;
    position: relative;
  }
  .login-bg{
    width: 100%;
    height: 100%;
    background: #3E3E3E;
  }
  .login-box{
    width: 350px;
    background: hsla(0,0%,100%,.3);
    border-radius: 5px;
    box-shadow: 0 0 2px #f7f7f7;
    border: 1px solid #f7f7f7;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -175px;
    margin-top: -225px;

  }
  .login-box-title{
    line-height: 50px;
    font-size: 20px;
    color: #ffffff;
    text-align: center;
    border-bottom: 1px solid #ffffff;
  }
  .login-box-from{
    width: 100%;
    height: auto;
    padding: 30px;
    box-sizing: border-box;
  }
  b{
    color: orange;
  }
</style>
