export const extractKeys = (node) => {
  let keys = [];

  const traverse = (node) => {
    if (!node) return;

    keys = keys.concat(node.keys);
    if (node.children) {
      node.children.forEach((child) => {
        traverse(child);
      });
    }
  };

  traverse(node);
  return keys;
};
