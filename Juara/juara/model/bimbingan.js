function Bimbingan(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.Bimbingan = connect_to.sequelize.define('bimbingan', {
    id_bimbingan: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    id_tim: Sequelize.STRING,
    tanggal_bimbingan: Sequelize.DATE,
    comment: Sequelize.STRING,
    file_bimbingan: Sequelize.STRING
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'bimbingan'
  });
}

Bimbingan.prototype.insert = function(req, filename){
  var new_data = this.Bimbingan.build({
    id_tim: req.id_tim,
    tanggal_bimbingan: req.tanggal_bimbingan,
    comment: req.comment,
    file_bimbingan: filename
  });
  return new_data.save()
}

Bimbingan.prototype.getAll = function(req){
  return this.Bimbingan.findAll();
}

module.exports = Bimbingan;
