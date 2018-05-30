const express = require('express');
const app = express();

app.use(express.static('platforms/browser/www'))

let port = process.env.PORT || 3000;

app.listen(port, function () {
  console.log('server is listening on port ' + port);
});
