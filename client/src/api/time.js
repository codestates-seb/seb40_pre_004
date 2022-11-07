export function getTime(itme) {
  return new Date(itme);
}

export function diff(a, b) {
  return a - b;
}

export const transDate = (item) =>
  new Date(item).toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  });
