module.exports = {
  primaryKey: "id",
  attributes: {
    id: { type: "number", unique: true, autoIncrement: true },
    name: { type: "string", required: true },
    type: { type: "string", required: true }
  }
};
