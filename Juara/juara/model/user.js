function User(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.User = connect_to.sequelize.define('user', {
    id_user: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    nrp: Sequelize.STRING,
    password: Sequelize.STRING,
    nama: Sequelize.STRING,
    email: Sequelize.STRING,
    jurusan: Sequelize.STRING,
    file_fotoprofil: Sequelize.STRING
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'user'
  });
}

User.prototype.checkUserAccount = function(nrp, password){
  return this.User.findAll({
    attributes: ['nrp', 'password'],
    where: {
      nrp: nrp,
      password: password
    }
  });
}

User.prototype.registerNewAccount = function(req){
  var new_user = this.User.build({
    nrp: req.nrp,
    password: req.password,
    nama: req.nama,
    email: req.email,
    jurusan: req.jurusan,
    file_fotoprofil: req.file_fotoprofil
  });
  return new_user.save()
}

User.prototype.getAllData = function(req){
  return this.User.findAll({
    attributes: ['password', 'nama', 'email', 'jurusan'],
    where: {
      nrp: req.nrp
    }
  });
}

module.exports = User;
