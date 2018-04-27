module.exports = {
  primaryKey: "id",
  attributes: {
    id: { type: "number", unique: true, autoIncrement: true },
    rezept: { type: "string", required: true },
    vorgang: { collection: "vorgang"}
  }
};
