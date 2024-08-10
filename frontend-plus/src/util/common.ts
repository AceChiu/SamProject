import { ElNotification } from 'element-plus'

export const validatePhone = (phone: string) => {
  const landlinePattern = /^(?:02|03|037|039|04|049|05|06|07|08|089)\d{7,8}$/

  if (!phone) {
    return false
  }

  if (!landlinePattern.test(phone)) {
    ElNotification({
      title: '電話格式錯誤',
      message: '請輸入正確的市話號碼，例如：02xxxxxxxx',
      type: 'warning'
    })
    return false
  }
  ElNotification({
    title: '電話格式正確',
    message: '電話格式正確',
    type: 'success'
  })
  return true
}

export const validateMobile = (mobile: string) => {
  const mobilePattern = /^09\d{8}$/

  if (!mobile) {
    return false
  }
  if (!mobile.startsWith('09') || !mobilePattern.test(mobile)) {
    ElNotification({
      title: '手機格式錯誤',
      message: '請輸入正確的手機號碼，例如：09xxxxxxxx',
      type: 'warning',
      offset: 60
    })
    return false
  } else {
    ElNotification({
      title: '手機格式正確',
      message: '手機格式正確',
      type: 'success',
      offset: 60
    })
    return true
  }
}

export const validateAddress = (address: string) => {
  const addressPattern = /^[\u4e00-\u9fa5a-zA-Z0-9\s,.'-]{2,}$/

  if (!addressPattern.test(address)) {
    ElNotification({
      title: '地址格式錯誤',
      message: '請輸入正確的地址',
      type: 'warning',
      offset: 120
    })
    return false
  }
  ElNotification({
    title: '地址格式正確',
    message: '地址格式正確',
    type: 'success',
    offset: 120
  })
  return true
}
