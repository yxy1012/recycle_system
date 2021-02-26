<template>
  <el-container direction="vertical">

    <div style="margin-top: 20px;">
      <div style="height: 550px;width:50%;float:left;padding-left: 0px;display: inline-block;position: relative;left:-12px;"    class="foods-wrapper" ref="foodsWrapper">
        <div>
          <div style="display: inline-block;margin-right: 6px;"><h1 class="title" style="width: 85%">请选择捐赠地址：</h1></div>
          <el-select  style="width: 400px"v-model="form.helpId"  clearable placeholder="请选择捐赠地点">
            <el-option v-for="item in help_the_poor" :key="item.helpId" :label="item.helpName" :value="item.helpId"></el-option>
          </el-select>
        </div>
        <div class="block"  style="margin: 0 auto ;">
          <div style="width: 128px;display: inline-block;"><h1 class="title" style="width: 95%">请选择上门时间: </h1> </div>
          <el-date-picker
            style="margin-left: 20px;"
            v-model="form.scheduledTime"
            type="datetime"
            value-format="yyyy-MM-dd hh:mm:ss"
            placeholder="选择日期时间">
          </el-date-picker>
        </div>
        <div>
          <div style="display: inline-block;"><h1 class="title" style="width: 85%">请选择上门地址：</h1></div>
          <el-select style="width: 400px;margin-left: 3px;" v-model="form.addressId"  clearable placeholder="请选择地址">
            <el-option
              v-for="item in addressList"
              :key="item.addressId"
              :label="item.addressDetails"
              :value="item.addressId">
            </el-option>
          </el-select>
        </div>
        <div>
          <div><h1 class="title" style="width: 98%">请填写捐赠物品详细信息：</h1></div>
          <div style="position: relative;left: 12px;"><el-input  type="textarea" :rows="5" v-model="form.donateDetail" ></el-input></div>
        </div>
        <div>
          <div><h1 class="title" style="width: 98%">请选择所含有的物品类型：</h1></div>
          <div>
            <el-checkbox-group v-model="checkList" style="padding-left: 30px">
              <el-checkbox label="日用品"></el-checkbox>
              <el-checkbox label="食品"></el-checkbox>
              <el-checkbox label="衣物"></el-checkbox>
              <el-checkbox label="数码产品" ></el-checkbox>
              <el-checkbox label="文件" ></el-checkbox>
            </el-checkbox-group>
          </div>
        </div>
        <div>
          <h1 class="title" style="width: 200px;display: inline-block;">请填写大概的重量（千克）：</h1>
          <div style="width: 200px;display: inline-block;"><el-input    v-model="weight" ></el-input></div>
        </div>
        <div style="float: left;margin-left: 10px;">
          <el-button style="margin-top: 12px;"  @click="finish" >确认捐赠</el-button>
        </div>
      </div>
      <div style="display: inline-block;float: left;width: 50%;height: 550px;color: black;" id="all-map"></div>
    </div>

  </el-container>
</template>

<script>
    import AMap from 'AMap'
    export default {
      data(){
        return {
          weight:'',
          checkList: [],
          form:{
            helpId:'',
            userId:this.$store.getters.getUserId,
            scheduledTime:'',
            donateDetail:'',
            addressId:'',
          },
          addressList: [{
            addressId:1,
            addressDetails:'浙江省杭州市西湖区留和路288号浙江工业大学屏峰校区'
          }],
          help_the_poor:[
            {
              helpId:2,
              helpName:'四川齿可波西乡布西村',
              longitude:11,
              latitude:111,
              helpDetail:'这里的人们的生活都不富裕，家家户户都有好几个孩子，父母为了谋生外出打工，大多是留守儿童和老人在家，因为封建思想，有的孩子还早早地就结了婚，冬天很冷，孩子们的脸和手脚都被冻得起了冻疮，真的很不容易，希望能够得到大家的帮助。',
              helpStatus:''
            },
          ],
        }
      },
      created(){
        const _this=this;
        axios.get('http://localhost:8181/userAlladdress/'+this.$store.getters.getUserId+'').then(function (resp) {
          _this.addressList=resp.data.addressList
        })
        axios.get('http://localhost:8181/userAllHelp').then(function (resp) {
          _this.help_the_poor=resp.data
          _this.GaodeMap()
        })
      },
      methods: {
        finish(){
          const _this=this
          _this.form.donateDetail+='    所含有的物品类型有：'+_this.checkList+'。     包裹的重量大约有：'+_this.weight+'(千克)'
          if(isNaN(_this.weight)){
            _this.$alert('重量请填写数字', '消息', {
              confirmButtonText: '确定',
            })
          }
          else if(this.form.donateDetail=='') {
            _this.$alert('请填写捐赠信息', '消息', {
              confirmButtonText: '确定',
            })
          }
          else if(this.form.helpId==''){
            _this.$alert('请选择捐赠地址', '消息', {
              confirmButtonText: '确定',
            })
          }
          else if(this.form.scheduledTime=='') {
            _this.$alert('请预约时间', '消息', {
              confirmButtonText: '确定',
            })
          }
          else if(this.form.addressId==''){
            _this.$alert('请预约地址', '消息', {
              confirmButtonText: '确定',
            })
          }
          else {
              axios.post('http://localhost:8181/placeandonation',this.form).then(function (resp) {
                if(resp.data){
                  _this.$alert('捐赠订单已生成','消息',{
                    confirmButtonText:'确定',
                    callback:action => {
                      _this.$router.push('/userdoingdonate')
                    }
                  })
                }
              })
            }
        },
        GaodeMap () {
          let map = new AMap.Map('all-map', {
            center: [this.help_the_poor[0].longitude, this.help_the_poor[0].latitude],
            resizeEnable: true,
            zoom: 4
          });
          AMap.plugin(['AMap.ToolBar', 'AMap.Scale'], function () {
            map.addControl(new AMap.ToolBar())
            map.addControl(new AMap.Scale())
          })
          let infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
          //遍历生成多个标记点
          for (let i = 0, marker; i < this.help_the_poor.length; i++) {
            let marker = new AMap.Marker({
              position: [this.help_the_poor[i].longitude, this.help_the_poor[i].latitude],//不同标记点的经纬度
              map: map,
            });
            marker.content = this.help_the_poor[i].helpName+" \n "+this.help_the_poor[i].helpDetail;
            marker.on('click', markerClick);
            //marker.emit('click', {target: marker});//默认初始化不出现信息窗体,打开初始化就出现信息窗体
          }
          function markerClick(e) {
            infoWindow.setContent(e.target.content);
            infoWindow.open(map, e.target.getPosition());
            map.setFitView(e.target);
          }
        },
      }
    }
</script>

<style scoped>
  .el-button {
    background-color: transparent;
  }
  /deep/ .el-textarea__inner{
    background: transparent;
  }
  /deep/ .el-input__inner{
    background: transparent;
  }
  .foods-wrapper{
    width: 50%;
  }
  .food-item{
    display: flex;
    margin: 18px;
    padding-bottom: 18px;
    border:rgba(7,17,27,.1) 1PX solid;
  }
  .demonstration{
    position: relative;
    top:0px;
    background-color:#96AABF;
    color: #fff;
    text-align: center;
    font-size: 17px;
    font-weight: 400;
    width: 1275px;
    height: 58px;
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
