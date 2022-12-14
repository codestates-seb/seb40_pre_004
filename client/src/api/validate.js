export const validateString = (string, regex) => {
  return regex.test(string);
};

export const validateDisplayName = (displayName, email) => {
  if (displayName === email) return 'sameWithEmail';

  return 'valid';
};

export const validateEmail = (email) => {
  const emailRegex =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  if (email.length === 0) return 'empty';
  if (!validateString(email, emailRegex)) return 'invalid';

  return 'valid';
};

export const validatePasswordForRegister = (password) => {
  const hasNumberRegex = /(?=.*[0-9])/;
  const hasLetterRegex = /(?=.*[a-zA-Z])/;
  const atLeastEightLettersRegex = /[a-zA-Z0-9`~!@#$%^&*()\-_=+]{8,}/;

  if (password.length === 0) return 'empty';
  if (
    !validateString(password, hasNumberRegex) &&
    !validateString(password, hasLetterRegex)
  )
    return 'missingNumberAndLetter';
  if (!validateString(password, hasNumberRegex)) return 'missingNumber';
  if (!validateString(password, hasLetterRegex)) return 'missingLetter';
  if (!validateString(password, atLeastEightLettersRegex)) {
    return 'short';
  }

  return 'valid';
};

export const validatePasswordForLogin = (password) => {
  if (password.length === 0) return 'empty';

  return 'valid';
};

export const validateIsHuman = (isHuman) => {
  if (isHuman) return 'valid';

  return 'invalid';
};

export const validateTitleForNewQ = (title) => {
  const letterLengthRegex = /^[0-9가-힣a-zA-Z].{15,}$/;
  if (title.length === 0) return 'empty';
  if (!validateString(title, letterLengthRegex)) return 'short';

  return 'valid';
};

export const validateBodyForNewQ = (text) => {
  if (text.length === 0) return 'empty';

  return 'valid';
};
