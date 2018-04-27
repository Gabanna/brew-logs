const jwt = require('jsonwebtoken')
const uuid = require('uuid')

const optionTemplate = {
    expiresIn: sails.config.security.jwtExpiration,
    issuer: 'brew-log-server',
    jwtid: uuid()
}

module.exports = {
    inputs: {
        user: {
            type: 'ref',
            required: true
        }
    },
    fn: createJwt
}

async function createJwt(inputs, exits) {
    let options = Object.assign({}, optionTemplate);
    options.subject = inputs.user.username;
    const token = jwt.sign(inputs.user, sails.config.security.apiKey, options)
    exits.success(token)
}