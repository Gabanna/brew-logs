const db = require('../db');
const moment = require('moment');

function register(app) {
    console.info('registering "create-brew-log"-function');
  app.post('/brew-logs', (req, res) => {
    let body = req.body;
    if (body && body.users) {
      createBrewLog(body)
        .then(user => {
          res.status(201).send(user);
        })
        .catch(err => {
          if (err.error) {
            res.status(500).send(err.error);
          } else {
            res.status(400).send(err.reason);
          }
          console.error(err);
        });
    } else {
        res.status(400).send('body data containing "users"-attribute is required')
    }
  });
}

function createBrewLog(brewLog) {
  return new Promise((resolve, reject) => {
    db
      .createConnection()
      .then(connection => {
        let brewLogs = connection.collection('brew-logs');
        brewLogs.insertOne({
          ...brewLog,
          created: moment().format(),
          author: brewLog.users[0]
        }, (err, doc) => {
          if (err) {
            reject({reason: err});
          } else {
            resolve(doc.ops[0]);
          }
        });
      })
      .catch(reject);
  });
}

module.exports = {
  register: register
};
