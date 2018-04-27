module.exports = {
  primaryKey: "id",
  attributes: {
    id: { type: "number", unique: true, autoIncrement: true },
    username: { type: "string", required: true, unique: true },
    email: { type: "string", required: true, unique: true },
    password: { type: "string", required: true }
  }
};
