export function getTime(itme) {
  return new Date(itme);
}

export function diff(a, b) {
  return a - b;
}

export const transDate = (item) => {
  const date = item !== undefined ? new Date(item) : new Date();
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  });
};

export const isToday = (item) => transDate() === transDate(item);
