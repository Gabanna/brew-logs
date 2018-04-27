module.exports = {
    primaryKey: "id",
    attributes: {
      id: { type: "number", unique: true, autoIncrement: true },
      abgeschlossen: { type: 'boolean' },
      steps: { collection: "step"}
    }
  };
  