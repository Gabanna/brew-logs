module.exports = {
  primaryKey: "name",
  attributes: {
    name: { type: "string", required: true, unique: true },
    menge: { type: "number" },
    einheit: { type: "string" }
  }
};
