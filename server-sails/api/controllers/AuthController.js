module.exports = {
  login: login,
  register: register
};

async function register(req, res) {
  var body = req.body;
  if (body.username && body.password && body.email) {
    let userCount = await User.count({
      or: [{ username: body.username }, { email: body.email }]
    });

    if (userCount > 0) {
      res.status(400).send({
        message: 'the username or email is already taken',
        err_key: 'usernameEmailAlreadyTaken'
      });
    } else {

        let user = await User.create(body);
        login(req, res);
    }
  } else {
    res.status(400).send({
      message: 'username, password and email are required',
      err_key: 'usernamePasswordEmailRequired'
    });
  }
}

async function login(req, res) {
  var body = req.body;

  if (body.username && body.password) {
    var user = await User.findOne({
      or: [{ username: body.username }, { email: body.username }]
    });

    if (user && user.password == body.password) {
      const token = await sails.helpers.jwtCreator.with({
        user: { username: user.username, email: user.email }
      });
      res.set('Authorization', 'Bearer ' + token);
      res.sendStatus(200);
    } else {
      res.sendStatus(401);
    }
  } else {
    res.status(400).send({
      message: 'username and password are required',
      err_key: 'usernamePasswordRequired'
    });
  }
}
