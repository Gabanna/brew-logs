const authModule = require('./src/auth/auth');
const brewLogsModule = require('./src/brew-logs/brew-logs')

require('./src/proxy')();
const server = require('./src/server');
const app = server();

authModule.init(app);
brewLogsModule.init(app);

const port = process.env.port | 3000;
app.listen(port, () => {
  console.log('running in port ' + port);
});
