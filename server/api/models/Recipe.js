module.exports = {
  primaryKey: "id",
  attributes: {
    id: { type: "number", unique: true, autoIncrement: true },
    name: { type: "string" },
    datum: { type: "string" },
    sorte: { type: "string" },
    author: { type: "string" },
    ausschlagwuerze: { type: "number" },
    sudhausausbeute: { type: "number" },
    stammwuerze: { type: "number" },
    bittere: { type: "number" },
    farbe: { type: "number" },
    alkohol: { type: "number" },
    kurzbeschreibung: { type: "string" },
    malz: { collection: "malz" },
    maishform: { type: "string" },
    hopfen: { collection: "hopfen" },
    hefe: { type: "string" },
    anmerkung: { type: "string" }
  }
};
