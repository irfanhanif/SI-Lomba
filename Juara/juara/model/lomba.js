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

module.exports = Lomba;
