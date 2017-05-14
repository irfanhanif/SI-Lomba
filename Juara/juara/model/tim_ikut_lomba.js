function TimIkutLomba(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.TimIkutLomba = connect_to.sequelize.define('tim_ikut_lomba', {
    id_tim_ikut_lomba: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    id_tim: Sequelize.STRING,
    id_lomba: Sequelize.STRING,
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'tim_ikut_lomba'
  });
}

TimIkutLomba.prototype.registerTeam = function(req){
  var ikut_lomba = this.TimIkutLomba.build({
    id_tim: req.id_tim,
    id_lomba: req.id_lomba
  });
  return ikut_lomba.save()
}

TimIkutLomba.prototype.certainCompetition = function(req){
  var tim = require('../Model/tim');
  var Tim = new tim();

  this.TimIkutLomba.belongsTo(Tim.Tim, {foreignKey: 'id_tim'});

  return this.TimIkutLomba.findAll({
    include: [{
      model: Tim.Tim
    }],
    where: {
      id_lomba: req.id_lomba
    }
  });
}

module.exports = TimIkutLomba;
