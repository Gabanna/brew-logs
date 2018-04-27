module.exports.maischen = {
  steps: [
    {
      rezept: "Einmaischen bei 45 °C",
      vorgang: [
        {
          name: "Wasser aufheizen",
          protokoll: {
            name: "Temperatur erreicht um ",
            type: "timestamp"
          }
        },
        {
          name: "Malz hinzugeben"
        }
      ]
    },
    {
      rezept: "Eiweißrast bei 55 °C, Dauer 10 min",
      vorgang: [
        {
          name: "Temperatur halten ",
          protokoll: {
            name: "Rastdauer",
            type: "periode"
          }
        }
      ]
    },
    {
      rezept: "Maltoserast: bei 63 °C",
      vorgang: [
        {
          name: "Aufheizen von [] °C auf [] °C",
          protokoll: {
            name: "Dauer",
            type: "periode"
          }
        },
        {
          name: "Temperatur halten",
          protokoll: {
            name: "Rastdauer",
            type: "periode"
          }
        }
      ]
    },
    {
      rezept: "Verzuckerung bei 72 °C, Dauer 30-40 min",
      vorgang: [
        {
          name: "Aufheizen von [] °C auf [] °C",
          protokoll: {
            name: "Dauer",
            type: "periode"
          }
        },
        {
          name: "Temperatur halten",
          protokoll: {
            name: "Rastdauer",
            type: "periode"
          }
        },
        {
          name: "Jodprobe",
          protokoll: {
            name: "Jodprobe O.K.",
            type: "boolean"
          }
        }
      ]
    },
    {
      rezept: "Läutertemperatur ist 78°C",
      vorgang: [
        {
          name: "Aufheizen von [] °C auf 78 °C",
          protokoll: {
            name: "Dauer",
            type: "periode"
          }
        }
      ]
    }
  ]
};
