<template>
  <div class="block">
          <div style="display: flex;margin:30px 0px;" >
            <el-card class="box-card" style="margin-right: 30px;margin-left: 60px; border-color: #c3dede" shadow="hover">
              <div slot="header" class="clearfix" style="color:#2b9088;font-weight: bold" >
                <span>派送员</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="collectorManage">管理</el-button>
              </div>
              <div style="font-size: 30px">
                {{ collectorcount }}
              </div>
            </el-card>
            <el-card class="box-card" style="margin-right: 30px; border-color: #c3dede" shadow="hover">
              <div slot="header" class="clearfix" style="color:#2b9088;font-weight: bold" >
                <span>用户</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="allUser">查看</el-button>
              </div>
              <div style="font-size: 30px">
                {{ usercount }}
              </div>
            </el-card>
            <el-card class="box-card" style="margin-right: 30px; border-color: #c3dede " shadow="hover">
              <div slot="header" class="clearfix" style="color:#2b9088;font-weight: bold" >
                <span>总交易额</span>
              </div>
              <div style="font-size: 30px">
                ￥{{ sumcount }}
              </div>
            </el-card>
          </div>

        <div style="display: flex;">
        <el-card class="box-card" style="margin-right: 30px;margin-left: 60px;width: 500px">
          <div slot="header" class="clearfix">
            <span>根据下单月份统计订单数</span>
          </div>
          <div ref="chart" style="width: 500px;height: 300px"></div>
        </el-card>
        <el-card class="box-card" style="margin-right: 20px;margin-left: 60px;width: 500px">
          <div slot="header" class="clearfix">
            <span>每月回收额</span>
          </div>
          <div ref="chart2" style="width: 500px;height: 300px"></div>
        </el-card>
        </div>
      </div>
</template>

<script>
import * as echarts from 'echarts'
var xData = ['2020-09', '2020-10', '2020-11', '2020-12']
var yData = [1, 2, 1, 2]
var xData2=['2020-9', '2020-10','2020-11','2020-12']
var yData2 = [34,39,35,50]
var Data=[{value: 335, name: '铝'},
  {value: 310, name: '铁'},
  {value: 234, name: '电池'},
  {value: 135, name: '消毒剂'}]
export default {
  name: 'index',
  created () {
    const _this = this
    // eslint-disable-next-line no-undef
    axios.get('http://localhost:8181/adminselectCountbydate').then(function (resp) {
        console.log(resp)
        for (var j = 0, len = resp.data.length; j < len; j++) {
          // alert(_this.x[j].recycleOrderId)
          yData.splice(j, 1, resp.data[j].count)
          xData.splice(j, 1, resp.data[j].scheduledTime)
        }
      }
    )
    axios.get('http://localhost:8181/adminselectAmountbydate').then(function (resp) {
        console.log(resp)
        /*   Data=resp.data;
           alert(JSON.stringify(Data))*/
        for (var j = 0, len = resp.data.length; j < len; j++) {
          yData2.splice(j, 1, resp.data[j].count)
          xData2.splice(j, 1, resp.data[j].scheduledTime)
        }
      }
    )
    axios.get('http://localhost:8181/usercount').then(function (resp) {
      _this.usercount=resp.data
    })
    axios.get('http://localhost:8181/totalAmount').then(function (resp) {
      _this.sumcount=resp.data
    })
    axios.get('http://localhost:8181/allCollectorstatus').then(function (resp) {
      console.log(resp)
      _this.collectorcount=resp.data.length
    })
  },
  mounted () {
    // 基于准备好的dom，初始化echarts实例
    setTimeout(() => {
    let option = {
    /*  title: {
        text: '根据下单月份统计订单数'
      },*/
      tooltip: {}, // 悬浮提示，不写就没有提示
      xAxis: { // 横坐标
        // data: JSON.stringify(this.y)
        type: 'category',
        data: xData
      },
      yAxis: {
        type: 'value'
      }, // 纵坐标，可以不写根据data里的数据列出来
      series: [{
        name: '数量', // 可以不写
        type: 'line', // 柱形
        data: yData,
        color:'#48b7ae'
      }]
    }
    // 绘制图表
    this.initChart(this.$refs.chart, option)
    let option2 = {
     /* title: {
        text: '每月回收金额'
      },*/
      tooltip: {},
      xAxis: {
        type: 'category',
        data: xData2
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: yData2,
        type: 'line',
        color:'#48b7ae'
      }]
    }
    // 绘制图表
    this.initChart(this.$refs.chart2, option2)
    let option3 = {
      title: {
        text: '废品统计',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['铝', '铁', '电池', '消毒剂']
      },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: Data,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    this.initChart(this.$refs.chart3, option3)
    }, 50)
  },
  data(){
    return{
      collectorcount:9,
      usercount:10,
      sumcount:100,
      donatecount:1000,
      count:3,
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
  methods: {
    initChart (container, option) {
      const myChart = echarts.init(container)
      myChart.setOption(option)
    },
    allUser(){
      this.$router.push('/adminUserManager')
    },
    doingInorder () {
      this.$router.push('/collectordoingInorder')
    },
    recycle () {
      this.$router.push('/collectordoinginorder')
    },
    collectorManage () {
      this.$router.push('/adminCollectorManage')
    }
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
