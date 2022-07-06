<template>
  <div class="dashboard-editor-container">
    <div class="text-box">
      <input type="file" class="file-select" @change="uploadChange">
      <p>上传进度：{{ process }}%</p>
      <p>上传速度：{{ uploadSpeed }} Mb/s</p>
      <el-button class="input-button" type="primary" @click="dialogFormVisible = true">输入视频中角色信息</el-button>
      <el-button type="primary" @click="dealVideo()">处理视频</el-button>
    </div>

    <hr>
    <div class="role-info">
      <el-descriptions title="用户信息" :column="4">
        <el-descriptions-item label="用户1">{{ curVideoInfo.name1 }}</el-descriptions-item>
        <el-descriptions-item label="性别">
          <el-tag v-if="curVideoInfo.sex1 == 1" size="small" >
            男
          </el-tag>
          <el-tag v-else >
            女
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="用户2">{{ curVideoInfo.name2 }}</el-descriptions-item>
        <el-descriptions-item label="性别">
          <el-tag v-if="curVideoInfo.sex2 == 1">
            男
          </el-tag>
          <el-tag type="success" v-else size="small">
            女
          </el-tag>
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <hr>
    <div class="show-box">
      <div class="video-player-container">
        <div class="video-player-before-deal">
          <p>处理前</p>
          <!-- <p>视频链接：{{ beforeDealPlayerOptions.sources[0].src }}</p> -->
          <video-player
            id="video"
            ref="videoPlayer"
            class="video-player vjs-custom-skin"
            name="videoPlayer"
            :playsinline="true"
            :global-options="globalSetting"
            :options="beforeDealPlayerOptions"
            @play="onPlayerPlay($event)"
            @pause="onPlayerPause($event)"
            @ended="onPlayerEnded($event)"
            @waiting="onPlayerWaiting($event)"
            @timeupdate="onPlayerTimeupdate($event)"
            @statechanged="playerStateChanged($event)"
            @canplaythrough="onPlayerCanplaythrough($event)"
            @ready="playerReadied"
          />
        </div>
        <div class="video-player-after-deal">
          <p>处理后</p>
          <!-- <p>视频链接：{{ afterDealPlayerOptions.sources[0].src }}</p> -->
          <video-player
            id="video"
            ref="videoPlayer"
            class="video-player vjs-custom-skin"
            name="videoPlayer"
            :playsinline="true"
            :global-options="globalSetting"
            :options="afterDealPlayerOptions"
            @play="onPlayerPlayAfter($event)"
            @pause="onPlayerPause($event)"
            @ended="onPlayerEnded($event)"
            @waiting="onPlayerWaiting($event)"
            @timeupdate="onPlayerTimeupdate($event)"
            @statechanged="playerStateChanged($event)"
            @canplaythrough="onPlayerCanplaythrough($event)"
            @ready="playerReadied"
          />
        </div>
      </div>
      <el-card class="box-card" :body-style="{padding: '0 20px' }" >
        <div slot="header" class="box-card-height">
          <span>运动员各部位运动变化情况</span>
        </div>
        <el-table
          :data="indexTableData"
          max-height="300"
          size="mini"
          style="width: 100%">
          <el-table-column
            prop="name"
            label="名称"
            align="center"
            width="110">
          </el-table-column>
          <el-table-column label="运动角度" align="center">
            <el-table-column
              label="用户1"
              align="center"
              width="110">
              <template slot-scope="scope">
                <el-tag size="small">{{ scope.row.value_01 }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              label="用户2"
              align="center"
              width="110">
              <template slot-scope="scope">
                <el-tag type="success" size="small">{{ scope.row.value_02 }}</el-tag>
              </template>
            </el-table-column>
          </el-table-column>
        </el-table>

      </el-card>
    </div>

    <div class="video-info-dialog-container">
      <!-- Form -->
      <el-dialog title="角色信息" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <div class="dia-box">
            <div class="dia-son">
              <!-- 角色1 -->
              <el-form-item label="角色图片" :label-width="formLabelWidth">
                <img :src="img_user1" class="img-style">
              </el-form-item>
              <el-form-item label="角色1姓名" :label-width="formLabelWidth">
                <el-input v-model="form.name1" autocomplete="off" style="width:200px" />
              </el-form-item>
              <el-form-item label="性别" :label-width="formLabelWidth">
                <el-select v-model="form.sex1" placeholder="请选择性别">
                  <el-option label="男" value="1" />
                  <el-option label="女" value="2" />
                </el-select>
              </el-form-item>
            </div>
            <div class="dia-son">
              <!-- 角色2 -->
              <el-form-item label="角色图片" :label-width="formLabelWidth">
                <img :src="img_user2" class="img-style">
              </el-form-item>
              <el-form-item label="角色2姓名" :label-width="formLabelWidth">
                <el-input v-model="form.name2" autocomplete="off" style="width:200px" />
              </el-form-item>
              <el-form-item label="性别" :label-width="formLabelWidth">
                <el-select v-model="form.sex2" placeholder="请选择性别">
                  <el-option label="男" value="1" />
                  <el-option label="女" value="2" />
                </el-select>
              </el-form-item>
            </div>
          </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="confirmVideoInfo()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div class="img-container">
      <div class="img-container-label">
        <div class="img-container-label-user">用户1</div>
        <div class="img-container-label-title">跳跃：</div>
        <div class="img-container-label-jump"></div>
        <div class="img-container-label-title">旋转：</div>
        <div class="img-container-label-rorate"></div>
      </div>
      <div class="img-sexbox">
        <div id="myChart-1eft-sholder-1" class="img-sample" />
        <div id="myChart-right-sholder-1" class="img-sample2" />
        <div id="myChart-1eft-hip-1" class="img-sample2" />
        <div id="myChart-right-hip-1" class="img-sample2" />
        <div id="myChart-1eft-knee-1" class="img-sample2" />
        <div id="myChart-right-knee-1" class="img-sample2" />
        <div id="myChart-1eft-ankle-1" class="img-sample2" />
        <div id="myChart-right-ankle-1" class="img-sample2" />
      </div>
    </div>
    <hr>
    <div class="img-container">
      <div class="img-container-label">
        <div class="img-container-label-user">用户2</div>
        <div class="img-container-label-title">跳跃：</div>
        <div class="img-container-label-jump"></div>
        <div class="img-container-label-title">旋转：</div>
        <div class="img-container-label-rorate"></div>
      </div>
      <div class="img-sexbox">
        <div id="myChart-1eft-sholder-2" class="img-sample" />
        <div id="myChart-right-sholder-2" class="img-sample2" />
        <div id="myChart-1eft-hip-2" class="img-sample2" />
        <div id="myChart-right-hip-2" class="img-sample2" />
        <div id="myChart-1eft-knee-2" class="img-sample2" />
        <div id="myChart-right-knee-2" class="img-sample2" />
        <div id="myChart-1eft-ankle-2" class="img-sample2" />
        <div id="myChart-right-ankle-2" class="img-sample2" />
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
import { dealVideoImg, dealVideo, setVideoInfo, getDealedVideoInfo, getImgs } from '@/api/video'
import { getToken } from '@/utils/auth' // get token from cookie
export default {
  name: 'DashboardAdmin',
  components: {
    videoPlayer
  },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    }
  },

  data() {
    return {
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
      fullscreenLoading: false,
      // upload
      fileList: [],
      cos: {},
      Prefix: 'video-identity/',
      process: 0,
      uploadSpeed: 0,
      videoInfoTimer: null,
      imgInfoTimer: null,
      windowLoading: null,
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
             type: "application/x-mpegURL",   // 类型
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
        flash: { hls: { withCredentials: false } },
          html5: { hls: { withCredentials: false } },
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
      }
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
    var COS = require('cos-js-sdk-v5')// 存储桶名称，由bucketname-appid 组成，appid必须填入，可以在COS控制台查看存储桶名称。 https://console.cloud.tencent.com/cos5/bucket
    // 初始化实例
    this.cos = new COS({
      SecretId: 'AKIDrXx70cy3qMddMoysVYOzKPDl22lDOIwJ',
      SecretKey: '7yYruue7JGwLTMyJNHuFK8gxxg9LGtGO'
    })
    // const filePath = "temp-file-to-upload" // 本地文件路径
    // cos.sliceUploadFile({
    //     Bucket: 'longma-public-test-1308112438', /* 填入您自己的存储桶，必须字段 */
    //     Region: 'ap-beijing',  /* 存储桶所在地域，例如ap-beijing，必须字段 */
    //     Key: 't.mp4',  /* 存储在桶里的对象键（例如1.jpg，a/b/test.txt），必须字段 */
    //     FilePath: 'C:\Users\CDB\Videos\Captures\video-test.mp4',                /* 必须 */
    //     onTaskReady: function(taskId) {                   /* 非必须 */
    //         console.log(taskId);
    //     },
    //     onHashProgress: function (progressData) {       /* 非必须 */
    //         console.log(JSON.stringify(progressData));
    //     },
    //     onProgress: function (progressData) {           /* 非必须 */
    //         console.log(JSON.stringify(progressData));
    //     }
    // }, function(err, data) {
    //     console.log(err || data);
    // });
  },
  mounted() {
    this.fixVideoControlBar()
    // this.drawOther()
    this.drawInit()
    // this.isImgGet()
    // this.createTable()
  },
  destoryed() {
    this.stopInterval()
  },
  methods: {
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
      const files = events.currentTarget.files
      const uploadFileList = [...files].map((file) => {
        const path = file.webkitRelativePath || file.name
        return {
          Bucket: 'longma-public-test-1308112438',
          Region: 'ap-beijing',
          Key: this.Prefix + path.split('.')[0] + '-' + this.getFormatDate() + '.' + path.split('.')[1],
          Body: file
        }
      })
      const that = this
      this.cos.uploadFiles({
        files: uploadFileList,
        SliceSize: 1024 * 1024 * 10, /* 设置大于10MB采用分块上传 */
        onProgress: function(info) {
          var percent = parseInt(info.percent * 10000) / 100
          var speed = parseInt(info.speed / 1024 / 1024 * 100) / 100
          console.log('进度：' + percent + '%; 速度：' + speed + 'Mb/s;')
          that.process = percent
          that.uploadSpeed = speed
        },
        onFileFinish: function(err, data, options) {
          if (err) {
            that.$message({
              message: '上传失败',
              type: 'warning'
            })
          } else {
            that.$message({
              message: '上传成功',
              type: 'success'
            })
            that.windowLoading = that.$loading({
              lock: true,
              text: '视频正在处理中，请稍后',
              spinner: 'el-icon-loading',
              background: 'rgba(0, 0, 0, 0.7)'
            })
            that.curVideoKey = options.Key

            that.beforeDealPlayerOptions.sources[0].src = that.bucketUrl + that.curVideoKey
            dealVideoImg(getToken(), that.curVideoKey).then((res) => {
              if (res.status == 0) {
                that.$message({
                  message: '载入成功',
                  type: 'success'
                })
                that.isImgGet()
              }
            })
          }
        }
      }, (err, data) => {
        const that = this
        if (err) {
          that.$message({
            message: '上传失败',
            type: 'warning'
          })
          return
        }
      })
    },
    createTable() {
      this.indexTableData = [
        {"name": "左臂", "value_01": 90, "value_02": 90},
        {"name": "右臂", "value_01": 90, "value_02": 90},
        {"name": "左胯", "value_01": 90, "value_02": 90},
        {"name": "右胯", "value_01": 90, "value_02": 90},
        {"name": "左膝", "value_01": 90, "value_02": 90},
        {"name": "右膝", "value_01": 90, "value_02": 90},
        {"name": "左踝", "value_01": 90, "value_02": 90},
        {"name": "右踝", "value_01": 90, "value_02": 90},
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
    drawTable() {
      var outputJson = JSON.parse(this.videoJson)
      var tableLength = outputJson[0]['angle']['left_shoulder'].length
      var cnt = 0
      var cntMap = {
        0: 'left_shoulder',
        1: 'right_shoulder',
        2: 'left_hip',
        3: 'right_hip',
        4: 'left_knee',
        5: 'right_knee',
        6: 'left_ankle',
        7: 'right_ankle'
      }
      var that = this
      var timeTabSig = setInterval(function() {
        if (cnt < (tableLength - 5)) {
          for(var j = 0; j<8; j++) {
            that.indexTableData[j]['value_01'] = outputJson[0]['angle'][cntMap[j]][cnt]
            that.indexTableData[j]['value_02'] = outputJson[1]['angle'][cntMap[j]][cnt]
          }
          cnt += 5
        }
        else {
          clearInterval(timeTabSig)
        }
      }, 200)
      this.timeSigList.push(timeTabSig)
    },
    drawInit() {
      this.initOption('myChart-1eft-sholder-1', [], '左肩运动曲线示意图', '#3F72BF')
      this.initOption('myChart-right-sholder-1', [], '右肩运动曲线示意图', '#335B99')
      this.initOption('myChart-1eft-hip-1', [], '左跨运动曲线示意图', '#4BBBC7')
      this.initOption('myChart-right-hip-1', [], '右胯运动曲线示意图', '#4BC796')
      this.initOption('myChart-1eft-knee-1', [], '左膝运动曲线示意图', '#C7574B')
      this.initOption('myChart-right-knee-1', [], '右膝运动曲线示意图', '#C7574B')
      this.initOption('myChart-1eft-ankle-1', [], '左踝运动曲线示意图', '#FFD700')
      this.initOption('myChart-right-ankle-1', [], '右踝运动曲线示意图', '#8B4513')

      this.initOption('myChart-1eft-sholder-2', [], '左肩运动曲线示意图', '#3F72BF')
      this.initOption('myChart-right-sholder-2', [], '右肩运动曲线示意图', '#335B99')
      this.initOption('myChart-1eft-hip-2', [], '左跨运动曲线示意图', '#4BBBC7')
      this.initOption('myChart-right-hip-2', [], '右胯运动曲线示意图', '#4BC796')
      this.initOption('myChart-1eft-knee-2', [], '左膝运动曲线示意图', '#C7574B')
      this.initOption('myChart-right-knee-2', [], '右膝运动曲线示意图', '#C7574B')
      this.initOption('myChart-1eft-ankle-2', [], '左踝运动曲线示意图', '#FFD700')
      this.initOption('myChart-right-ankle-2', [], '右踝运动曲线示意图', '#8B4513')
      // this.drawOther()
    },
    drawOther() {
      var outputJson = JSON.parse(this.videoJson)
      // var outputJson = [{ 'idx': 0, 'name': '张三', 'sex': '男', 'angle': { 'left_shoulder': [
      //   35, 35, 36, 37, 29, 31, 33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   ],
      //   'left_hip': [
      //   35, 35, 36, 37, 29, 31, 33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   ],
      //   'left_knee': [
      //   35, 35, 36, 37, 29, 31, 33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   ],
      //   'left_ankle': [
      //   35, 35, 36, 37, 29, 31, 33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   33, 30, 30, 32, 32, 34, 34, 34, 31, 27, 29, 28, 31, 31, 34, 34, 39, 42, 45, 50,
      //   ] },
      //   'action': {
      //     'jump': [true, true, true, true,true, true, true, true, false,false, false, false, false, false,false, false, false, false, false,false,
      //             true, false, false, false,false, false, false, false, false,false, false, false, false, false,false, false, false, false, false,false,
      //             true, false, false, false,false, false, false, false, false,false, false, false, false, false,false, false, false, false, false,false,
      //             true, false, false, false,false, false, false, false, false,false, false, false, false, false,false, false, false, false, false,false,
      //             true, false, false, false,false, false, false, false, false,false, false, false, false, false,false, false, false, false, false,false
      //     ],
      //     'lift': [false, false, false, false,false, false, false, false, false, false,true, false, false, false, false, false,false, false, false, false,
      //     false, false, false, false,false, false, false, false, false, false,true, false, false, false, false, false,false, false, false, false,
      //     false, false, false, false,false, false, false, false, false, false,true, false, false, false, false, false,false, false, false, false,
      //     false, false, false, false,false, false, false, false, false, false,true, false, false, false, false, false,false, false, false, false,
      //     false, false, false, false,false, false, false, false, false, false,true, false, false, false, false, false,false, false, false, false
      //     ],
      //     'rotate': [false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, true,
      //     false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, true,
      //     false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, true,
      //     false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, true,
      //     false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, true],
      //   }
      //   }]

      this.defineOption('myChart-1eft-sholder-1', outputJson[0]['angle']['left_shoulder'], outputJson[0]['action'], '左肩运动曲线示意图', '#3F72BF')
      this.defineOption('myChart-right-sholder-1', outputJson[0]['angle']['right_shoulder'], outputJson[0]['action'], '右肩运动曲线示意图', '#335B99')
      this.defineOption('myChart-1eft-hip-1', outputJson[0]['angle']['left_hip'], outputJson[0]['action'], '左胯运动曲线示意图', '#4BBBC7')
      this.defineOption('myChart-right-hip-1', outputJson[0]['angle']['right_hip'], outputJson[0]['action'], '右胯运动曲线示意图', '#4BC796')
      this.defineOption('myChart-1eft-knee-1', outputJson[0]['angle']['left_knee'],  outputJson[0]['action'],'左膝运动曲线示意图', '#C7574B')
      this.defineOption('myChart-right-knee-1', outputJson[0]['angle']['right_knee'], outputJson[0]['action'], '右膝运动曲线示意图', '#C7574B')
      this.defineOption('myChart-1eft-ankle-1', outputJson[0]['angle']['left_ankle'],  outputJson[0]['action'],'左踝运动曲线示意图', '#FFD700')
      this.defineOption('myChart-right-ankle-1', outputJson[0]['angle']['right_ankle'],  outputJson[0]['action'],'右踝运动曲线示意图', '#8B4513')


      this.defineOption('myChart-1eft-sholder-2', outputJson[1]['angle']['left_shoulder'],  outputJson[1]['action'], '左肩运动曲线示意图', '#3F72BF')
      this.defineOption('myChart-right-sholder-2', outputJson[1]['angle']['right_shoulder'],  outputJson[1]['action'], '右肩运动曲线示意图', '#335B99')
      this.defineOption('myChart-1eft-hip-2', outputJson[1]['angle']['left_hip'],  outputJson[1]['action'], '左胯运动曲线示意图', '#4BBBC7')
      this.defineOption('myChart-right-hip-2', outputJson[1]['angle']['right_hip'], outputJson[1]['action'], '右胯运动曲线示意图', '#4BC796')
      this.defineOption('myChart-1eft-knee-2', outputJson[1]['angle']['left_knee'], outputJson[1]['action'], '左膝运动曲线示意图', '#C7574B')
      this.defineOption('myChart-right-knee-2', outputJson[1]['angle']['right_knee'], outputJson[1]['action'], '右膝运动曲线示意图', '#C7574B')
      this.defineOption('myChart-1eft-ankle-2', outputJson[1]['angle']['left_ankle'], outputJson[1]['action'], '左踝运动曲线示意图', '#FFD700')
      this.defineOption('myChart-right-ankle-2', outputJson[1]['angle']['right_ankle'], outputJson[1]['action'], '右踝运动曲线示意图', '#8B4513')
    },
    initOption(chartId, chartInitData, chartName, lineColor) {
      var chartData = []
      var chartLabel = []
      var chartSize = 200
      var chartHead = 0
      var chartTail = chartSize
      // chartData = chartInitData.slice(chartHead, chartTail)
      for (var i = 0; i < chartSize; i++) {
        chartLabel.push(i)
        chartData.push(90)
        // chartData.push(Math.random())
      }
      var option = {
        title: {
          text: chartName,
          textStyle: {
            fontSize: 14,
          },
          y: 'center'
        },
        grid: {
          x: 170,
          x2: 10,
          y: 20,
          y2:10
        },
        xAxis: {
          type: 'category',
          data: chartLabel,
          axisLine:{ show:false },
          axisLabel:{ show: false },
          axisTick: { show:false },
        },
        yAxis: {
          type: 'value',
          axisLine:{ show:false },
          axisLabel:{ show: false },
          axisTick: { show:true },
          max: 200,
          min: 0
        },
        series: [
          {
            data: chartData,
            name: 11,
            type: 'line',
            lineStyle:{
              color: lineColor
            },
            symbol: 'none',
          }
        ]
      }
      const myChart = this.$echarts.init(document.getElementById(chartId), 'dark')
      myChart.setOption(option)
    },
    defineOption(chartId, chartInitData, chartActions, chartName, lineColor) {
      var chartData = []
      var chartLabel = []
      var chartSize = 200
      var chartHead = 0
      var chartTail = chartSize
      var actionList = []
      // chartData = chartInitData.slice(chartHead, chartTail)
      for (var i = 0; i < chartSize; i++) {
        chartLabel.push(i)
        chartData.push(90)
        // chartData.push(Math.random())
      }
      chartInitData = chartData.concat(chartInitData)
      var option = {
        title: {
          text: chartName,
          textStyle: {
            fontSize: 14,
          },
          y: 'center'
        },
        grid: {
          x: 170,
          x2: 10,
          y: 20,
          y2:10
        },
        xAxis: {
          type: 'category',
          data: chartLabel,
          axisLine:{ show:false },
          axisLabel:{ show: false },
          axisTick: { show:false },
        },
        yAxis: {
          type: 'value',
          axisLine:{ show:false },
          axisLabel:{ show: false },
          axisTick: { show:true },
          max: 200,
          min: 0
        },
        series: [
          {
            data: chartData,
            name: 11,
            type: 'line',
            lineStyle:{
              color: lineColor
            },
            symbol: 'none',
          }
        ]
      }
      const myChart = this.$echarts.init(document.getElementById(chartId))
      myChart.setOption(option)
      var intervarCnt = 0
      var timeSig = setInterval(function() {
        if (chartSize > (chartInitData.length - 5)) {
          clearInterval(timeSig)
        }
        const stopSig = chartSize
        for (var i = stopSig; i < stopSig + 5; i++) {
          // chartData.shift()
          // chartData.push(Math.random())
          chartLabel.shift()
          let tmpIndex = i + ''
          chartLabel.push(tmpIndex)
          chartSize++
          if (chartName === '左肩运动曲线示意图') {
            if (chartActions['jump'][(i-stopSig) + intervarCnt*5]) {
              actionList.push({
                value: '',
                coord: [tmpIndex, 200],
                itemStyle: {
                  color: 	'rgb(220, 20, 100, 1)'
                },
                symbolSize: [10,4],
                symbol: 'rect'
              })
            }
            else {
              actionList.push({
                value: '',
                coord: [tmpIndex, 200],
                itemStyle: {
                  color: 	'rgb(220, 20, 100, 0.2)'
                },
                symbolSize: [8,4],
                symbol: 'rect'
              })
            }
            // if (chartActions['lift'][(i-stopSig)+ intervarCnt*5]) {
            //   actionList.push({
            //     value: '',
            //     coord: [tmpIndex, 180],
            //     itemStyle: {
            //       color: 'rgb(0, 191, 255, 1)'
            //     },
            //     symbolSize: [10,4],
            //     symbol: 'rect'
            //   })
            // }
            // else {
            //   actionList.push({
            //     value: '',
            //     coord: [tmpIndex, 180],
            //     itemStyle: {
            //       color: 'rgb(0, 191, 255, 0.2)'
            //     },
            //     symbolSize: [8,4],
            //     symbol: 'rect'
            //   })
            // }
            if (chartActions['rotate'][(i-stopSig) + intervarCnt*5]) {
              actionList.push({
                value: '',
                coord: [tmpIndex, 180],
                itemStyle: {
                  color: 'rgb(255, 255, 0, 1)'
                },
                symbolSize: [10,4],
                symbol: 'rect'
              })
            }
            else {
              actionList.push({
                value: '',
                coord: [tmpIndex, 180],
                itemStyle: {
                  color: 'rgb(255, 255, 0, 0.2)'
                },
                symbolSize: [8,4],
                symbol: 'rect'
              })
            }
          }
          else {
            actionList = []
          }

        }
        intervarCnt += 1

        chartHead = chartHead + 5
        chartTail += chartTail + 5
        chartData = chartInitData.slice(chartHead, chartTail)
        myChart.setOption(
          {
            title: {
              text: chartName,
              textStyle: {
                fontSize: 14,
              }
            },
            grid: {
              x: 170,
              x2: 10,
              y: 20,
              y2: 10
            },
            xAxis: {
              type: 'category',
              data: chartLabel,
              axisLine:{ show:false },
              axisLabel:{ show: false },
              axisTick: { show:false },
            },
            yAxis: {
              type: 'value',
              axisLine:{ show:false },
              axisLabel:{ show: false },
              axisTick: { show:true },
              max: 200,
              min: 0
            },
            series: [
              {
                data: chartData,
                name: 11,
                type: 'line',
                lineStyle:{
                  color: lineColor
                },
                symbol: 'none',
                markPoint: {
                  data: actionList
                }
              }
            ]
          }
        )
      }, 200)
      this.timeSigList.push(timeSig)
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
      videoBars[1].style.display = 'flex'
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
      this.drawOther()
      this.drawTable()
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
      if (month >= 1 && month <= 9) {
        month = '0' + month
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = '0' + strDate
      }
      var currentdate = year + seperator1 + month + seperator1 + strDate
      return currentdate
    },

    handleRemove() {
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files) {
    },
    beforeRemove(file) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    beforeUpload(file) {
      console.log(file)

      const isLt10M = file.size / 1024 / 1024 < 10

      if (isLt10M) {
        return true
      }

      this.$message({
        message: '请上传10mb以下的zip文件',
        type: 'warning'
      })
      return false
    },
    handleSuccess({ results, header }) {
    },

    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}`
        ? 'ascending'
        : sort === `-${key}`
          ? 'descending'
          : ''
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
      }
    }
  }
}
</script>

<style lang="scss" scoped>
// video
.vjs-custom-skin > .video-js .vjs-big-play-button {
    background-color: rgba(0,0,0,0.45);
    font-size: 3.0em;
    /* border-radius: 50%; */
    height: 1.5em !important;
    line-height:1.5em !important;
    margin-top: -1em !important;
}
.show-box {
  display: flex;
  width: 100%;
  align-items: flex-end;
}
.box-card {
  width: 380px;
  margin: 0 0 0 30px;
  height: 365px;
  &-height {
    height: 15px;
  }
}
.video-player-container {
  width: 1400px;
  height: 410px;
  display: flex;
}
.video-player-before-deal {
  flex: 1 1 600px;
  padding-right: 50px;
}
.video-player-after-deal {
  flex: 1 1 600px;
  padding-left: 50px;
}

.dashboard-editor-container {
  padding: 10px 30px;
  // background-color: rgb(240, 242, 245);
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
.text-box {
  display: flex;
  align-items: center;
}
.input-button {
  margin-left: 50px;
}
.role-info {
  width: 750px;
  display: flex;
  justify-content: left;
  align-items: center;
}
</style>
