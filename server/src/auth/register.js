const db = require("../db");
const md5 = require("md5");

function register(app) {
  console.info('registering "register"-function');
  app.post("/auth/register", (req, res) => {
    let body = req.body;
    if (body) {
        registerUser(body.email, body.username, body.password)
        .then(user => {
          res.status(201).send(user);
        })
        .catch(err => {
          if (err.error) {
            res.status(500).send(err.error);
          } else {
            res.status(401).send(err.reason);
          }
        });
    } else {
      res.status(400).send("body data is required");
    }
  });
}

function registerUser(email, username, password) {
  return new Promise((resolve, reject) => {
    db
      .createConnection()
      .then(connection => {
        let users = connection.collection("users");
        users.insertOne({
          email: email,
          username: username,
          password: password
        }, (err, doc) => {
            if(err) {
                reject(err)
            } else {
                resolve({ email: email, username: username });
            }
        });

      })
      .catch(err => reject({ error: err }));
  });
}

module.exports = {
  register: register
};
