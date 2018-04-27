const jwt = require('jsonwebtoken')

module.exports = {
    inputs: {
        token: {
            type: 'string',
            required: true
        }
    },
    fn: validateJwt
}

async function validateJwt(inputs, exits) {
    const jwt_token = inputs.token.replace('Bearer ', '');

    try {
        let user = jwt.verify(jwt_token, sails.config.security.apiKey);
        exits.success(user);

    } catch(err) {
        exits.error(err);
    }
}