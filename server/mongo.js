var ObjectID = require('bson-objectid');

module.exports = {
  "localhost:27017": {
    "databases": {
      "brew-logs": {
        "collections": [
          {
            "name": "system.namespaces",
            "documents": [
              {
                "name": "system.indexes"
              },
              {
                "name": "users"
              }
            ]
          },
          {
            "name": "system.indexes",
            "documents": [
              {
                "v": 1,
                "key": {
                  "_id": 1
                },
                "ns": "brew-logs.users",
                "name": "_id_",
                "unique": true
              }
            ]
          },
          {
            "name": "users",
            "documents": [
              {
                "local": {
                  "email": "tester@gmail.com",
                  "password": "$2a$12$fSFbKDtOKpUq2YRKZ1dNVeKufa0UtIr5KAw.ikKPmRlqZ7xZFbZwi",
                  "verificationToken": "$2a$12$TkWELdeMdVoPZuK0nC1wJ.LMAZSBsUii6xI3PyvaslwaQ2eSdCt9m",
                  "verificationTokenExpiresAt": "2018-04-18T09:49:36.936Z"
                },
                "_id": ObjectID("5ad706a12800bb44f4999226")
              }
            ]
          }
        ]
      }
    }
  }
}