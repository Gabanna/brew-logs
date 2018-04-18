const {MongoClient} = require( 'mongodb');
const mongomock = require('mongo-mock');
const dbConfig = require('../config/db.json');

module.exports = function() {
    return new Promise((resolve, reject) => {
        if(process.argv.indexOf('m') > -1) {
            var mongoClient = mongomock.MongoClient;
            mongoClient.persist="../data/mongo.js";
            var url = 'mongodb://localhost:27017/' + dbConfig.dbname;
        
            mongoClient.connect(url, {}, function(err, db) {
                if(err) {
                    reject(err);
                }
        
                resolve(db);
            });
        
        } else {
            const url = "mongodb://" + dbConfig.dbuser + ":" + dbConfig.dbpassword + "@" + dbConfig.host + ":" + dbConfig.port + "/" + dbConfig.dbname;
            MongoClient.connect(url, (err, db) => {
                if(err) {
                    reject(err);
                }
        
                resolve(db);
            });
        }
    })
};