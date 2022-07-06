<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
// 柱状图
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { getConsumptionDistributionOfSpecificDay } from '@/api/access'

const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  data() {
    return {
      chart: null,
      currentDayConsumption: []
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.getConsumptionDistribution()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    // 日用电量分布
    getConsumptionDistribution() {
      var timestamp = Date.parse(new Date())
      getConsumptionDistributionOfSpecificDay({
        'timestamp': timestamp
      }).then(res => {
        console.log('consumption distribution', res)
        this.currentDayConsumption = res.data
        this.initChart()
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      var data = []
      for (let i = 10; i < 18; i++) {
        data.push(this.currentDayConsumption[i])
      }
      this.chart.setOption({
        title: {
          text: '今日用电量分布',
          left: '35%',
          top: -5
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00'],
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: [{
          name: 'pageA',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: data,
          animationDuration
        }]
      })
    }
  }
}
</script>
