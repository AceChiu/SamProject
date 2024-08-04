import { ElNotification } from 'element-plus'

export const validatePhone = (phone: string) => {
  // const phone = currentUser.phone
  const mobilePattern = /^09\d{8}$/
  const landlinePattern = /^(?:02|03|037|039|04|049|05|06|07|08|089)\d{7,8}$/

  if (!phone) {
    return false
  }
  if (phone.startsWith('09')) {
    if (!mobilePattern.test(phone)) {
      ElNotification({
        title: '電話格式錯誤',
        message: '請輸入正確的手機號碼，例如：09xxxxxxxx',
        type: 'warning'
      })
      return false
    }
  } else if (!landlinePattern.test(phone)) {
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
