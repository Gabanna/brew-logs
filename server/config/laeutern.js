module.exports.laeutern = {
  steps: [
    {
      rezept: "Aufmaischen bei  78°C",
      vorgang: [
        {
          name: "Aufmaischen",
          protokoll: {
            name: "Fertig um ",
            type: "timestamp"
          }
        },
        {
          name: "Maischerast (20 min)",
          protokoll: {
            name: "Rastdauern",
            type: "periode"
          }
        }
      ]
    },
    {
      rezept: "Abmaischen 78°C",
      vorgang: [
        {
          name: "Vorderwürze klar",
          protokoll: {
            name: "Fertig um ",
            type: "timestamp"
          }
        }
      ]
    },
    {
      rezept: "Nachguss",
      vorgang: [
        {
          name: "Nachguß : [] Liter bei [] °C"
        }
      ]
    }
  ]
};
