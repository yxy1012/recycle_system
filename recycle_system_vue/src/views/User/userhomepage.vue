<template>
  <div class="block">
    <el-carousel :interval="4000" type="card" height="300px">
      <el-carousel-item v-for="item in imagebox" :key="item.id" >
        <img :src="item.idView" class="image">
      </el-carousel-item>
    </el-carousel>
    <div style="display: flex">
<!--      左侧-->
      <div>
        <div style="display: flex;margin:30px 0px" >
          <el-card class="box-card" style=" margin-right: 60px;margin-left: 60px">
            <div slot="header" class="clearfix">
              <span>我的订单</span>
              <el-button style="float: right; padding: 3px 0" type="text"  @click="doingInorder">查看订单</el-button>
            </div>
            您有{{total}}个正在进行的订单
          </el-card>
          <el-button style="width: 200px;height: 120px; box-shadow: 0px 1px 5px #cbcaca; " @click="recycle">立即下单</el-button>
        </div>
        <div style="display: flex;margin:30px 0px" >
          <el-card class="box-card" style="margin-right: 60px;margin-left: 60px">
            <div slot="header" class="clearfix">
              <span>我的捐赠订单</span>
              <el-button style="float: right; padding: 3px 0" type="text"  @click="doingDonation">查看捐赠订单</el-button>
            </div>
            您有{{total2}}个正在进行中的捐赠订单
          </el-card>
          <el-button style="width: 200px;height: 120px; box-shadow: 0px 1px 5px #cbcaca; " @click="donate">立即捐赠</el-button>
        </div>
        <el-card class="box-card" style="margin-left: 60px; width: 740px;height: 180px">
            <div slot="header" class="clearfix">
            <span>回收流程</span>
            </div>
            <div style="text-align: center">
              <i class="el-icon-shopping-cart-2" ></i>
              <i class="el-icon-arrow-right" ></i>
              <i class="el-icon-refresh" ></i>
              <i class="el-icon-arrow-right" ></i>
              <i class="el-icon-circle-check" ></i>
              <i class="el-icon-arrow-right" ></i>
              <i class="el-icon-chat-dot-square" ></i>
            </div>
            <pre style="font-size: 15px">        预约下单             上门回收              完成订单             评价订单
        </pre>
        </el-card>
      </div>
<!--      右侧-->
      <div>

        <el-card class="box-card" style="width: 390px ;margin:30px 40px">
          <div slot="header" class="clearfix">
            <span>回收小贴士</span>
          </div>
          <div  class="clearfix">
            <p><i class="el-icon-question"></i>1.预约上门收费吗？</p>
            <p><i class="el-icon-s-opportunity"></i>上门回收是免费的，不收取任何费用。</p>
            <el-divider></el-divider>
            <p><i class="el-icon-question"></i>2.如何预约上门回收？</p>
            <p><i class="el-icon-s-opportunity"></i>在首页点击“立即下单”，填写预约信息，回收人员会在预约上门时间免费上门收取废品，请您在回收员
            上门之前把废品进行打包，等待回收员上门。</p>

          </div>
        </el-card>
      </div>
    </div>


  </div>

</template>

<script>
export default {
  name: 'index',
  data(){
    return{
      total:1,
      total2:1,
      userrequire:{
        id:this.$store.getters.getUserId,
        collectorname:'',
        datebyday:'',
        datebymonth:'',
        datepick:'day',//判断是按月查询还是按日查询
      },
      tableData: [{
        id: 1304,
        booktime: '12月15日 下午17：00',
        collector: '陈南',
        phone:13615787610
      }, {
        id: 1567,
        booktime: '12月15日 下午17：00',
        collector: '陈大南',
        phone:13615787610
      }, {
        id: 3456,
        booktime: '12月15日 下午17：00',
        collector: '陈小南',
        phone:13615787610
      }],
      imagebox:[{
        id:0,
        idView:require('../../assets/imagebox/1.png')
      },{
        id:1,
        idView:require('../../assets/imagebox/2.png')
      },{
        id:2,
        idView:require('../../assets/imagebox/3.png')
      },{
        id:3,
        idView:require('../../assets/imagebox/4.png')
      }

      ]
    }
  },
  created () {
    const _this=this;
    axios.post('http://localhost:8181/userfindAllDoingOrders/1/8',this.userrequire).then(function (resp) {
      console.log(resp)
      _this.show=true
      _this.total = resp.data.total
    })
    axios.post('http://localhost:8181/userfinddoingdonate/1/8',this.userrequire).then(function (resp) {
      console.log(resp)
      _this.show=true
      _this.total2 = resp.data.total

    })
  },
  methods: {
    doingInorder () {
      this.$router.push('/userdoingInorder')
    },
    doingDonation () {
      this.$router.push('/userdoingdonate')
    },
    recycle () {
      this.$router.push('/recycle')
    },
    donate () {
      this.$router.push('/donate')
    },


  }
}
</script>

<style scoped>
  * {
    background-color: transparent;
  }
  .el-card{
    background-color: transparent;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
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
    width: 480px;
  }
  .el-icon-shopping-cart-2,.el-icon-refresh,.el-icon-circle-check,.el-icon-chat-dot-square{
    font-size: 40px;margin-right: 45px
  }
  .el-icon-arrow-right{
    font-size: 20px;margin-right: 45px;
  }
  .el-icon-question{
    margin-right: 10px;
    font-size: 18px;
    color: #6dbf6d;
  }
  .el-icon-s-opportunity{
    margin-right: 10px;
    font-size: 18px;
    color: #fa914b;
  }

</style>
