const db = require('../db');

function register(app) {
  console.info('registering "login"-function');
  app.post('/auth/login', (req, res) => {
    let body = req.body;
    if (body) {
      login(body.email, body.password)
        .then(user => {
          res.send(user);
        })
        .catch(err => {
          if (err.error) {
            res.status(500).send(err.error);
          } else {
            res.status(401).send(err.reason);
          }
          console.error(err);
        });
    }
  });
}

function login(email, password) {
  return new Promise((resolve, reject) => {
    db.find().make(filter => {
      filter.where('')
    })
        let users = connection.collection('users');
        let user = users.findOne({ $or: [{email: email}, {username: email}] }, (err, doc) => {
          if (err) {
            reject(err);
          } else {
            if (doc && doc.password == password) {
              resolve({
                email: doc.email,
                username: doc.username
              });
            } else {
              reject({ reason: 'unauthorized' });
            }
          }
        });
      };
}

module.exports = {
  register: register
};