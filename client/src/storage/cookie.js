import { Cookies } from 'react-cookie';

const cookies = new Cookies();

export const setRefreshToken = (refreshToken) => {
  const expireDate = new Date();
  expireDate.setDate(expireDate.getDate() + 7);

  return cookies.set('refresh_token', refreshToken, {
    sameSite: 'strict',
    path: '/',
    expires: expireDate,
  });
};

export const getCookieToken = () => {
  return cookies.get('refresh_token');
};

export const removeCookieToken = () => {
  return cookies.remove('refresh_token', { sameSite: 'strict', path: '/' });
};
