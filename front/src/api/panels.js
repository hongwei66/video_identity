import request from '@/utils/request'

export function fetchOnlinePanelList() {
  return request({
    url: '/devices/online',
    method: 'post',
    headers: {
      'content-type': 'application/json'
    },
    data: {}
  })
}

export function updatePanel(data) {
  return request({
    url: '/panels/update_status',
    method: 'post',
    data
  })
}

export function getPanelDistribution(data) {
  return request({
    url: 'accesses/access_cnt_distribution_specific_day',
    method: 'post',
    data
  })
}

export function onlineSmartDevice(data) {
  return request({
    url: 'panels/online_smart',
    method: 'post',
    data
  })
}
