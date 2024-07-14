import { authAxiosInstance } from '../axiosInstance'
import user from '../interface/user'
const url = 'api/user-profile/'

export const getCurrentUser = (email: string) => {
  return authAxiosInstance.get(`${url}` + 'find/email/' + email)
}

export const postCreateUser = (user: user) => {
  return authAxiosInstance.post(
    `${url}` + 'create',
    JSON.stringify({
      "email": user.email,
      "familyName": user.familyName,
      "givenName": user.givenName,
      "name": user.name,
      "googleId": user.googleId
    })
  )
}
