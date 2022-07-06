import request from '@/utils/request'

export function getSrcList() {
  return request({
    url: '/sqlstat/getSrcList',
    method: 'post',
    data: {}
  })
}

export function download(data) {
  return request({
    url: '/sqlstat/download',
    method: 'get',
    params: data
  })
}

export function getRuleByType(data) {
  return request({
    url: '/sqlstat/getRuleByType',
    method: 'post',
    params: { data }
  })
}

export function scanByCommonRules(data) {
  return request({
    url: '/sqlstat/scanByCommonRules',
    method: 'post',
    data: data
  })
}
