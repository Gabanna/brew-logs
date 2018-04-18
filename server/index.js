const Ooth = require( 'ooth');

const OothMongo = require( 'ooth-mongo');
const oothLocal = require( 'ooth-local');
const {ObjectId} = require( 'mongodb');
const mail = require('./src/mail');
const db = require('./src/db');

require('./src/proxy')();
const server = require('./src/server');
const app = server();

db().then(db => {
    startApp(db);
})
.catch(error => {
    throw error;
});

function startApp(db) {
    const ooth = new Ooth({
        path: '/auth'
    });
    const oothMongo = new OothMongo(db, ObjectId);
    ooth.start(app, oothMongo).then(() => {
        const port = process.env.port | 3000;
        app.listen(port, () => {
            console.log('running in port ' + port);
        })
    }).catch(console.error);

    ooth.use('local', oothLocal(mail()));
}
