const express = require( 'express');
var bodyParser = require('body-parser')
const cors = require( 'cors');

module.exports = function() {
  const app = express();

  const corsMiddleware = cors({
    origin: "http://localhost:8100",
    credentials: true,
    preflightContinue: false
  });
  app.use(corsMiddleware);
  app.options(corsMiddleware);
  app.use(bodyParser.json())

  return app;
};
