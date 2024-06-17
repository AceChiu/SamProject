import { authAxiosInstance } from '../axiosInstance'
import { IUser } from '../interface/user'
const url = '/user-profile/'

export const getCurrentUser = (email: string) => {
  return authAxiosInstance.get(`${url}` + 'find/email/' + email)
}

export const postCreateUser = (user: IUser) => {
  return authAxiosInstance.post(
    `${url}` + 'create',
    JSON.stringify({
      "email": user.email,
      "familyName": user.familyName,
      "givenName": user.givenName,
      "name": user.name
    })
  )
}
