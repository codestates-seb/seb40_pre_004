import { useState } from 'react';

const useCheckbox = (initialValue) => {
  const [value, setValue] = useState(initialValue);

  const bind = {
    onClick: (e) => {
      setValue(e.target.checked);
    },
  };

  return [value, bind];
};

export default useCheckbox;
