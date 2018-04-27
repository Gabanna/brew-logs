module.exports = {
  primaryKey: "sorte",
  attributes: {
    sorte: { type: "string", unique: true, required: true },
    menge: { type: "number" },
    alpha: { type: "number" },
    kockzeit: { type: "number" }
  }
};
