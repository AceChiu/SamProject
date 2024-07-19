import { authAxiosInstance } from '../axiosInstance'
import user from '../interface/user'
const url = 'api/user-profile/'

export const getCurrentUser = (email: string) => {
  return authAxiosInstance.get(`${url}` + 'find/email/' + email)
}

export const postUpdateUser = (user: user) => {
  return authAxiosInstance.post(
    `${url}` + 'update',
    JSON.stringify({
      "id": user.id,
      "username": user.username,
      "birthday": user.birthday,
      "phone": user.phone,
      "address": user.address
    })
  )
}
