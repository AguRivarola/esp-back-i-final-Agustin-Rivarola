db.createUser({
  user: "usrmongo",
  pwd: "pwdmongo",
  roles: [
    {
      role: "readWrite",
      db: "catalogdevmongo"
    },
    {
      role: "readWrite",
      db: "seriedevmongo"
    }
  ]
})