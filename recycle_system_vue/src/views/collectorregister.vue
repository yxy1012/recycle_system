<template>
  <div class="login">
    <vue-particles class="login-bg" color='#39AFFD' :particleOpacity="0.7" :particlesNumber="100" shapeType="circle" :particleSize="4" linesColor="#8DD1FE"
                   :linesWidth="1" :lineLinked="true" :lineOpacity="0.4" :linesDistance="150" :moveSpeed="3" :hoverEffect="true" hoverMode="grab" :clickEffect="true" clickMode="push"
    >
    </vue-particles>
    <!-- 登录面板 -->
    <div class="login-box">
      <div class="login-box-title">
        派送员注册
      </div>
      <div class="login-box-from">
        <el-form :model="collectorregister" :rules="registerrules" ref="registerForm"  class="demo-ruleForm" >

          <el-form-item prop="userName">
            <el-input v-model="collectorregister.userName" placeholder="请输入用户名" size="medium">
              <el-button slot="prepend" icon="el-icon-user"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="collectorName">
            <el-input v-model="collectorregister.collectorName" placeholder="请输入真实姓名" size="medium">
              <el-button slot="prepend" icon="el-icon-user"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="collectorregister.password"  placeholder="请输入密码" size="medium" >
              <el-button slot="prepend" icon="el-icon-key"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="repassword">
            <el-input v-model="collectorregister.repassword"  placeholder="请重复密码" size="medium" >
              <el-button slot="prepend" icon="el-icon-key"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="phone">
            <el-input v-model="collectorregister.phone"  placeholder="请输入电话号码" size="medium" >
              <el-button slot="prepend" icon="el-icon-phone"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="idcardNumber">
            <el-input v-model="collectorregister.idcardNumber"  placeholder="请输入身份证" size="medium" >
              <el-button slot="prepend" icon="el-icon-postcard"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="siteName">
            <el-input v-model="collectorregister.siteName"  placeholder="请输入您的站点的地址" size="medium" >
              <el-button slot="prepend" icon="el-icon-s-home"></el-button>
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
        } else if (value !== this.collectorregister.password) {
          callback(new Error('两次输入密码不一致！'))
        }
        callback()
      };
      return {
        identity:'2',
        jingwei:'',
        collectorregister:{ //派送员注册表单
          collectorName:'',
          userName:'',
          password:'',
          repassword:'',
          phone:'',
          idcardNumber:'',
          siteName:'',
          longitude:'',
          latitude:'',
        },
        registerrules: {  //登陆验证规则
          userName: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'},
            {validator: letterRule, trigger: 'blur'}
          ],
          collectorName: [
            {required: true, message: '请输入真实姓名', trigger: 'blur'},
            {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'},
            {validator: letterRule, trigger: 'blur'}
          ],
          siteName: [
            {required: true, message: '请输入站点地址', trigger: 'blur'},
            {min: 1, max: 1000000, message: '', trigger: 'blur'},
            {validator: letterRule, trigger: 'blur'}
          ],
          phone: [
            {required: true, message: '请输入电话号码', trigger: 'blur'},
            {min: 11, max: 11, message: '长度为11个数字', trigger: 'blur'},
            {validator: letterRule, trigger: 'blur'}
          ],
          idcardNumber: [
            {required: true, message: '请输入电话号码', trigger: 'blur'},
            {min: 18, max: 18, message: '长度为18个数字', trigger: 'blur'},
            {validator: letterRule, trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'},
            {validator: letterRule, trigger: 'blur'}
          ],
          repassword: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'},
            {validator: validateRePassword, trigger: 'blur'}
          ]
        },

      }
    },
    methods:{
      submitForm(formName){
        const _this=this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.identity==2){
              axios.get('https://restapi.amap.com/v3/geocode/geo?address='+this.collectorregister.siteName+'&key=8c922d0176df163a311ac3425db373c6').then(function (resp) {
                console.log(resp)
                _this.jingwei=resp.data.geocodes[0].location
                _this.collectorregister.longitude=parseFloat(_this.jingwei.substr(0,10))
                _this.collectorregister.latitude=parseFloat(_this.jingwei.substr(11,10))
                axios.post('http://localhost:8181/register/collector',_this.collectorregister).then(function (r) {
                  console.log(r)
                  if(r.data){
                    _this.$alert('注册成功，等待审核','消息',{
                      confirmButtonText:'确定',
                      callback:action => {
                        _this.$router.push('/login')
                      }
                    })
                  }
                  else {
                    _this.$alert('用户名已存在');
                  }
                })
              })
            }
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
    margin-top: -300px;

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
