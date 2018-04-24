const authModule = require('./src/auth');
const brewLogsModule = require('./src/brew-logs/brew-logs')

require('./src/proxy')();
const server = require('./src/server');
const app = server();

authModule.init(app);
brewLogsModule.init(app);

const port = process.env.port | 3000;
const host = '0.0.0.0'
app.listen(port, host, () => {
  console.log('running in port ' + port);
});
