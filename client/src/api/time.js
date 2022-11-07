export function getTime(itme) {
  return new Date(itme);
}

export function diff(a, b) {
  return a - b;
}

export const time = (item) =>
  diff(new Date().getDate(), getTime(item).getDate());

export const transDate = (item) => {
  const date = item !== undefined ? new Date(item) : new Date();
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
  });
};

export const isToday = (item) => transDate() === transDate(item);
