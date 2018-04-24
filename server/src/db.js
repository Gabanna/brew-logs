const dbConfig = require("../config/db.json");
const total = require('total.js');

module.exports = (mode, port) => {
    return total.http(mode, {
        port: port,
        ip: '127.0.0.1'
    })
}
