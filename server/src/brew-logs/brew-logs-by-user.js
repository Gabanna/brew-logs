const db = require('../db');

function register(app) {
  console.info('registering "brew-logs-for-user"-function');
  app.get('/brew-logs/user/:email', (req, res) => {
    let email = decodeURI(req.params.email);
    findBrewLogsByUser(email)
      .then(brewlogs => {
        res.status(200).send(brewlogs);
      })
      .catch(err => {
        if (err.error) {
          res.status(500).send(err.error);
        } else {
          res.status(400).send(err.reason);
        }
        console.error(err);
      });
  });
}

function findBrewLogsByUser(email) {
  return new Promise((resolve, reject) => {
    db
      .createConnection()
      .then(connection => {
        let brewLogs = connection.collection('brew-logs');
        brewLogs.find({users: email}).toArray((err, doc) => {
          if (err) {
            reject({ reason: err });
          } else {
            resolve(doc);
          }
        });
      })
      .catch(reject);
  });
}

module.exports = {
  register: register
};
