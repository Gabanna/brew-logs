module.exports = {
  findBrewLogsByUser: findBrewLogsByUser,
  createBrewLog: createBrewLog
};

async function findBrewLogsByUser(req, res) {
  const token = req.headers["authorization"];
  const jwt = await sails.helpers.jwtValidator.with({
    token: token
  });

  let user = await User.findOne({ username: jwt.username });

  let brewlogs = await BrewLog.find({
    or: [{ author: user.id }, { users: { contains: user.id } }]
  }).populate("maischen");
  
  res.send(brewlogs || []);
}

async function createBrewLog(req, res) {
  const token = req.headers["authorization"];
  if (token) {
    const logName = req.body.name;

    if (logName) {
      const jwt = await sails.helpers.jwtValidator.with({
        token: token
      });

      let user = await User.findOne({ username: jwt.username });

      if (user) {
        let brewLog = await sails.helpers.createBrewLog.with({
          name: logName,
          author: user.id
        });

        res.status(201).send(brewLog);
      } else {
        res.status(400).send("user " + jwt.username + " is unknown");
      }
    } else {
      res.status(400).send("name is required");
    }
  } else {
    res.sendStatus(401);
  }
}
