function CustomToolBar() {
  return (
    <div id="toolbar">
      <select className="ql-header">
        <option value="1"></option>
        <option value="2"></option>
        <option value="3"></option>
        <option value="4"></option>
      </select>
      <button className="ql-bold"></button>
      <button className="ql-italic"></button>
      <select className="ql-color">
        <option value="red"></option>
        <option value="green"></option>
        <option value="blue"></option>
        <option value="orange"></option>
        <option value="violet"></option>
        <option value="#d0d1d2"></option>
      </select>
      <select className="ql-background"></select>
      <button className="ql-link"></button>
      <button className="ql-image"></button>
    </div>
  );
}

export default CustomToolBar;
