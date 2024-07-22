import React, { useEffect, useState } from "react";
import { KeysDisplay } from "./components/KeyDisplay";
import { extractKeys } from "./utils";

const App = () => {
  const [tree, setTree] = useState(null);
  const [order, setOrder] = useState(4); // Valor predeterminado del orden
  const [error, setError] = useState(null);
  const [key, setKey] = useState("");

  const fetchTree = async (order) => {
    try {
      const response = await fetch(
        `http://localhost:8080/btree/create?order=${order}`,
        {
          method: "POST",
        }
      );
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const tree = await response.json();
      setTree(tree);
    } catch (error) {
      console.error("Error fetching tree:", error);
      setError(error);
    }
  };

  const handleCreateTree = () => {
    fetchTree(order);
  };

  const addKey = async (key) => {
    const response = await fetch(`http://localhost:8080/btree?key=${key}`, {
      method: "POST",
    });
    const tree = await response.json();
    setTree(tree);
  };

  const handleAddKey = () => {
    addKey(key);
  };

  const keys = tree ? extractKeys(tree.root) : [];

  return (
    <div>
      <h1>BTree Visualization</h1>
      {error ? (
        <p>Error: {error.message}</p>
      ) : tree ? (
        <KeysDisplay keys={keys} />
      ) : (
        <p>Loading tree...</p>
      )}
      <input
        type="number"
        value={order}
        onChange={(e) => setOrder(Number(e.target.value))}
        placeholder="Enter tree order"
      />
      <button onClick={handleCreateTree}>Create Tree</button>

      {/* /* Insertar Key*/}
      <input
        type="number"
        value={key}
        onChange={(e) => setKey(Number(e.target.value))}
        placeholder="Key to Insert"
      />
      <button onClick={handleAddKey}>Add Key</button>
    </div>
  );
};

export default App;
