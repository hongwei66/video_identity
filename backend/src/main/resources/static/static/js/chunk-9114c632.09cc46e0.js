(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9114c632"],{"093a":function(e,t,i){"use strict";i.d(t,"a",(function(){return n})),i.d(t,"e",(function(){return a})),i.d(t,"c",(function(){return s})),i.d(t,"d",(function(){return l})),i.d(t,"b",(function(){return c})),i.d(t,"f",(function(){return r}));var o=i("b775");function n(e,t){return Object(o["a"])({url:"/video/dealVideo",method:"post",params:{token:t},data:e})}function a(e,t){return Object(o["a"])({url:"/video/setVideoInfo",method:"post",params:{token:t},data:e})}function s(e){return Object(o["a"])({url:"/video/getDealedVideoInfo",method:"post",params:{token:e}})}function l(e){return Object(o["a"])({url:"/video/getImages",method:"post",params:{token:e}})}function c(e,t){return Object(o["a"])({url:"/video/dealVideoImg",method:"post",params:{token:e},data:{videoKey:t}})}function r(e,t,i){return Object(o["a"])({url:"/video/uploadVideoV2",method:"post",params:{token:e,videoName:t},data:i})}},1:function(e,t){},6724:function(e,t,i){"use strict";i("8d41");var o="@@wavesContext";function n(e,t){function i(i){var o=Object.assign({},t.value),n=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},o),a=n.ele;if(a){a.style.position="relative",a.style.overflow="hidden";var s=a.getBoundingClientRect(),l=a.querySelector(".waves-ripple");switch(l?l.className="waves-ripple":(l=document.createElement("span"),l.className="waves-ripple",l.style.height=l.style.width=Math.max(s.width,s.height)+"px",a.appendChild(l)),n.type){case"center":l.style.top=s.height/2-l.offsetHeight/2+"px",l.style.left=s.width/2-l.offsetWidth/2+"px";break;default:l.style.top=(i.pageY-s.top-l.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",l.style.left=(i.pageX-s.left-l.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return l.style.backgroundColor=n.color,l.className="waves-ripple z-active",!1}}return e[o]?e[o].removeHandle=i:e[o]={removeHandle:i},i}var a={bind:function(e,t){e.addEventListener("click",n(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[o].removeHandle,!1),e.addEventListener("click",n(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[o].removeHandle,!1),e[o]=null,delete e[o]}},s=function(e){e.directive("waves",a)};window.Vue&&(window.waves=a,Vue.use(s)),a.install=s;t["a"]=a},7320:function(e,t,i){"use strict";i.r(t);var o=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{directives:[{name:"loading",rawName:"v-loading",value:e.fullscreenLoading,expression:"fullscreenLoading"}],staticClass:"dashboard-editor-container",attrs:{"element-loading-text":"视频正在处理中，请稍后","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)"}},[i("div",{staticClass:"text-container"},[i("div",{staticClass:"text-box"},[i("el-upload",{staticClass:"upload-demo",attrs:{action:e.uploadActionUrl,"on-preview":e.handlePreview,"before-upload":e.beforeUpload,"on-remove":e.handleRemove,"on-change":e.onUploadChange,"before-remove":e.beforeRemove,"on-progress":e.onUploadProcess,"on-error":e.onUploadError,multiple:"",limit:10,"on-exceed":e.handleExceed,"file-list":e.fileList,"on-success":e.handleSuccess,"show-file-list":!1}},[i("el-button",{attrs:{type:"primary"}},[e._v("点击上传")])],1),i("div",{staticClass:"progress"},[e.uploading?i("el-progress",{attrs:{"text-inside":!0,"stroke-width":25,percentage:e.process,status:"success",format:e.formatProcess}}):e._e()],1),e._m(0)],1)]),i("div",{staticClass:"show-container"},[i("div",{staticClass:"video-box"},[i("div",{staticClass:"main-video-container"},[i("img",{staticClass:"main-video-container-img",attrs:{src:e.mainVideoUrl}}),i("div",{staticClass:"container"},[i("div",[i("div",{staticClass:"action",class:e.textAnimation1?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation1")}}},[e._v("后外点冰跳")]),i("div",{staticClass:"spectrum spectrum1",class:e.inAnimation1?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation1")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation2?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation2")}}},[e._v("后内点冰跳")]),i("div",{staticClass:"spectrum spectrum2",class:e.inAnimation2?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation2")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation3?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation3")}}},[e._v("勾手跳")]),i("div",{staticClass:"spectrum spectrum3",class:e.inAnimation3?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation3")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation4?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation4")}}},[e._v("后内结环跳")]),i("div",{staticClass:"spectrum spectrum4",class:e.inAnimation4?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation4")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation5?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation5")}}},[e._v("后外结环跳")]),i("div",{staticClass:"spectrum spectrum5",class:e.inAnimation5?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation5")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation6?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation6")}}},[e._v("阿克塞尔跳")]),i("div",{staticClass:"spectrum spectrum6",class:e.inAnimation6?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation6")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation7?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation7")}}},[e._v("燕式旋转")]),i("div",{staticClass:"spectrum spectrum7",class:e.inAnimation7?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation7")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation8?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation8")}}},[e._v("直立旋转")]),i("div",{staticClass:"spectrum spectrum8",class:e.inAnimation8?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation8")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation9?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation9")}}},[e._v("蹲踞旋转")]),i("div",{staticClass:"spectrum spectrum9",class:e.inAnimation9?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation9")}}})]),i("div",[i("div",{staticClass:"action",class:e.textAnimation10?"text-anim":"",on:{click:function(t){return e.changeTextAnim("textAnimation10")}}},[e._v("非基本姿态")]),i("div",{staticClass:"spectrum spectrum10",class:e.inAnimation10?"anim":"",on:{click:function(t){return e.changeAnim("inAnimation10")}}})])])]),i("div",{staticClass:"sub-video-container"},[i("div",{staticClass:"sub-video-container-videos"},[i("div",{staticClass:"sub-video-container-videos-hot"},[i("img",{staticClass:"sub-video-container-videos-img",attrs:{src:e.hotVideoUrl}}),i("div",{staticClass:"sub-video-container-videos-title"},[e._v("注意力热图")])]),i("div",{staticClass:"sub-video-container-videos-bone"},[i("img",{staticClass:"sub-video-container-videos-img",attrs:{src:e.boneVideoUrl}}),i("div",{staticClass:"sub-video-container-videos-title"},[e._v("3D建模")])])]),i("div",{staticClass:"sub-video-container-charts"},[i("div",{staticClass:"sub-video-container-charts-chart"},[i("table",{staticClass:"angle-table"},[i("tr",[i("td",{staticClass:"angle-title"},[e._v("左肩膀")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.left_shoulder,"end-val":e.poseResult.left_shoulder,duration:800}}),e._v("° ")],1),i("td",{staticClass:"angle-title"},[e._v("右肩膀")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.right_shoulder,"end-val":e.poseResult.right_shoulder,duration:800}}),e._v("° ")],1)]),i("tr",[i("td",{staticClass:"angle-title"},[e._v("左手臂")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.left_hip,"end-val":e.poseResult.left_hip,duration:800}}),e._v("° ")],1),i("td",{staticClass:"angle-title"},[e._v("右手臂")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.right_hip,"end-val":e.poseResult.right_hip,duration:800}}),e._v("° ")],1)]),i("tr",[i("td",{staticClass:"angle-title"},[e._v("左膝盖")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.left_knee,"end-val":e.poseResult.left_knee,duration:800}}),e._v("° ")],1),i("td",{staticClass:"angle-title"},[e._v("右膝盖")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.right_knee,"end-val":e.poseResult.right_knee,duration:800}}),e._v("° ")],1)]),i("tr",[i("td",{staticClass:"angle-title"},[e._v("左脚踝")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.left_ankle,"end-val":e.poseResult.left_ankle,duration:800}}),e._v("° ")],1),i("td",{staticClass:"angle-title"},[e._v("右脚踝")]),i("td",{staticClass:"angle-value"},[i("count-to",{staticClass:"card-panel-num",attrs:{"start-val":e.poseResultOld.right_ankle,"end-val":e.poseResult.right_ankle,duration:800}}),e._v("° ")],1)])])]),i("div",{staticClass:"sub-video-container-charts-title"},[e._v("关键点角度")])])])])]),e._m(1),e.reportShow?i("div",{staticClass:"report"},[i("div",{staticClass:"report-box"},[i("el-table",{staticStyle:{width:"100%",height:"auto"},attrs:{data:e.reportData,"max-height":"400"}},[i("el-table-column",{attrs:{type:"index",width:"100",align:"center",label:"动作序号"}}),i("el-table-column",{attrs:{label:"时间",align:"center",width:"400"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("i",{staticClass:"el-icon-time"}),i("span",{staticStyle:{"margin-left":"10px"}},[e._v(e._s(t.row.time))])]}}],null,!1,1591012344)}),i("el-table-column",{attrs:{label:"动作名称",align:"center",width:"450"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-tag",{attrs:{size:"medium"}},[e._v(e._s(t.row.actionName))])]}}],null,!1,408967121)}),i("el-table-column",{attrs:{label:"得分",align:"center",width:"450"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-tag",{attrs:{size:"medium"}},[e._v(e._s(t.row.score))])]}}],null,!1,2832678720)})],1)],1)]):e._e()])},n=[function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"logo"},[i("div",{staticClass:"logo-first"},[i("img",{attrs:{src:"https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/logo01.png"}})]),i("div",{staticClass:"logo-second"},[i("img",{attrs:{src:"https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/logo02.png"}})])])},function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"ci-container"},[i("div",{staticClass:"ci-box"},[i("div",{staticClass:"ci-box-charts",attrs:{id:"confidence-chart"}})])])}],a=i("2909"),s=(i("b0c0"),i("ac1f"),i("1276"),i("99af"),i("5319"),i("d81d"),i("a9e3"),i("07ac"),i("b64b"),i("6724")),l=i("d6d3"),c=(i("a151"),i("fda2"),i("451f"),i("093a")),r=i("5f87"),d=i("1157"),m=i.n(d),u=i("ec1b"),p=i.n(u),g=(i("ed08"),{name:"DashboardAdmin",components:{videoPlayer:l["videoPlayer"],CountTo:p.a},directives:{waves:s["a"]},data:function(){return{local:!1,fullscreenLoading:!1,mainVideoUrl:"https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/back-2.png",hotVideoUrl:"https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/hot-poster-1.png",boneVideoUrl:"https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/bone-poster.png",pathSymbols:{reindeer:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/01.png",plane:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/02.png",rocket:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/03.png",train:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/04.png",ship:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/05.png",car:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/06.png",run:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/07.png",walk:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/08.png",squatRotate:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/09.png",else:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/10.png"},confidenceOption:{backgroundColor:{type:"linear",x:0,y:0,x2:0,y2:1,colorStops:[{offset:0,color:"#AED6F1"},{offset:1,color:"#AED6F1"}],global:!1},title:{text:"置信度识别",textStyle:{color:"white",fontWeight:"bold",fontSize:28},padding:[10,10,50,10],left:600},tooltip:{trigger:"axis",axisPointer:{type:"none"},formatter:function(e){return e[0].name+": "+e[0].value}},xAxis:{data:["后外冰点跳","后内冰点跳","勾手跳","后内结环跳","后外结环跳","阿克塞尔跳","燕式旋转","直立旋转","蹲踞旋转","非基本姿态"],axisTick:{show:!0},axisLine:{show:!0},axisLabel:{color:"white",fontWeight:"bold",fontSize:15}},yAxis:{splitLine:{show:!0},axisTick:{show:!0},axisLine:{show:!0},axisLabel:{show:!0}},color:["#3498DB"],series:[{name:"hill",type:"pictorialBar",barCategoryGap:"-130%",symbol:"path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z",itemStyle:{opacity:.5},emphasis:{itemStyle:{opacity:1}},data:[.6,.4,.1,.1,.1,0,0,0,0,0],z:10},{name:"glyph",type:"pictorialBar",barGap:"-100%",symbolPosition:"end",symbolSize:50,symbolOffset:[0,"-120%"],data:[{value:.6,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/01.png",symbolSize:[28,28]},{value:.6,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/02.png",symbolSize:[28,28]},{value:.1,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/03.png",symbolSize:[28,28]},{value:.1,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/04.png",symbolSize:[28,28]},{value:.1,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/05.png",symbolSize:[28,28]},{value:.1,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/06.png",symbolSize:[28,28]},{value:.1,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/07.png",symbolSize:[28,28]},{value:.1,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/08.png",symbolSize:[28,28]},{value:.1,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/09.png",symbolSize:[28,28]},{value:.7,symbol:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/10.png",symbolSize:[28,28]}]}]},confidenceChart:void 0,chartId:"confidence-chart",readyWebSocket:void 0,websockets:{actionClassificationWebsocket:void 0,actionConfidenceWebsocket:void 0,poseResultWebsocket:void 0},actionClassficationWSUrl:"ws://106.75.81.133:4011/ws/action_classification/result_stream",actionConfidenceWSUrl:"ws://106.75.81.133:4011/ws/action_confidence/result_stream",poseResultWSUrl:"ws://106.75.81.133:4011/ws/pose/result_stream",poseResult:{left_shoulder:0,right_shoulder:0,left_hip:0,right_hip:0,left_knee:0,right_knee:0,left_ankle:0,right_ankle:0},poseResultOld:{left_shoulder:0,right_shoulder:0,left_hip:0,right_hip:0,left_knee:0,right_knee:0,left_ankle:0,right_ankle:0},inAnimation1:!1,inAnimation2:!1,inAnimation3:!1,inAnimation4:!1,inAnimation5:!1,inAnimation6:!1,inAnimation7:!1,inAnimation8:!1,inAnimation9:!1,inAnimation10:!1,textAnimation1:!1,textAnimation2:!1,textAnimation3:!1,textAnimation4:!1,textAnimation5:!1,textAnimation6:!1,textAnimation7:!1,textAnimation8:!1,textAnimation9:!1,textAnimation10:!1,animationMap:{"后外点冰跳":["inAnimation1","textAnimation1"],"后内点冰跳":["inAnimation2","textAnimation2"],"勾手跳":["inAnimation3","textAnimation3"],"后内结环跳":["inAnimation4","textAnimation4"],"后外结环跳":["inAnimation5","textAnimation5"],"阿克塞尔跳":["inAnimation6","textAnimation6"],"燕式旋转":["inAnimation7","textAnimation7"],"直立旋转":["inAnimation8","textAnimation8"],"蹲踞旋转":["inAnimation9","textAnimation9"],"非基本姿态旋转":["inAnimation10","textAnimation10"]},dialogFormVisible:!1,curVideoInfo:{name1:"用户1",sex1:"1",name2:"用户2",sex2:"2"},form:{name1:"",sex1:"",name2:"",sex2:""},indexTableData:[],formLabelWidth:"150px",img_user1:"",img_user2:"",videoJson:[],uploadLoaclBaseUrl:"http://127.0.0.1:8888/video/uploadVideoV2",uploadBaseUrl:"http://106.75.81.133:8887/video/uploadVideoV2",uploadActionUrl:"",fileList:[],cos:{},Prefix:"video-identity/",process:0,uploadSpeed:0,videoInfoTimer:null,imgInfoTimer:null,windowLoading:void 0,uploading:!1,bucketUrl:"https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/",curVideoKey:"",dealedVideoKey:"",globalSetting:{},playTime:"",current:"",timeSigList:[],beforeDealPlayerOptions:{playbackRates:[.5,1,1.5,2],autoplay:!1,muted:!1,loop:!1,preload:"auto",language:"zh-CN",aspectRatio:"16:9",fluid:!0,sources:[{type:"application/x-mpegURL",withCredentials:!1,src:"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-uni4934e7b/c4d93960-5643-11eb-a16f-5b3e54966275.m3u8"}],poster:"https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/video-identity/post1.png",notSupportedMessage:"视频等待上传中，请稍后再试",controlBar:{timeDivider:!0,durationDisplay:!0,remainingTimeDisplay:!1,fullscreenToggle:!0},flash:{hls:{withCredentials:!1}},html5:{hls:{withCredentials:!1}}},afterDealPlayerOptions:{playbackRates:[.5,1,1.5,2],autoplay:!0,muted:!1,loop:!1,preload:"auto",language:"zh-CN",aspectRatio:"16:9",fluid:!0,sources:[{type:"video/mp4",src:""}],poster:"https://longma-public-test-1308112438.cos.ap-beijing.myqcloud.com/video-identity/post2.png",notSupportedMessage:"视频等待分析中，请稍后再试",controlBar:{timeDivider:!0,durationDisplay:!0,remainingTimeDisplay:!1,fullscreenToggle:!0}},reportData:[],reportShow:!1}},watch:{beforeDealPlayerOptions:{handler:function(e,t){var i=this;setTimeout((function(){i.fixVideoControlBar()}),300)},deep:!0}},created:function(){var e=this;console.log("query",m()("#upload-button"));var t=i("3438");this.cos=new t({SecretId:"AKIDrXx70cy3qMddMoysVYOzKPDl22lDOIwJ",SecretKey:"7yYruue7JGwLTMyJNHuFK8gxxg9LGtGO"});for(var o=function(t){setTimeout((function(){e.changeAnim("inAnimation"+t),e.changeTextAnim("textAnimation"+t)}),200*t)},n=1;n<=10;n++)o(n);setTimeout((function(){for(var t=function(t){setTimeout((function(){e.changeAnim("inAnimation"+t),e.changeTextAnim("textAnimation"+t)}),200*(10-t))},i=10;i>=1;i--)t(i)}),3600),this.uploadBaseUrl=(this.local?this.uploadLoaclBaseUrl:this.uploadBaseUrl)+"?token="+Object(r["a"])(),this.uploadActionUrl=this.uploadBaseUrl+"&videoName=test.mp4"},mounted:function(){this.initReadyWebSocket(),this.initEcharts(),this.initReport()},beforeDestroy:function(){this.closeWebSocket()},destoryed:function(){this.closeWebSocket(),this.stopInterval()},methods:{initEcharts:function(){console.log(document.getElementById(this.chartId));var e=this.$echarts.init(document.getElementById(this.chartId),"dark"),t=document.getElementById(this.chartId).firstElementChild;e.setOption(this.confidenceOption);var i=t.firstElementChild;console.log("echart node",t.childElementCount),console.log("cancas node",i),t.setAttribute("style",t.getAttribute("style")+"border-radius: 20px;border: 1px;box-shadow: 0px 2px 10px 2px rgb(0 0 0 / 20%);"),i.setAttribute("style",i.getAttribute("style")+"border-radius: 20px;"),this.confidenceChart=e},buttonUpload:function(){console.log("buttonUpload",m()("#file-input")),m()("#file-input").click()},onUploadProcess:function(e,t,i){console.log("process event",e),this.process=parseInt(e.percent)},onUploadChange:function(e,t){this.uploadActionUrl=(this.local?this.uploadLoaclBaseUrl:this.uploadBaseUrl)+"&videoName="+e.name.split(".")[0]+"-"+this.getFormatDate()+"."+e.name.split(".")[1]},onUploadError:function(){this.$message({message:"上传失败",type:"warning"})},handleRemove:function(e,t){console.log(e,t)},handlePreview:function(e){console.log(e)},handleExceed:function(e,t){this.$message.warning("当前限制选择 3 个文件，本次选择了 ".concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},beforeRemove:function(e,t){return this.$confirm("确定移除 ".concat(e.name,"？"))},beforeUpload:function(e){console.log(e);var t=e.size/1024/1024<500;return t?(this.uploading=!0,!0):(this.$message({message:"请上传500mb以下的zip文件",type:"warning"}),!1)},handleSuccess:function(e){e.results,e.header;this.$message({message:"上传成功",type:"success"}),this.fullscreenLoading=!0},initReadyWebSocket:function(){var e=this;if(!("WebSocket"in window)&&!("MozWebSocket"in window))return console.log("当前浏览器 Not support websocket"),void this.$message({message:"当前浏览器不支持websocket",type:"error"});console.log("has websocket"),e.readyWebSocket=new WebSocket(e.local?"ws://127.0.0.1:8888/websocket/1":"ws://106.75.81.133:8887/websocket/1"),this.readyWebSocket.onerror=function(e){console.log("WebSocket连接发生错误",e)},this.readyWebSocket.onopen=function(e){console.log("WebSocket连接成功")},this.readyWebSocket.onclose=function(e){console.log("init WebSocket连接关闭"),this.readyWebSocket=null},this.readyWebSocket.onmessage=function(t){console.log("ready websocket: ",t.data),console.log("ready websocket: that.fullscreenLoading",e.fullscreenLoading),"ready"===t.data?setTimeout((function(){e.initWebSocket(),e.hotVideoUrl="http://106.75.81.133:4012/video_feed_heatmap",e.boneVideoUrl="http://106.75.81.133:4012/video_feed_3d",e.mainVideoUrl="http://106.75.81.133:4012/video_feed_main_result",console.log("**********fullscreen111111",e.fullscreenLoading),e.fullscreenLoading=!1,e.uploading=!1,console.log("**********fullscreen22222",e.fullscreenLoading)}),1e3):(console.log(JSON.parse(t.data)),e.initReport(JSON.parse(t.data)))}},initWebSocket:function(){var e=this,t=this;if(t.fullscreenLoading=!1,this.fullscreenLoading=!1,console.log("init websocket that",t),console.log("init websocket that.windowLoading",t.windowLoading),console.log("init websocket this",this),console.log("init websocket this.windowLoading",this.windowLoading),!("WebSocket"in window)&&!("MozWebSocket"in window))return console.log("当前浏览器 Not support websocket"),void this.$message({message:"当前浏览器不支持websocket",type:"error"});console.log("has websocket"),this.websockets.actionClassificationWebsocket=new WebSocket(this.actionClassficationWSUrl),this.websockets.actionConfidenceWebsocket=new WebSocket(this.actionConfidenceWSUrl),this.websockets.poseResultWebsocket=new WebSocket(this.poseResultWSUrl);var i=function(t){e.websockets[t].onerror=function(e){console.log("WebSocket连接发生错误",e)},e.websockets[t].onopen=function(e){console.log("WebSocket连接成功")},e.websockets[t].onclose=function(e){console.log("WebSocket连接关闭"),this.websockets[t]=null}};for(var o in this.websockets)i(o);this.websockets.actionClassificationWebsocket.onmessage=function(e){console.log("action classfication",e.data),t.changeAnim(t.animationMap[e.data][0]),t.changeTextAnim(t.animationMap[e.data][1])},this.websockets.actionConfidenceWebsocket.onmessage=function(e){console.log("action confidence",e.data);var i=JSON.parse(e.data);t.confidenceOption.series[0].data=i;for(var o=0;o<10;o++)t.confidenceOption.series[1].data[o].value=i[o];t.confidenceChart.setOption(t.confidenceOption)},this.websockets.poseResultWebsocket.onmessage=function(e){console.log("pose result",e.data);var i=e.data.replace(/'/g,'"');console.log(i),t.poseResultOld=t.poseResult,t.poseResult=JSON.parse(i),console.log("this.poseResult",this.poseResult)}},closeWebSocket:function(){for(var e in null!=this.readyWebSocket&&console.log("已执行closeWebSocket中关闭websocket操作"),this.websockets)null!=this.websockets[e]&&this.websockets[e].close()},confirmVideoInfo:function(){var e=this;this.dialogFormVisible=!1,this.curVideoInfo.name1=this.form.name1,this.curVideoInfo.sex1=this.form.sex1,this.curVideoInfo.name2=this.form.name2,this.curVideoInfo.sex2=this.form.sex2,Object(c["e"])(this.curVideoInfo,Object(r["a"])()).then((function(t){0==t.status?e.$message({message:"设置视频角色信息成功",type:"success"}):e.$message({message:t.msg,type:"error"})}))},uploadChange:function(e){var t=this;console.log("uploadchange",e),this.uploading=!0;var i=e.currentTarget.files,o=Object(a["a"])(i).map((function(e){var i=e.webkitRelativePath||e.name;return{Bucket:"longma-public-test-1308112438",Region:"ap-beijing",Key:t.Prefix+i.split(".")[0]+"-"+t.getFormatDate()+"."+i.split(".")[1],Body:e,FileName:i.split(".")[0]+"-"+t.getFormatDate()+"."+i.split(".")[1]}}));console.log("upload file list",o);var n=this;if(1===o.length){var s={file:o[0].Body};console.log("upload v2 param",s),Object(c["f"])(Object(r["a"])(),o[0].FileName,s).then((function(e){console.log("res")}))}else n.$message({message:"只能选择单个文件上传",type:"warning"})},formatProcess:function(e){return e+"%"},changeAnim:function(e){var t=this;console.log(e),console.log(this[e]),this[e]||(this[e]=!0,setTimeout((function(){t[e]=!1}),1500))},changeTextAnim:function(e){var t=this;console.log(e),console.log(this[e]),this[e]||(this[e]=!0,setTimeout((function(){t[e]=!1}),1500))},createTable:function(){this.indexTableData=[{name:"左臂",value_01:90,value_02:90},{name:"右臂",value_01:90,value_02:90},{name:"左胯",value_01:90,value_02:90},{name:"右胯",value_01:90,value_02:90},{name:"左膝",value_01:90,value_02:90},{name:"右膝",value_01:90,value_02:90},{name:"左踝",value_01:90,value_02:90},{name:"右踝",value_01:90,value_02:90}]},getDealedVideoUrl:function(){var e=this,t=this;Object(c["c"])(Object(r["a"])()).then((function(i){0===i.status&&(t.$message({message:"视频处理成功",type:"success"}),e.videoInfoTimer&&(clearInterval(e.videoInfoTimer),e.videoInfoTimer=null),t.afterDealPlayerOptions.sources[0].src=t.bucketUrl+i.data.videoKey,t.videoJson=i.data.jsonStr,e.windowLoading.close())}))},createVideoInfoInterval:function(){this.videoInfoTimer&&(clearInterval(this.videoInfoTimer),this.videoInfoTimer=null);var e=this;this.videoInfoTimer=setInterval((function(){e.getDealedVideoUrl()}),2e3)},stopInterval:function(){this.videoInfoTimer&&(clearInterval(this.videoInfoTimer),this.videoInfoTimer=null),this.imgInfoTimer&&(clearInterval(this.imgInfoTimer),this.imgInfoTimer=null)},isImgGet:function(){console.log("isImgGet");var e=this;this.imgInfoTimer=setInterval((function(){Object(c["d"])(Object(r["a"])()).then((function(t){0===t.status&&(clearInterval(e.imgInfoTimer),e.dialogFormVisible=!0,e.img_user1=e.bucketUrl+t.data.img0,e.img_user2=e.bucketUrl+t.data.img1,e.windowLoading.close())}))}),3e3)},dealVideo:function(){var e=this;this.windowLoading=this.$loading({lock:!0,text:"视频正在处理中，请稍后",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});var t={videoKey:this.curVideoKey};Object(c["a"])(t,Object(r["a"])()).then((function(t){0!=t.status?e.$message({message:t.msg,type:"warning"}):e.createVideoInfoInterval()}))},fixVideoControlBar:function(){var e=document.querySelectorAll(".vjs-control-bar");e[0].style.display="flex"},onPlayerPlay:function(e){console.log("player play!",e)},onPlayerPlayAfter:function(e){console.log("player play2!");for(var t=0;t<this.timeSigList.length;t++)clearInterval(this.timeSigList[t]);this.timeSigList=[]},onPlayerPause:function(e){console.log("player pause!",e)},onPlayerEnded:function(e,t){console.log(t)},onPlayerWaiting:function(e){var t=localStorage.getItem("cacheTime");e.cache_.currentTime-Number(t)>.1?(this.current=Number(t),this.playerReadied(e)):this.current=e.cache_.currentTime},onPlayerPlaying:function(e){},onPlayerLoadeddata:function(e){},onPlayerTimeupdate:function(e){this.playTime=e.cache_.currentTime;var t=e.cache_.currentTime;setTimeout((function(){localStorage.setItem("cacheTime",t)}),500);var i=localStorage.getItem("cacheTime");e.cache_.currentTime-Number(i)>2?(this.current=Number(i),this.playerReadied(e)):this.current=e.cache_.currentTime},onPlayerCanplay:function(e){},onPlayerCanplaythrough:function(e){},playerStateChanged:function(e){},playerReadied:function(e){e.currentTime(this.current)},getFormatDate:function(){var e=new Date,t="-",i=e.getFullYear(),o=e.getMonth()+1,n=e.getDate(),a=(new Date).getMilliseconds();o>=1&&o<=9&&(o="0"+o),n>=0&&n<=9&&(n="0"+n);var s=i+t+o+t+n+t+a;return s},initConfident:function(){var e={reindeer:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/01.png",plane:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/02.png",rocket:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/03.png",train:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/04.png",ship:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/05.png",car:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/06.png",run:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/07.png",walk:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/08.png",squatRotate:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/09.png",else:"image://https://video-skate-1308580196.cos.ap-beijing.myqcloud.com/10.png"},t={tooltip:{trigger:"axis",axisPointer:{type:"none"},formatter:function(e){return e[0].name+": "+e[0].value}},xAxis:{data:["后外点冰跳","后内点冰跳","勾手跳","后内结环跳","后外结环跳","阿克塞尔跳","燕式旋转","直立旋转","蹲踞旋转","非基本姿态旋转"],axisTick:{show:!1},axisLine:{show:!1},axisLabel:{color:"#1296db"}},yAxis:{splitLine:{show:!0},axisTick:{show:!0},axisLine:{show:!1},axisLabel:{show:!0}},color:["#1296db"],series:[{name:"hill",type:"pictorialBar",barCategoryGap:"-130%",symbol:"path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z",itemStyle:{opacity:.5},emphasis:{itemStyle:{opacity:1}},data:[111,60,25,18,12,9,2,1,3,4],z:10},{name:"glyph",type:"pictorialBar",barGap:"-100%",symbolPosition:"end",symbolSize:50,symbolOffset:[0,"-120%"],data:[{value:111,symbol:e.reindeer,symbolSize:[60,60]},{value:60,symbol:e.rocket,symbolSize:[60,60]},{value:25,symbol:e.plane,symbolSize:[60,60]},{value:18,symbol:e.train,symbolSize:[60,60]},{value:12,symbol:e.ship,symbolSize:[60,60]},{value:9,symbol:e.car,symbolSize:[60,60]},{value:2,symbol:e.run,symbolSize:[60,60]},{value:1,symbol:e.walk,symbolSize:[60,60]},{value:3,symbol:e.squatRotate,symbolSize:[60,60]},{value:3,symbol:e.else,symbolSize:[60,60]}]}]},i=this.$echarts.init(document.getElementById("confidence-chart"));i.setOption(t)},initReport:function(e){var t=[];for(var i in e){var o=Object.values(e[i]),n=Object.keys(e[i]);console.log("initReport o, actionName, actionInfo",i,n,o),t.push({time:i,actionName:n[0],score:o[0]})}this.reportData=t,this.reportShow=!0}}}),h=g,v=(i("9648"),i("2877")),f=Object(v["a"])(h,o,n,!1,null,"a4c61646",null);t["default"]=f.exports},"8d41":function(e,t,i){},9648:function(e,t,i){"use strict";i("972f")},"972f":function(e,t,i){}}]);