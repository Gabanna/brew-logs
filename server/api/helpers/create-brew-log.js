module.exports = {
  inputs: {
    name: {
      type: "string",
      required: true
    },
    author: {
      type: "number",
      required: true
    }
  },
  fn: createBrewLog
};

async function createBrewLog(inputs, exits) {

  try {
    let maischenSteps = await createSteps(sails.config.maischen);
    let laeuternSteps = await createSteps(sails.config.laeutern);

    let maischen = await Maischen.create({ steps: steps }).fetch();
    let maischen = await Maischen.create({ steps: steps }).fetch();

    let brewLog = await BrewLog.create({
      ...inputs,
      maischen: maischen.id
    }).fetch();
    exits.success(brewLog);
  } catch (err) {
    exits.error(err);
  }
}

function createSteps(template) {
  return new Promise((resolve, reject) => {
    let steps = [];
    template.steps.forEach(step => {
      createVorgang(step)
        .then(vorgang => {
          Step.create({
            rezept: step.rezept,
            vorgang: vorgang
          })
            .fetch()
            .then(stepModel => {
              steps.push(stepModel.id);
              if (steps.length == template.steps.length) {
                resolve(steps);
              }
            })
            .catch(reject);
        })
        .catch(reject);
    });
  });
}

function createVorgang(step) {
  return new Promise((resolve, reject) => {
    let vorgangs = [];
    step.vorgang.forEach(vorgang => {
      if (vorgang.protokoll) {
        Protokoll.create({
          name: vorgang.protokoll.name,
          type: vorgang.protokoll.type
        })
          .fetch()
          .then(protokoll => {
            Vorgang.create({ name: vorgang.name, protokoll: protokoll.id })
              .fetch()
              .then(vorgangModel => {
                vorgangs.push(vorgangModel.id);
                if (vorgangs.length == step.vorgang.length) {
                  resolve(vorgangs);
                }
              })
              .catch(reject);
          })
          .catch(reject);
      } else {
        Vorgang.create(vorgang)
          .fetch()
          .then(vorgangModel => {
            vorgangs.push(vorgangModel.id);
            if (vorgangs.length == step.vorgang.length) {
              resolve(vorgangs);
            }
          })
          .catch(reject);
      }
    });
  });
}
