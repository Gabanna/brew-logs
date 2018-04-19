var nodemailer = require("nodemailer");
const emailer = require("ooth-local-emailer");
const mailConfig = require("../../config/mail.json");

module.exports = function() {
  return emailer({
    ...mailConfig,
    sendMail: sendMail
  });
};

function sendMail({ from, to, subject, body, html }) {
  var transporter = nodemailer.createTransport(mailConfig.transport);

  var mailOptions = {
    from: from,
    to: to,
    subject: subject,
    text: body
  };

  transporter.sendMail(mailOptions, (error, info) => {
    if (error) {
      console.log(error);
    } else {
      console.log("Email sent: " + info.response);
    }
  });
}
