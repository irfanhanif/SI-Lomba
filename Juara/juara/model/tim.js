function Tim(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.Tim = connect_to.sequelize.define('tim', {
    id_tim: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    nrp: Sequelize.STRING,
    nama_tim: Sequelize.STRING,
    maksimal_anggota: Sequelize.INTEGER,
    deskripsi_tim: Sequelize.STRING,
    file_fotoprofil_tim: Sequelize.STRING
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'tim'
  });
}

Tim.prototype.getMyTim = function(req){
  return this.Tim.findAll({
    where: {
      nrp: req.nrp
    }
  });
}

Tim.prototype.insertTeam = function(req){
  var new_team = this.Tim.build({
    nama_tim: req.nama_tim,
    nrp: req.nrp,
    maksimal_anggota: req.maksimal_anggota,
    deskripsi_tim: req.deskripsi_tim,
    file_fotoprofil_tim: req.file_fotoprofil_tim
  });
  return new_team.save()
}

Tim.prototype.editTeam = function(req){
  return this.Tim.update({
    nama_tim: req.nama_tim,
    nrp: req.nrp,
    maksimal_anggota: req.maksimal_anggota,
    deskripsi_tim: req.deskripsi_tim,
    file_fotoprofil_tim: req.file_fotoprofil_tim
  }, {
    where: {
      id_tim: req.id_tim
    }
  });
}

module.exports = Tim;
