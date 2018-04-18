var nodemailer = require('nodemailer');
const emailer = require('ooth-local-emailer')

module.exports = {
    sendMail: function({from, to, subject, body, html}) {
        var transporter = nodemailer.createTransport({
            service: 'gmail',
            auth: {
              user: 'ronny.gallus@gmail.com',
              pass: 'Ihm13jss'
            }
          });
          
          var mailOptions = {
            from: from,
            to: to,
            subject: subject,
            text: body
          };
          
          transporter.sendMail(mailOptions, function(error, info){
            if (error) {
              console.log(error);
            } else {
              console.log('Email sent: ' + info.response);
            }
          });
    }
}