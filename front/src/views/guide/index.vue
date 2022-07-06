<template>
  <div
    v-loading="fullscreenLoading"
    class="dashboard-editor-container"
    element-loading-text="视频正在处理中，请稍后"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >
    <div class="text-container">

      <div class="text-box">
        <!--        <input id="file-input" type="file" class="file-select" @change="uploadChange">-->
        <!--        <el-button id="upload-button" type="primary" @click="buttonUpload()">上传</el-button>-->
        <el-upload
          class="upload-demo"
          :action="uploadActionUrl"
          :on-preview="handlePreview"
          :before-upload="beforeUpload"
          :on-remove="handleRemove"
          :on-change="onUploadChange"
          :before-remove="beforeRemove"
          :on-progress="onUploadProcess"
          :on-error="onUploadError"
          multiple
          :limit="10"
          :on-exceed="handleExceed"
          :file-list="fileList"
          :on-success="handleSuccess"
          :show-file-list="false"
        ><el-button type="primary">点击上传</el-button>

        </el-upload>
        <div class="progress">
          <el-progress v-if="uploading" :text-inside="true" :stroke-width="25" :percentage="process" status="success" :format="formatProcess" />
        </div>
        <div class="logo">
          <div class="logo-first">
            <img src="@/assets/imgs/logo01.png">
          </div>
          <div class="logo-second">
            <img src="@/assets/imgs/logo02.png">
          </div>
        </div>
      </div>

    </div>
    <div class="show-container">
      <div class="video-box">
        <div class="main-video-container">
          <!-- <img class ="main-video-container-img" src="https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/video-identity/post1.png"/> -->
          <img class="main-video-container-img" :src="mainVideoUrl">
          <div class="container">
            <div>
              <div :class="textAnimation1? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation1')">后外点冰跳</div>
              <div :class="inAnimation1?'anim':''" class="spectrum spectrum1" @click="changeAnim('inAnimation1')" />
            </div>
            <div>
              <div :class="textAnimation2? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation2')">后内点冰跳</div>
              <div :class="inAnimation2?'anim':''" class="spectrum spectrum2" @click="changeAnim('inAnimation2')" />
            </div>
            <div>
              <div :class="textAnimation3? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation3')">勾手跳</div>
              <div :class="inAnimation3?'anim':''" class="spectrum spectrum3" @click="changeAnim('inAnimation3')" />
            </div>
            <div>
              <div :class="textAnimation4? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation4')">后内结环跳</div>
              <div :class="inAnimation4?'anim':''" class="spectrum spectrum4" @click="changeAnim('inAnimation4')" />
            </div>
            <div>
              <div :class="textAnimation5? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation5')">后外结环跳</div>
              <div :class="inAnimation5?'anim':''" class="spectrum spectrum5" @click="changeAnim('inAnimation5')" />
            </div>
            <div>
              <div :class="textAnimation6? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation6')">阿克塞尔跳</div>
              <div :class="inAnimation6?'anim':''" class="spectrum spectrum6" @click="changeAnim('inAnimation6')" />
            </div>
            <div>
              <div :class="textAnimation7? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation7')">燕式旋转</div>
              <div :class="inAnimation7?'anim':''" class="spectrum spectrum7" @click="changeAnim('inAnimation7')" />
            </div>
            <div>
              <div :class="textAnimation8? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation8')">直立旋转</div>
              <div :class="inAnimation8?'anim':''" class="spectrum spectrum8" @click="changeAnim('inAnimation8')" />
            </div>
            <div>
              <div :class="textAnimation9? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation9')">蹲踞旋转</div>
              <div :class="inAnimation9?'anim':''" class="spectrum spectrum9" @click="changeAnim('inAnimation9')" />
            </div>
            <div>
              <div :class="textAnimation10? 'text-anim':''" class="action" @click="changeTextAnim('textAnimation10')">非基本姿态</div>
              <div :class="inAnimation10?'anim':''" class="spectrum spectrum10" @click="changeAnim('inAnimation10')" />
            </div>
          </div>
        </div>
        <!-- 热图 and 3d 骨架 -->
        <div class="sub-video-container">
          <div class="sub-video-container-videos">
            <div class="sub-video-container-videos-hot">
              <img class="sub-video-container-videos-img" :src="hotVideoUrl">
              <div class="sub-video-container-videos-title">注意力热图</div>
            </div>
            <div class="sub-video-container-videos-bone">
              <img class="sub-video-container-videos-img" :src="boneVideoUrl">
              <div class="sub-video-container-videos-title">3D建模</div>
            </div>
          </div>
          <div class="sub-video-container-charts">
            <div class="sub-video-container-charts-chart">
              <table class="angle-table">
                <tr>
                  <td class="angle-title">左肩膀</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.left_shoulder" :end-val="poseResult.left_shoulder" :duration="800" class="card-panel-num" />°
                  </td>
                  <td class="angle-title">右肩膀</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.right_shoulder" :end-val="poseResult.right_shoulder" :duration="800" class="card-panel-num" />°
                  </td>
                </tr>
                <tr>
                  <td class="angle-title">左手臂</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.left_hip" :end-val="poseResult.left_hip" :duration="800" class="card-panel-num" />°
                  </td>
                  <td class="angle-title">右手臂</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.right_hip" :end-val="poseResult.right_hip" :duration="800" class="card-panel-num" />°
                  </td>
                </tr>
                <tr>
                  <td class="angle-title">左膝盖</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.left_knee" :end-val="poseResult.left_knee" :duration="800" class="card-panel-num" />°
                  </td>
                  <td class="angle-title">右膝盖</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.right_knee" :end-val="poseResult.right_knee" :duration="800" class="card-panel-num" />°
                  </td>
                </tr>
                <tr>
                  <td class="angle-title">左脚踝</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.left_ankle" :end-val="poseResult.left_ankle" :duration="800" class="card-panel-num" />°
                  </td>
                  <td class="angle-title">右脚踝</td>
                  <td class="angle-value">
                    <count-to :start-val="poseResultOld.right_ankle" :end-val="poseResult.right_ankle" :duration="800" class="card-panel-num" />°
                  </td>
                </tr>
              </table>
            </div>
            <div class="sub-video-container-charts-title">关键点角度</div>
          </div>
        </div>
      </div>
    </div>
    <div class="ci-container">
      <div class="ci-box">
        <div id="confidence-chart" class="ci-box-charts" />
      </div>
    </div>
    <div v-if="reportShow" class="report">
      <div class="report-box">
        <el-table
          :data="reportData"
          max-height="400"
          style="width: 100%; height: auto"
        >
          <el-table-column
            type="index"
            width="100"
            align="center"
            label="动作序号"
          />
          <el-table-column
            label="时间"
            align="center"
            width="400"
          >
            <template slot-scope="scope">
              <i class="el-icon-time" />
              <span style="margin-left: 10px">{{ scope.row.time }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="动作名称"
            align="center"
            width="450"
          >
            <template slot-scope="scope">
              <el-tag size="medium">{{ scope.row.actionName }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="得分"
            align="center"
            width="450"
          >
            <template slot-scope="scope">
              <el-tag size="medium">{{ scope.row.score }}</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>

import waves from '@/directive/waves' // waves directive
import { videoPlayer } from 'vue-video-player'
import 'videojs-contrib-hls'
import 'video.js/dist/video-js.css'
import 'vue-video-player/src/custom-theme.css'
import { dealVideoImg, dealVideo, setVideoInfo, getDealedVideoInfo, getImgs, videoUpload, videoUploadV2 } from '@/api/video'
import { getToken } from '@/utils/auth' // get token from cookie
import $ from 'jquery'
import CountTo from 'vue-count-to'
import { formatTime } from '@/utils'
export default {
  name: 'DashboardAdmin',
  components: {
    videoPlayer,
    CountTo
  },
  directives: { waves },

  data() {
    return {
      local: false,
      fullscreenLoading: false,
      // video url https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/video-identity/post1.png
      mainVideoUrl: 'https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/back-2.png',
      hotVideoUrl: 'https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/hot-poster-1.png',
      boneVideoUrl: 'https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/bone-poster.png',
      // echarts
      pathSymbols: {
        reindeer:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/01.png',
        plane:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/02.png',
        rocket:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/03.png',
        train:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/04.png',
        ship:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/05.png',
        car:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/06.png',
        run:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/07.png',
        walk:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/08.png',
        squatRotate:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/09.png',
        else:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/10.png'
      },
      confidenceOption: {
        backgroundColor: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [{
            offset: 0, color: '#AED6F1' // 0% 处的颜色
          }, {
            offset: 1, color: '#AED6F1' // 100% 处的颜色
          }],
          global: false // 缺省为 false
        },
        title: {
          text: '置信度识别',
          textStyle: {
            color: 'white',
            fontWeight: 'bold',
            fontSize: 28
          },
          padding: [10, 10, 50, 10],
          left: 600
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'none'
          },
          formatter: function(params) {
            return params[0].name + ': ' + params[0].value
          }
        },
        xAxis: {
          data: ['后外冰点跳', '后内冰点跳', '勾手跳', '后内结环跳', '后外结环跳', '阿克塞尔跳', '燕式旋转', '直立旋转', '蹲踞旋转', '非基本姿态'],
          axisTick: { show: true },
          axisLine: { show: true },
          axisLabel: {
            color: 'white',
            fontWeight: 'bold',
            fontSize: 15
          }
        },
        yAxis: {
          splitLine: { show: true },
          axisTick: { show: true },
          axisLine: { show: true },
          axisLabel: { show: true }
        },
        color: ['#3498DB'],
        series: [{
          name: 'hill',
          type: 'pictorialBar',
          barCategoryGap: '-130%',
          // symbol: 'path://M0,10 L10,10 L5,0 L0,10 z',
          symbol: 'path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z',
          itemStyle: {
            opacity: 0.5
          },
          emphasis: {
            itemStyle: {
              opacity: 1
            }
          },
          data: [0.6, 0.4, 0.1, 0.1, 0.1, 0, 0, 0, 0, 0],
          z: 10
        },
        {
          name: 'glyph',
          type: 'pictorialBar',
          barGap: '-100%',
          symbolPosition: 'end',
          symbolSize: 50,
          symbolOffset: [0, '-120%'],
          data: [
            {
              value: 0.6,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/01.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.6,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/02.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.1,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/03.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.1,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/04.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.1,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/05.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.1,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/06.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.1,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/07.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.1,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/08.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.1,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/09.png',
              symbolSize: [28, 28]
            },
            {
              value: 0.7,
              symbol: 'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/10.png',
              symbolSize: [28, 28]
            }
          ]
        }]
      },
      confidenceChart: undefined,
      chartId: 'confidence-chart',
      // websocket
      readyWebSocket: undefined, // 监控后端是否视频处理完成
      websockets: {
        actionClassificationWebsocket: undefined,
        actionConfidenceWebsocket: undefined,
        poseResultWebsocket: undefined
      },
      actionClassficationWSUrl: 'ws://106.75.81.133:4011/ws/action_classification/result_stream',
      actionConfidenceWSUrl: 'ws://106.75.81.133:4011/ws/action_confidence/result_stream',
      poseResultWSUrl: 'ws://106.75.81.133:4011/ws/pose/result_stream',
      poseResult: {
        left_shoulder: 0,
        right_shoulder: 0,
        left_hip: 0,
        right_hip: 0,
        left_knee: 0,
        right_knee: 0,
        left_ankle: 0,
        right_ankle: 0
      },
      poseResultOld: {
        left_shoulder: 0,
        right_shoulder: 0,
        left_hip: 0,
        right_hip: 0,
        left_knee: 0,
        right_knee: 0,
        left_ankle: 0,
        right_ankle: 0
      },
      // animition
      inAnimation1: false,
      inAnimation2: false,
      inAnimation3: false,
      inAnimation4: false,
      inAnimation5: false,
      inAnimation6: false,
      inAnimation7: false,
      inAnimation8: false,
      inAnimation9: false,
      inAnimation10: false,
      textAnimation1: false,
      textAnimation2: false,
      textAnimation3: false,
      textAnimation4: false,
      textAnimation5: false,
      textAnimation6: false,
      textAnimation7: false,
      textAnimation8: false,
      textAnimation9: false,
      textAnimation10: false,
      animationMap: {
        '后外点冰跳': ['inAnimation1', 'textAnimation1'],
        '后内点冰跳': ['inAnimation2', 'textAnimation2'],
        '勾手跳': ['inAnimation3', 'textAnimation3'],
        '后内结环跳': ['inAnimation4', 'textAnimation4'],
        '后外结环跳': ['inAnimation5', 'textAnimation5'],
        '阿克塞尔跳': ['inAnimation6', 'textAnimation6'],
        '燕式旋转': ['inAnimation7', 'textAnimation7'],
        '直立旋转': ['inAnimation8', 'textAnimation8'],
        '蹲踞旋转': ['inAnimation9', 'textAnimation9'],
        '非基本姿态旋转': ['inAnimation10', 'textAnimation10']
      },
      // video info dialog
      dialogFormVisible: false,
      curVideoInfo: {
        name1: '用户1',
        sex1: '1',
        name2: '用户2',
        sex2: '2'
      },
      form: {
        name1: '',
        sex1: '',
        name2: '',
        sex2: ''
      },
      indexTableData: [],
      formLabelWidth: '150px',
      img_user1: '',
      img_user2: '',
      videoJson: [],
      // upload
      uploadLoaclBaseUrl: 'http://127.0.0.1:8888/video/uploadVideoV2',
      uploadBaseUrl: 'http://106.75.81.133:8887/video/uploadVideoV2',
      uploadActionUrl: '',
      fileList: [],
      cos: {},
      Prefix: 'video-identity/',
      process: 0,
      uploadSpeed: 0,
      videoInfoTimer: null,
      imgInfoTimer: null,
      windowLoading: undefined,
      uploading: false,
      // video
      bucketUrl: 'https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/',
      curVideoKey: '',
      dealedVideoKey: '',
      globalSetting: {},
      playTime: '',
      current: '',
      timeSigList: [],
      beforeDealPlayerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
        autoplay: false, // 如果为true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 是否视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          {
            type: 'application/x-mpegURL', // 类型
            withCredentials: false,
            src: 'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-uni4934e7b/c4d93960-5643-11eb-a16f-5b3e54966275.m3u8' // 中央1 M3U8连接
          }
        ],
        poster: 'https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/video-identity/post1.png', // 封面地址
        notSupportedMessage: '视频等待上传中，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true, // 当前时间和持续时间的分隔符
          durationDisplay: true, // 显示持续时间
          remainingTimeDisplay: false, // 是否显示剩余时间功能
          fullscreenToggle: true // 是否显示全屏按钮
        },
        flash: { hls: { withCredentials: false }},
        html5: { hls: { withCredentials: false }}
      },
      afterDealPlayerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
        autoplay: true, // 如果为true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 是否视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [
          {
            type: 'video/mp4', // 类型
            src: '' // url地址
          }
        ],
        poster: 'https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/video-identity/post2.png', // 封面地址
        notSupportedMessage: '视频等待分析中，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true, // 当前时间和持续时间的分隔符
          durationDisplay: true, // 显示持续时间
          remainingTimeDisplay: false, // 是否显示剩余时间功能
          fullscreenToggle: true // 是否显示全屏按钮
        }
      },
      reportData: [],
      reportShow: false
    }
  },
  watch: {
    beforeDealPlayerOptions: {
      handler(val, oldVal) {
        setTimeout(() => {
          this.fixVideoControlBar()
        }, 300)
      },
      deep: true
    }
  },
  created() {
    console.log('query', $('#upload-button'))
    var COS = require('cos-js-sdk-v5')// 存储桶名称，由bucketname-appid 组成，appid必须填入，可以在COS控制台查看存储桶名称。 https://console.cloud.tencent.com/cos5/bucket
    // 初始化实例
    this.cos = new COS({
      SecretId: 'AKIDrXx70cy3qMddMoysVYOzKPDl22lDOIwJ',
      SecretKey: '7yYruue7JGwLTMyJNHuFK8gxxg9LGtGO'
    })
    for (let i = 1; i <= 10; i++) {
      setTimeout(() => {
        this.changeAnim('inAnimation' + '' + i)
        this.changeTextAnim('textAnimation' + '' + i)
      }, 200 * i)
    }
    setTimeout(() => {
      for (let i = 10; i >= 1; i--) {
        setTimeout(() => {
          this.changeAnim('inAnimation' + '' + i)
          this.changeTextAnim('textAnimation' + '' + i)
        }, 200 * (10 - i))
      }
    }, 3600)
    this.uploadBaseUrl = (this.local ? this.uploadLoaclBaseUrl : this.uploadBaseUrl) + '?token=' + getToken()
    this.uploadActionUrl = this.uploadBaseUrl + '&videoName=test.mp4'
  },
  mounted() {
    this.initReadyWebSocket()
    this.initEcharts()
    this.initReport()
  },
  beforeDestroy() {
    this.closeWebSocket()
  },
  destoryed() {
    this.closeWebSocket()
    this.stopInterval()
  },
  methods: {
    // echarts
    initEcharts() {
      console.log(document.getElementById(this.chartId))
      const myChart = this.$echarts.init(document.getElementById(this.chartId), 'dark')
      const echartNode = document.getElementById(this.chartId).firstElementChild
      myChart.setOption(this.confidenceOption)
      const canvasNode = echartNode.firstElementChild
      console.log('echart node', echartNode.childElementCount)
      console.log('cancas node', canvasNode)
      echartNode.setAttribute('style', echartNode.getAttribute('style') + 'border-radius: 20px;' + 'border: 1px;box-shadow: 0px 2px 10px 2px rgb(0 0 0 / 20%);')
      canvasNode.setAttribute('style', canvasNode.getAttribute('style') + 'border-radius: 20px;')
      this.confidenceChart = myChart
    },
    // 点击上传
    buttonUpload() {
      console.log('buttonUpload', $('#file-input'))
      $('#file-input').click()
    },
    onUploadProcess(event, file, fileList) {
      console.log('process event', event)
      // console.log('进度：' + percent + '%; 速度：' + speed + 'Mb/s;')
      this.process = parseInt(event.percent)
    },
    onUploadChange(file, filelist) {
      this.uploadActionUrl = (this.local ? this.uploadLoaclBaseUrl : this.uploadBaseUrl) + '&videoName=' + file.name.split('.')[0] + '-' + this.getFormatDate() + '.' + file.name.split('.')[1]
    },
    onUploadError() {
      this.$message({
        message: '上传失败',
        type: 'warning'
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    beforeUpload(file) {
      console.log(file)
      const isLt10M = file.size / 1024 / 1024 < 500
      if (isLt10M) {
        this.uploading = true
        return true
      }
      this.$message({
        message: '请上传500mb以下的zip文件',
        type: 'warning'
      })
      return false
    },
    handleSuccess({ results, header }) {
      this.$message({
        message: '上传成功',
        type: 'success'
      })
      this.fullscreenLoading = true
      // this.windowLoading = this.$loading({
      //   lock: true,
      //   text: '视频正在处理中，请稍后',
      //   spinner: 'el-icon-loading',
      //   background: 'rgba(0, 0, 0, 0.7)'
      // })
    },
    // websocket
    initReadyWebSocket() {
      var that = this
      if ('WebSocket' in window || 'MozWebSocket' in window) {
        console.log('has websocket')
        that.readyWebSocket = new WebSocket(that.local ? 'ws://127.0.0.1:8888/websocket/1' : 'ws://106.75.81.133:8887/websocket/1')
      } else {
        console.log('当前浏览器 Not support websocket')
        this.$message({
          message: '当前浏览器不支持websocket',
          type: 'error'
        })
        return
      }
      this.readyWebSocket.onerror = function(ev) {
        console.log('WebSocket连接发生错误', ev)
      }
      this.readyWebSocket.onopen = function(ev) {
        console.log('WebSocket连接成功')
      }
      this.readyWebSocket.onclose = function(ev) {
        console.log('init WebSocket连接关闭')
        this.readyWebSocket = null
      }
      this.readyWebSocket.onmessage = function(ev) {
        console.log('ready websocket: ', ev.data)
        console.log('ready websocket: that.fullscreenLoading', that.fullscreenLoading)
        if (ev.data === 'ready') {
          setTimeout(function() {
            that.initWebSocket()
            that.hotVideoUrl = 'http://106.75.81.133:4012/video_feed_heatmap'
            that.boneVideoUrl = 'http://106.75.81.133:4012/video_feed_3d'
            that.mainVideoUrl = 'http://106.75.81.133:4012/video_feed_main_result'
            console.log('**********fullscreen111111', that.fullscreenLoading)
            that.fullscreenLoading = false
            that.uploading = false
            console.log('**********fullscreen22222', that.fullscreenLoading)
          }, 1000)
        } else {
          console.log(JSON.parse(ev.data))
          that.initReport(JSON.parse(ev.data))
        }
      }
    },
    initWebSocket() {
      const that = this
      that.fullscreenLoading = false
      this.fullscreenLoading = false
      console.log('init websocket that', that)
      console.log('init websocket that.windowLoading', that.windowLoading)
      console.log('init websocket this', this)
      console.log('init websocket this.windowLoading', this.windowLoading)
      // var websocket = null;
      // 判断当前浏览器是否支持WebSocket
      if ('WebSocket' in window || 'MozWebSocket' in window) {
        console.log('has websocket')
        this.websockets.actionClassificationWebsocket = new WebSocket(this.actionClassficationWSUrl)
        this.websockets.actionConfidenceWebsocket = new WebSocket(this.actionConfidenceWSUrl)
        this.websockets.poseResultWebsocket = new WebSocket(this.poseResultWSUrl)
      } else {
        console.log('当前浏览器 Not support websocket')
        this.$message({
          message: '当前浏览器不支持websocket',
          type: 'error'
        })
        return
      }
      for (const key in this.websockets) {
        this.websockets[key].onerror = function(ev) {
          console.log('WebSocket连接发生错误', ev)
        }
        this.websockets[key].onopen = function(ev) {
          console.log('WebSocket连接成功')
        }
        this.websockets[key].onclose = function(ev) {
          console.log('WebSocket连接关闭')
          this.websockets[key] = null
        }
      }
      // 接收到消息的回调方法
      this.websockets.actionClassificationWebsocket.onmessage = function(event) {
        console.log('action classfication', event.data)
        that.changeAnim(that.animationMap[event.data][0])
        that.changeTextAnim(that.animationMap[event.data][1])
      }
      this.websockets.actionConfidenceWebsocket.onmessage = function(event) {
        console.log('action confidence', event.data)
        const data = JSON.parse(event.data)
        that.confidenceOption.series[0].data = data
        for (let i = 0; i < 10; i++) {
          that.confidenceOption.series[1].data[i].value = data[i]
        }
        that.confidenceChart.setOption(that.confidenceOption)
      }
      this.websockets.poseResultWebsocket.onmessage = function(event) {
        console.log('pose result', event.data)
        const data = event.data.replace(/'/g, '"')
        console.log(data)
        that.poseResultOld = that.poseResult
        that.poseResult = JSON.parse(data)
        console.log('this.poseResult', this.poseResult)
      }
    },
    // 关闭WebSocket连接
    closeWebSocket() {
      if (this.readyWebSocket != null) {
        console.log('已执行closeWebSocket中关闭websocket操作')
      }
      for (const key in this.websockets) {
        if (this.websockets[key] != null) {
          this.websockets[key].close()
        }
      }
    },
    // video info dialog
    confirmVideoInfo() {
      const that = this
      this.dialogFormVisible = false
      this.curVideoInfo.name1 = this.form.name1
      this.curVideoInfo.sex1 = this.form.sex1
      this.curVideoInfo.name2 = this.form.name2
      this.curVideoInfo.sex2 = this.form.sex2
      setVideoInfo(this.curVideoInfo, getToken()).then((res) => {
        if (res.status == 0) {
          that.$message({
            message: '设置视频角色信息成功',
            type: 'success'
          })
        } else {
          that.$message({
            message: res.msg,
            type: 'error'
          })
        }
      })
    },

    // upload
    uploadChange(events) {
      console.log('uploadchange', events)
      this.uploading = true
      const files = events.currentTarget.files
      const uploadFileList = [...files].map((file) => {
        const path = file.webkitRelativePath || file.name
        return {
          Bucket: 'longma-public-test-1308112438',
          Region: 'ap-beijing',
          Key: this.Prefix + path.split('.')[0] + '-' + this.getFormatDate() + '.' + path.split('.')[1],
          Body: file,
          FileName: path.split('.')[0] + '-' + this.getFormatDate() + '.' + path.split('.')[1]
        }
      })
      console.log('upload file list', uploadFileList)
      const that = this
      // 不通过cos 直接上传到服务器
      if (uploadFileList.length !== 1) {
        that.$message({
          message: '只能选择单个文件上传',
          type: 'warning'
        })
        return
      }
      const uploadV2Param = {
        file: uploadFileList[0].Body
      }
      console.log('upload v2 param', uploadV2Param)
      videoUploadV2(getToken(), uploadFileList[0].FileName, uploadV2Param).then(res => {
        console.log('res')
      })
      // this.cos.uploadFiles({
      //   files: uploadFileList,
      //   SliceSize: 1024 * 1024 * 10, /* 设置大于10MB采用分块上传 */
      //   onProgress: function(info) {
      //     var percent = parseInt(info.percent * 10000) / 100
      //     var speed = parseInt(info.speed / 1024 / 1024 * 100) / 100
      //     console.log('进度：' + percent + '%; 速度：' + speed + 'Mb/s;')
      //     that.process = percent
      //     that.uploadSpeed = speed
      //   },
      //   onFileFinish: function(err, data, options) {
      //     if (err) {
      //       that.$message({
      //         message: '上传失败',
      //         type: 'warning'
      //       })
      //     } else {
      //       that.$message({
      //         message: '上传成功',
      //         type: 'success'
      //       })
      //       that.windowLoading = that.$loading({
      //         lock: true,
      //         text: '视频正在处理中，请稍后',
      //         spinner: 'el-icon-loading',
      //         background: 'rgba(0, 0, 0, 0.7)'
      //       })
      //       that.curVideoKey = options.Key
      //       videoUpload(getToken(), that.curVideoKey).then((res) => {
      //         if (res.status != 0) {
      //           that.windowLoading.close()
      //           that.$message({
      //             message: '视频处理失败',
      //             type: 'error'
      //           })
      //           return
      //         }
      //       })
      //     }
      //   }
      // }, (err, data) => {
      //   const that = this
      //   if (err) {
      //     that.$message({
      //       message: '上传失败',
      //       type: 'warning'
      //     })
      //     return
      //   }
      // })
    },
    formatProcess(percentage) {
      return percentage + '%'
    },

    // 跳跃动画
    changeAnim(t) {
      console.log(t)
      console.log(this[t])
      if (!this[t]) {
        this[t] = true
        setTimeout(() => {
          this[t] = false
        }, 1500)
      }
    },
    changeTextAnim(t) {
      console.log(t)
      console.log(this[t])
      if (!this[t]) {
        this[t] = true
        setTimeout(() => {
          this[t] = false
        }, 1500)
      }
    },
    createTable() {
      this.indexTableData = [
        { 'name': '左臂', 'value_01': 90, 'value_02': 90 },
        { 'name': '右臂', 'value_01': 90, 'value_02': 90 },
        { 'name': '左胯', 'value_01': 90, 'value_02': 90 },
        { 'name': '右胯', 'value_01': 90, 'value_02': 90 },
        { 'name': '左膝', 'value_01': 90, 'value_02': 90 },
        { 'name': '右膝', 'value_01': 90, 'value_02': 90 },
        { 'name': '左踝', 'value_01': 90, 'value_02': 90 },
        { 'name': '右踝', 'value_01': 90, 'value_02': 90 }
      ]
    },
    getDealedVideoUrl() {
      const that = this
      getDealedVideoInfo(getToken()).then((res) => {
        if (res.status === 0) {
          that.$message({
            message: '视频处理成功',
            type: 'success'
          })
          if (this.videoInfoTimer) {
            clearInterval(this.videoInfoTimer)
            this.videoInfoTimer = null
          }
          that.afterDealPlayerOptions.sources[0].src = that.bucketUrl + res.data.videoKey
          that.videoJson = res.data.jsonStr
          this.windowLoading.close()
        }
      })
    },
    createVideoInfoInterval() {
      if (this.videoInfoTimer) {
        clearInterval(this.videoInfoTimer)
        this.videoInfoTimer = null
      }
      const that = this
      this.videoInfoTimer = setInterval(() => {
        that.getDealedVideoUrl()
      }, 2000)
    },
    stopInterval() {
      if (this.videoInfoTimer) {
        clearInterval(this.videoInfoTimer)
        this.videoInfoTimer = null
      }
      if (this.imgInfoTimer) {
        clearInterval(this.imgInfoTimer)
        this.imgInfoTimer = null
      }
    },
    isImgGet() {
      console.log('isImgGet')
      const that = this
      this.imgInfoTimer = setInterval(function() {
        getImgs(getToken()).then((res) => {
          if (res.status === 0) {
            clearInterval(that.imgInfoTimer)
            that.dialogFormVisible = true
            that.img_user1 = that.bucketUrl + res.data.img0
            that.img_user2 = that.bucketUrl + res.data.img1
            that.windowLoading.close()
          }
        })
      }, 3000)
    },
    // video
    dealVideo() {
      this.windowLoading = this.$loading({
        lock: true,
        text: '视频正在处理中，请稍后',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      const data = {
        videoKey: this.curVideoKey
      }
      dealVideo(data, getToken()).then((res) => {
        if (res.status != 0) {
          this.$message({
            message: res.msg,
            type: 'warning'
          })
        } else {
          // 轮询等待 视频处理结果
          this.createVideoInfoInterval()
        }
      })
    },
    // 处理video-control-bar
    fixVideoControlBar() {
      const videoBars = document.querySelectorAll('.vjs-control-bar')
      videoBars[0].style.display = 'flex'
      // videoBars[1].style.display = 'flex'
    },
    // 播放回调
    onPlayerPlay(player) {
      console.log('player play!', player)
    },

    onPlayerPlayAfter(player) {
      console.log('player play2!')
      for (var i = 0; i < this.timeSigList.length; i++) {
        clearInterval(this.timeSigList[i])
      }
      this.timeSigList = []
    },

    // 暂停回调
    onPlayerPause(player) {
      console.log('player pause!', player)
    },

    // 视频播完回调
    onPlayerEnded($event, player) {
      console.log(player)
    },
    // DOM元素上的readyState更改导致播放停止
    onPlayerWaiting(player) {
      const time = localStorage.getItem('cacheTime')
      if (player.cache_.currentTime - Number(time) > 0.1) {
        this.current = Number(time)
        this.playerReadied(player)
      } else {
        this.current = player.cache_.currentTime
      }
    },

    // 已开始播放回调
    onPlayerPlaying($event) {
    },

    // 当播放器在当前播放位置下载数据时触发
    onPlayerLoadeddata($event) {
    },

    // // 当前播放位置发生变化时触发。
    onPlayerTimeupdate(player) {
      this.playTime = player.cache_.currentTime
      const playTime = player.cache_.currentTime
      setTimeout(function() {
        localStorage.setItem('cacheTime', playTime)
      }, 500)

      const time = localStorage.getItem('cacheTime')
      if (player.cache_.currentTime - Number(time) > 2) {
        this.current = Number(time)
        this.playerReadied(player)
      } else {
        this.current = player.cache_.currentTime
      }
    },

    // 媒体的readyState为HAVE_FUTURE_DATA或更高
    onPlayerCanplay(player) {
    },

    // 媒体的readyState为HAVE_ENOUGH_DATA或更高。这意味着可以在不缓冲的情况下播放整个媒体文件。
    onPlayerCanplaythrough(player) {
    },

    // 播放状态改变回调
    playerStateChanged(playerCurrentState) {
    },
    // 将侦听器绑定到组件的就绪状态。与事件监听器的不同之处在于，如果ready事件已经发生，它将立即触发该函数。。
    playerReadied(player) {
      player.currentTime(this.current)
    },

    // 获取日期
    getFormatDate() {
      var date = new Date()
      var seperator1 = '-'
      var year = date.getFullYear()
      var month = date.getMonth() + 1
      var strDate = date.getDate()
      var timeStamp = new Date().getMilliseconds()
      if (month >= 1 && month <= 9) {
        month = '0' + month
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = '0' + strDate
      }
      var currentdate = year + seperator1 + month + seperator1 + strDate + seperator1 + timeStamp
      return currentdate
    },
    initConfident() {
      const pathSymbols = {
        reindeer:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/01.png',
        plane:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/02.png',
        rocket:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/03.png',
        train:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/04.png',
        ship:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/05.png',
        car:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/06.png',
        run:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/07.png',
        walk:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/08.png',
        squatRotate:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/09.png',
        else:
          'image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/10.png'
      }
      const optionConfident = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'none'
          },
          formatter: function(params) {
            return params[0].name + ': ' + params[0].value
          }
        },
        xAxis: {
          data: ['后外点冰跳', '后内点冰跳', '勾手跳', '后内结环跳', '后外结环跳', '阿克塞尔跳', '燕式旋转', '直立旋转', '蹲踞旋转', '非基本姿态旋转'],
          axisTick: { show: false },
          axisLine: { show: false },
          axisLabel: {
            color: '#1296db'
          }
        },
        yAxis: {
          splitLine: { show: true },
          axisTick: { show: true },
          axisLine: { show: false },
          axisLabel: { show: true }
        },
        color: ['#1296db'],
        series: [
          {
            name: 'hill',
            type: 'pictorialBar',
            barCategoryGap: '-130%',
            // symbol: 'path://M0,10 L10,10 L5,0 L0,10 z',
            symbol: 'path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z',
            itemStyle: {
              opacity: 0.5
            },
            emphasis: {
              itemStyle: {
                opacity: 1
              }
            },
            data: [111, 60, 25, 18, 12, 9, 2, 1, 3, 4],
            z: 10
          },
          {
            name: 'glyph',
            type: 'pictorialBar',
            barGap: '-100%',
            symbolPosition: 'end',
            symbolSize: 50,
            symbolOffset: [0, '-120%'],
            data: [
              {
                value: 111,
                symbol: pathSymbols.reindeer,
                symbolSize: [60, 60]
              },
              {
                value: 60,
                symbol: pathSymbols.rocket,
                symbolSize: [60, 60]
              },
              {
                value: 25,
                symbol: pathSymbols.plane,
                symbolSize: [60, 60]
              },
              {
                value: 18,
                symbol: pathSymbols.train,
                symbolSize: [60, 60]
              },
              {
                value: 12,
                symbol: pathSymbols.ship,
                symbolSize: [60, 60]
              },
              {
                value: 9,
                symbol: pathSymbols.car,
                symbolSize: [60, 60]
              },
              {
                value: 2,
                symbol: pathSymbols.run,
                symbolSize: [60, 60]
              },
              {
                value: 1,
                symbol: pathSymbols.walk,
                symbolSize: [60, 60]
              },
              {
                value: 3,
                symbol: pathSymbols.squatRotate,
                symbolSize: [60, 60]
              },
              {
                value: 3,
                symbol: pathSymbols.else,
                symbolSize: [60, 60]
              }
            ]
          }
        ]
      }
      const myChart = this.$echarts.init(document.getElementById('confidence-chart'))
      myChart.setOption(optionConfident)
    },
    initReport(data) {
      const arr = []
      for (const o in data) {
        const actionScore = Object.values(data[o])
        const actionName = Object.keys(data[o])
        console.log('initReport o, actionName, actionInfo', o, actionName, actionScore)
        arr.push({
          'time': o,
          'actionName': actionName[0],
          'score': actionScore[0]
        })
      }
      // this.reportData = [
      //   {'time': '60', 'actionName': 'SSP', 'score': 0.98},
      //   {'time': '120', 'actionName': 'USP', 'score': 0.94},
      //   {'time': '180', 'actionName': 'SSP', 'score': 0.92},
      //   {'time': '240', 'actionName': 'USP', 'score': 0.95},
      //   {'time': '300', 'actionName': 'SSP', 'score': 0.56},
      //   {'time': '360', 'actionName': 'SSP', 'score': 0.78},
      //   {'time': '420', 'actionName': 'USP', 'score': 0.67},
      //   {'time': '300', 'actionName': 'SSP', 'score': 0.56},
      //   {'time': '360', 'actionName': 'SSP', 'score': 0.78},
      //   {'time': '420', 'actionName': 'USP', 'score': 0.67}
      // ]
      this.reportData = arr
      this.reportShow = true
    }
  }
}
</script>

<style lang="scss" scoped>
// upload
.file-select {
  opacity: 0;
  width: 0px;
  height: 0px;
}
// upload prograss
.progress{
  width: 300px;
  margin-left: 50px;
}
// video
.vjs-custom-skin > .video-js .vjs-big-play-button {
    background-color: rgba(0,0,0,0.45);
    font-size: 3.0em;
    /* border-radius: 50%; */
    height: 1.5em !important;
    line-height:1.5em !important;
    margin-top: -1em !important;
}
.show-container {
  display: flex;
  width: 100%;
  align-items: flex-end;
  justify-content: center;
  margin-bottom: 40px;
}
.box-card {
  width: 380px;
  margin: 0 0 0 30px;
  height: 365px;
  &-height {
    height: 15px;
  }
}
.video-box {
  width: 1400px;
  height: 600px;
  display: flex;
  justify-content: space-between;
}
.video-player-before-deal {
  flex: 1 1 600px;
  padding-right: 50px;
}
.video-player-after-deal {
  flex: 1 1 600px;
  // padding-left: 50px;
}
// 图片视频 主视频
.main-video-container {
  width: 640px;
  height:100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  &-img {
    width: 640px;
    height:480px;
    border-radius: 20px;
    border: 1px;
    box-shadow: 0px 2px 10px 2px rgb(0 0 0 / 20%);
  }
}
.sub-video-container {
  width: 700px;
  height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  &-videos{
    display: flex;
    flex-direction: row;
    width: 100%;
    justify-content: space-between;
    &-hot {
      width: 340px;
      height: 250px;
    }
    &-bone {
      width: 340px;
      height: 250px;
    }
    &-img {
      width: 100%;
      height: 100%;
      border-radius: 20px;
      border-radius: 20px;
      box-shadow: 0px 2px 10px 2px rgb(0 0 0 / 20%);
    }
    &-title {
      color: white;
      font-size: 24px;
      font-weight: bold;
      line-height: 40px;
      text-align: center;
    }
  }
  &-charts {
    width: 100%;
    height:300px;
    background: rgba(40, 116, 166,0.3);
    border-radius: 20px;
    box-shadow: 0px 2px 10px 2px rgb(0 0 0 / 20%);
    &-title {
      font-size: 24px;
      font-weight: bold;
      line-height: 40px;
      text-align: center;
      color: white;
    }
    &-chart {
      width: 100%;
      height:260px;
    }
  }
}
// 角度表格
.angle-table{
  border-collapse: collapse;
  font-size: 24px;
  color: white;
}
.angle-title {
  text-align: center;
  width: 160px;
  height: 65px;
  font-size: 20px;
}
.angle-value {
  text-align: center;
  width: 160px;
  height: 65px;
  font-size: 24px;
  font-weight: bold;
}
// 置信度
.ci-container {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-botton:20px;
}
.ci-box {
  width: 1400px;
  height: 370px;
  border-radius: 20px;
  box-shadow: 0px 2px 10px 2px rgb(0 0 0 / 20%);
  &-title {
    font-size: 30px;
    font-weight: bold;
    line-height: 60px;
    color: white;
    text-align: center;
  }
  &-charts {
    width: 100%;
    height: 370px;
  }
}
.dashboard-editor-container {
  padding: 10px 30px;
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
.dashboard-editor-container::before{
  /*background-image: url("../../assets/imgs/back-6.webp");*/
  background-image: radial-gradient(#2E86C1, #D6EAF8);
  background-size: cover;
  width: 100%;
  height: 100%;
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;/*-1 可以当背景*/
  -webkit-filter: blur(3px);
  filter: blur(3px);
  opacity: 0.8;
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}

// 统计数据
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}

.img-container {
  // display: flex;
  // justify-content: space-between;
  width: 1750px;
  // height: 500px;
  margin-top: 20px;
  align-items: center;
  &-label {
    display:flex;
    align-items: center;
    margin-bottom: 20px;
    height: 20px;
    &-user {
      font-size:20px;
      margin-right: 20px;
    }
    &-title {
      font-size:20px;
      margin-right: 10px;
    }
    &-jump {
      width:40px;
      height: 5px;
      border-radius: 1px;
      background: rgb(220, 20, 100, 1);
      margin-right: 20px;
    }
    &-rorate {
      width:40px;
      height: 5px;
      border-radius: 1px;
      background: rgb(255, 255, 0, 1);
    }
  }
}
.img-sexbox {
  width: 1750px;

}
.img-sample {
  width: 1700px;
  height: 70px;
}
.img-sample2 {
  width: 1700px;
  height: 70px;
  // margin-top: -120px;
  margin-top: 2px
}
.dia-box {
  display: flex;
}
.dia-son {
  // justify-content: space-between;
}
.img-style {
  width: 200px;
  height:400px;
}
.text-container {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}
.text-box {
  width: 1400px;
  display: flex;
  align-items: center;
  margin-top: 20px;
  position: relative;
  height: 100px;
}
.input-button {
  // margin-left: 50px;
}
.role-info {
  width: 750px;
  display: flex;
  justify-content: left;
  align-items: center;
}

.spectrum{
  width: 58px;
  height: 20px;
  background: #000000;
  display: inline-block;
  border-radius: 4px;
  transform-origin: bottom;
  margin-right: 6px;
}
/* 设置每一个的颜色和延迟，造成一种此起彼伏的感觉 */
.spectrum1{
  background-color: #D6EAF8 ;
  animation-delay: 0.1s;
}
.spectrum2{
  background-color: #AED6F1;
  animation-delay: 0.2s;
}
.spectrum3{
  background-color: #5DADE2;
  animation-delay: 0.3s;
}
.spectrum4{
  background-color: #3498DB;
  animation-delay: 0.4s;
}
.spectrum5{
  background-color: #2874A6;
  animation-delay: 0.5s;
}
.spectrum6{
  background-color: #148F77;
  animation-delay: 0.6s;
}
.spectrum7{
  background-color: #17A589;
  animation-delay: 0.7s;
}
.spectrum8{
  background-color: #48C9B0;
  animation-delay: 0.8s;
}
.spectrum9{
  background-color: #A3E4D7;
  animation-delay: 0.9s;
}
.spectrum10{
  background-color: #D1F2EB;
  animation-delay: 1s;
}
@keyframes dancing{
  0%{
    transform: scaleY(1);
    opacity: 1;
  }
  30%{
    transform: scaleY(3);
    opacity: 1;
  }
  100%{
    transform: scaleY(1);
    opacity: 1;
  }
}
@keyframes moving{
  0%{
    transform: translate(0px, 0px);
    opacity: 1;
  }
  30%{
    transform: translate(0px, -60px);
    opacity: 1;
  }
  100%{
    transform: translate(0px, 0px);
    opacity: 1;
  }
}
.container {
  display:flex;
}
/* 设置倒影，选择180度，并移位，设置透明度 */
.container.down{
  transform: rotateX(180deg);
  opacity: 0.3;
}
.anim {
  animation: dancing 1.5s ease-in-out 1;
}
.text-anim {
  animation: moving 1.5s ease-in-out 1;
}
.action {
  font-size: 12px;
  color: black;
  text-align: center;
  margin-top: 5px;
  font-weight: bold;
  color:white;
  margin-bottom:5px;
}
.logo {
  position: absolute;
  right: 0;
  height:100px;
  width: 500px;
  display: flex;
  align-items: center;
}
.logo-first {
  width: 250px;
}
.logo-first > img{
  width: 100%;
}
.logo-second {
  width: 250px;
}
.logo-second > img {
  width: 100%;
}
.report {
  width: 100%;
  padding-top:30px;
  &-box {
    width: 1400px;
    // height: 600px;
    overflow: auto;
    margin: auto;
    border-radius: 20px;
  }
}

</style>
