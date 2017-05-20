function Lomba(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.Lomba = connect_to.sequelize.define('lomba', {
    id_lomba: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    nama_lomba: Sequelize.STRING,
    penyelenggara: Sequelize.STRING,
    kategori: Sequelize.STRING,
    hadiah: Sequelize.STRING,
    syarat: Sequelize.STRING,
    deskripsi_lomba: Sequelize.STRING
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'lomba'
  });
}

Lomba.prototype.getAllLomba = function(req){
  return this.Lomba.findAll();
}

User.prototype.insertNew = function(req){
  var new_lomba = this.User.build({
    nama_lomba: req.nama_lomba,
    penyelenggara: req.penyelenggara,
    kategori: req.kategori,
    hadiah: req.hadiah,
    syarat: req.syarat,
    deskiripsi_lomba: req.deskiripsi_lomba
  });
  return new_lomba.save()
}

module.exports = Lomba;
