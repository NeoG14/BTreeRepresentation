import React from "react";

export const KeysDisplay = ({ keys }) => {
  return (
    <div>
      <h2>Keys in BTree</h2>
      <ul>
        {keys.map((key, index) => (
          <li key={index}>{key}</li>
        ))}
      </ul>
    </div>
  );
};
