const { MongoClient } = require("mongodb");
const mongomock = require("mongo-mock");
const dbConfig = require("../config/db.json");
var fs = require("fs");

function createConnection() {
  return new Promise((resolve, reject) => {
    if (isEmbedded()) {
      setupEmbeddedDb(resolve, reject);
    } else {
      setupRemoteDb(resolve, reject);
    }
  });
}

function isEmbedded() {
  return process.argv.indexOf("-e") > -1;
}

function setupRemoteDb(resolve, reject) {
  const url =
    "mongodb://" +
    dbConfig.dbuser +
    ":" +
    dbConfig.dbpassword +
    "@" +
    dbConfig.host +
    ":" +
    dbConfig.port +
    "/" +
    dbConfig.dbname;
  MongoClient.connect(url, (err, db) => {
    if (err) {
      reject(err);
    }

    resolve(db);
  });
}

module.exports = {
  createConnection: createConnection
};

function setupEmbeddedDb(resolve, reject) {
  const dataPath = "./data/mongo.js";
  var mongoClient = mongomock.MongoClient;
  mongoClient.persist = dataPath;
  var url = "mongodb://localhost:27017/" + dbConfig.dbname;

  var loadlocation = __dirname + "./../data/mongo.js";

  if (fs.existsSync(loadlocation)) {
    mongoClient
      .load(loadlocation)
      .then(() => {
        mongoClient.connect(url, {}, function(err, db) {
          if (err) {
            reject(err);
          }
          resolve(db);
        });
      })
      .catch(reject);
  } else {
    mongoClient.connect(url, {}, function(err, db) {
      if (err) {
        reject(err);
      }
      resolve(db);
    });
  }
}
