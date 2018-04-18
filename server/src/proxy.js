const globalTunnel = require('global-tunnel');
const proxyConfig = require('../config/proxy.json');

module.exports = function() {
    process.env.http_proxy = proxyConfig.http_proxy;
    globalTunnel.initialize();
}
