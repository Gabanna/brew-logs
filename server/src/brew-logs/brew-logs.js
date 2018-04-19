const createBrewLogModule = require('./create-brew-log')
const brewLogsByUserModule = require('./brew-logs-by-user')

function init(app) {
  console.info('initialising brew-logs module')
  createBrewLogModule.register(app)
  brewLogsByUserModule.register(app)
};

module.exports = {
  init: init
}
