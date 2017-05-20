function Dosbing(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.Dosbing = connect_to.sequelize.define('dosbing', {
    id_dosbing: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    nama_dosbing: Sequelize.STRING,
    jurusan_dosbing: Sequelize.STRING,
    hp_dosbing: Sequelize.STRING,
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'dosbing'
  });
}

Dosbing.prototype.getAll = function(req){
  return this.Dosbing.findAll();
}

module.exports = Dosbing;
