<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
// 饼状图
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { getDeviceDistribution } from '@/api/device'

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
      chart: null
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.getDeviceDistribution()
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
    // 设备分布
    getDeviceDistribution() {
      getDeviceDistribution().then(res => {
        console.log('pdevice distribution', res)
        this.deviceDistribution = res.data
        this.initChart()
      })
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')

      // 数据处理
      var types = []
      this.deviceDistribution.forEach(element => {
        console.log(element)
        types.push(element.name)
      })
      this.chart.setOption({
        title: {
          text: '设备类型分布',
          left: '35%',
          top: 1
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: types
        },
        series: [
          {
            name: '设备类型',
            type: 'pie',
            roseType: 'radius',
            radius: [15, 95],
            center: ['50%', '38%'],
            data: this.deviceDistribution,
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
