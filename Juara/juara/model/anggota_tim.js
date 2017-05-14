function AnggotaTim(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.AnggotaTim = connect_to.sequelize.define('anggota_tim', {
    id_anggota_tim: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    id_tim: Sequelize.INTEGER,
    nrp: Sequelize.STRING,
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'anggota_tim'
  });
}

AnggotaTim.prototype.getJoinedTim = function(req){
  var tim = require('../Model/tim');
  var Tim = new tim();

  this.AnggotaTim.belongsTo(Tim.Tim, {foreignKey: 'id_tim'});

  return this.AnggotaTim.findAll({
    include: [{
      model: Tim.Tim
    }],
    where: {
      nrp: req.nrp
    }
  });
}

AnggotaTim.prototype.insertNewMember = function(req){
  var new_member = this.AnggotaTim.build({
    nrp: req.nrp,
    id_tim: req.id_tim
  });
  return new_member.save()
}

module.exports = AnggotaTim;
