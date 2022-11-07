import { Cookies } from 'react-cookie';

const cookies = new Cookies();

export const setRefreshToken = (refreshToken) => {
  return cookies.set('Refresh', refreshToken, {
    sameSite: 'strict',
    path: '/',
    maxAge: 420 * 60, // 420분
  });
};

export const getCookieToken = () => {
  return cookies.get('Refresh');
};

export const removeCookieToken = () => {
  return cookies.remove('Refresh', { sameSite: 'strict', path: '/' });
};
