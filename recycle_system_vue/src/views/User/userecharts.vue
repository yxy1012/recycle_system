<template>
  <div>
    <div ref="chart"  style="width: 600px;height: 300px;float: left;"></div>
    <div ref="chart1" style="width: 600px;height: 300px;float: left;"></div>
    <div ref="chart2" style="width: 600px;height: 300px;float: left;"></div>
    <div ref="chart3" style="width: 600px;height: 300px;float: left;"></div>
  </div>
</template>

<script>

import * as echarts from 'echarts'
// eslint-disable-next-line no-unused-vars
var xData = ['2020-09-19', '2020-08-19', '2021-01-02', '2020-04-12']
var yData = [1, 2, 1, 2]
var xData1 = ['8:00', '9:00', '12:00', '13:00']
var yData1 = [1, 2, 1, 2]
var data=['铝', '铁', '电池', '消毒剂']
var Data = [{value: 335, name: '铝'},
  {value: 310, name: '铁'},
  {value: 234, name: '电池'},
  {value: 135, name: '消毒剂'}]
// eslint-disable-next-line no-unused-vars,no-undef
export default {
  methods: {
    initChart (container, option) {
      let myChart = echarts.init(container)
      myChart.setOption(option)
    },
  },
  mounted () {
    // 基于准备好的dom，初始化echarts实例
    setTimeout(() => {
      let option = {
        title: {
          text: '根据日期统计订单数'
        },
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
          type: 'bar', // 柱形
          data: yData
        }]
      }
      // 绘制图表
      this.initChart(this.$refs.chart, option)
      let option1 = {
        title: {
          text: '根据下单时间统计订单数'
        },
        tooltip: {},
        xAxis: {
          type: 'category',
          data: xData1
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: yData1,
          type: 'line'
        }]
      }
      // 绘制图表
      this.initChart(this.$refs.chart1, option1)
      let option2 = {
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
          data: data,
        },
        series: [
          {
            name: '占比',
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
      this.initChart(this.$refs.chart2, option2)
    }, 50)

  },
  created () {
    const _this = this
    // eslint-disable-next-line no-undef
    axios.get('http://localhost:8181/userselectCountbydate/' + _this.$store.getters.getUserId ).then(function (resp) {
        console.log(resp)
      for(var i=0,len1=xData.length;i<len1;i++){
        xData.splice(i)
      }
      for(var t=0,len2=yData.length;t<len2;t++){
        yData.splice(t)
      }
        for (var j = 0, len = resp.data.length; j < len; j++) {
          yData.push(resp.data[j].count)
          xData.push(resp.data[j].scheduledTime)
        }
      }
    )
    axios.get('http://localhost:8181/userselectCountbytime/' + _this.$store.getters.getUserId ).then(function (resp) {
        console.log(resp)
      for(var i=0,len1=xData1.length;i<len1;i++){
        xData1.splice(i)
      }
      for(var t=0,len2=yData1.length;t<len2;t++){
        yData1.splice(t)
      }
        for (var j = 0, len = resp.data.length; j < len; j++) {
          // alert(_this.x[j].recycleOrderId)
          yData1.push(resp.data[j].count)
          xData1.push(resp.data[j].scheduledTime+":00")
        }
      }
    )
    axios.get('http://localhost:8181/userselectCountByitemid/'+_this.$store.getters.getUserId).then(function (resp) {
        console.log(resp)
        for(var i=0,len1=Data.length;i<len1;i++){
          Data.splice(i)
        }
      for(var t=0,len2=Data.length;t<len2;t++){
        data.splice(t)
      }
        for (var j = 0, len = resp.data.length; j < len; j++) {
          Data.push(resp.data[j])
          data.push(resp.data[j].name)
        }
      }
    )
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped>

</style>
