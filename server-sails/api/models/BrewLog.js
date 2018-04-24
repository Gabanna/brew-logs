module.exports = {
  primaryKey: "id",
  attributes: {
    id: { type: "number", unique: true, autoIncrement: true },
    author: { type: "number", required: true },
    name: { type: "string", required: true },
    users: { type: "ref" },
  }
};
