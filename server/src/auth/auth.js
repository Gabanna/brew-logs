const mail = require('./mail');
const login = require('./login')
const register = require('./register')

function init(app) {
  console.info('initialising auth module')
  login.register(app);
  register.register(app);
};

module.exports = {
  init: init
}
