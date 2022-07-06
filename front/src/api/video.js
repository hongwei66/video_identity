import request from '@/utils/request'

export function dealVideo(data, token) {
  return request({
    url: '/video/dealVideo',
    method: 'post',
    params: { token },
    data
  })
}

export function setVideoInfo(data, token) {
  return request({
    url: '/video/setVideoInfo',
    method: 'post',
    params: { token },
    data
  })
}

export function getDealedVideoInfo(token) {
  return request({
    url: '/video/getDealedVideoInfo',
    method: 'post',
    params: { token }
  })
}

export function getImgs(token) {
  return request({
    url: '/video/getImages',
    method: 'post',
    params: { token }
  })
}

export function dealVideoImg(token, videoKey) {
  return request({
    url: '/video/dealVideoImg',
    method: 'post',
    params: { token },
    data: {
      'videoKey': videoKey
    }
  })
}

// 花滑2.0
/**
 * 通知后端视频地址
 * @param token
 * @param videoKey
 * @returns {AxiosPromise}
 */
export function videoUpload(token, videoKey) {
  return request({
    url: '/video/uploadVideo',
    method: 'post',
    params: { token },
    data: {
      'videoKey': videoKey
    }
  })
}

/**
 * 通知后端视频地址
 * @param token
 * @param videoKey
 * @returns {AxiosPromise}
 */
export function videoUploadV2(token, videoName, data) {
  return request({
    url: '/video/uploadVideoV2',
    method: 'post',
    params: { token, videoName },
    data
  })
}

