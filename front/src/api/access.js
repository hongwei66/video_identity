import request from '@/utils/request'

export function getConsumptionOfSpecificDay(data) {
  return request({
    url: 'accesses/consumption_specific_day',
    method: 'post',
    data: data
  })
}

export function getAccessCntDistributionOfSpecificDay(data) {
  return request({
    url: 'accesses/access_cnt_distribution_specific_day',
    method: 'post',
    data: data
  })
}

export function getAccessCntOfSpecificDay(data) {
  return request({
    url: 'accesses/access_cnt_specific_day',
    method: 'post',
    data: data
  })
}

export function getConsumptionDistributionOfSpecificDay(data) {
  return request({
    url: 'accesses/consumption_day_distribution',
    method: 'post',
    data: data
  })
}
