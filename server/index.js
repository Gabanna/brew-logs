const express = require( 'express');
const session = require( 'express-session');
const uuid = require( 'uuid');
const cors = require( 'cors');
const Ooth = require( 'ooth');
const {MongoClient, ObjectId} = require( 'mongodb');
const OothMongo = require( 'ooth-mongo');
const mongomock = require('mongo-mock');
const oothLocal = require('ooth-local');
const emailer = require('ooth-local-emailer')

var nodemailer = require('nodemailer');

const config = require('./config.json');

const globalTunnel = require('global-tunnel');

process.env.http_proxy = config.http_proxy;
globalTunnel.initialize();

const app = express();
app.use(session({
    name: uuid(),
    secret: uuid(),
    resave: false,
    saveUninitialized: true
}));

const corsMiddleware = cors({
    origin: 'http://localhost:8100',
    credentials: true,
    preflightContinue: false
});
app.use(corsMiddleware);
app.options(corsMiddleware);

const ooth = new Ooth({
    path: '/auth'
});

const port = process.env.port | 3000;

if(process.argv.indexOf('m') > -1) {
    var mongoClient = mongomock.MongoClient;
    mongoClient.persist="mongo.js";
    var url = 'mongodb://localhost:27017/brew-logs';

    mongoClient.connect(url, {}, function(err, db) {
        if(err) {
            throw err;
        }

        startApp(db);
    });

} else {
    const url = "mongodb://" + config.mongodb.dbuser + ":" + config.mongodb.dbpassword + "@" + config.mongodb.host + ":" + config.mongodb.port + "/" + config.mongodb.dbname;
    MongoClient.connect(url, (err, db) => {
        if(err) {
            throw err;
        }

        startApp(db);
    });
}


function startApp(db) {
    const oothMongo = new OothMongo(db, ObjectId);
    ooth.start(app, oothMongo).then(() => {
        app.listen(port, () => {
            console.log('running in port ' + port);
        })
    }).catch(console.error);

    ooth.use('local', oothLocal(emailer({
        from: "info@example.com",
        siteName: 'Ooth local config',
        url: 'http://localhost:3000',
        sendMail: sendMail
    })));
}

function sendMail({from, to, subject, body, html}) {
    var transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
          user: 'ronny.gallus@gmail.com',
          pass: 'Ihm13jss'
        }
      });
      
      var mailOptions = {
        from: from,
        to: to,
        subject: subject,
        text: body
      };
      
      transporter.sendMail(mailOptions, function(error, info){
        if (error) {
          console.log(error);
        } else {
          console.log('Email sent: ' + info.response);
        }
      });
}