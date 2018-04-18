const express = require( 'express');
const session = require( 'express-session');
const uuid = require( 'uuid');
const cors = require( 'cors');

module.exports = function() {
  const app = express();
  app.use(
    session({
      name: uuid(),
      secret: uuid(),
      resave: false,
      saveUninitialized: true
    })
  );

  const corsMiddleware = cors({
    origin: "http://localhost:8100",
    credentials: true,
    preflightContinue: false
  });
  app.use(corsMiddleware);
  app.options(corsMiddleware);

  return app;
};
