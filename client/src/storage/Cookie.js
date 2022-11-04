import { Cookies } from 'react-cookie';

const cookies = new Cookies();

export const setRefreshToken = (refreshToken) => {
  return cookies.set('refresh', refreshToken, {
    sameSite: 'strict',
    path: '/',
    maxAge: 420 * 60, // 420분
    // httpOnly: true,
  });
};

export const getCookieToken = () => {
  return cookies.get('refresh');
};

export const removeCookieToken = () => {
  return cookies.remove('refresh', { sameSite: 'strict', path: '/' });
};
