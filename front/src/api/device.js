import request from '@/utils/request'

export function getOnlineNum() {
  return request({
    url: '/devices/online_num',
    method: 'post',
    data: {}
  })
}

export function uploadfile(data) {
  return request({
    headers: { 'Content-Type': 'multipart/form-data;charset=utf-8' },
    url: '/sqlstat/upload',
    method: 'post',
    data: data
  })
}

export function getDeviceDistribution() {
  return request({
    url: '/devices/type_distribution',
    method: 'post',
    data: {}
  })
}
