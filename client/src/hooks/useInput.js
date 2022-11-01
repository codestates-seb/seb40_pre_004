import { useState } from 'react';

const useInput = (initialValue) => {
  const [value, setValue] = useState(initialValue);

  const bind = {
    value,
    onChange: (e) => {
      setValue(e.target.value);
    },
  };

  return [value, bind];
};

export default useInput;
