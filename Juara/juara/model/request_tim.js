function RequestTim(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.RequestTim = connect_to.sequelize.define('request_tim', {
    id_request: {
      type: Sequelize.INTEGER,
      primaryKey: true
    },
    nrp: Sequelize.STRING,
    id_tim: Sequelize.INTEGER
  }, {
    timestamps: false,
    paranoid: true,
    underscored: true,
    freezeTableName: true,
    tableName: 'request_tim'
  });
}

RequestTim.prototype.getListRequest = function(req){
  var user = require('../Model/user');
  var User = new user();

  this.RequestTim.belongsTo(User.User, {foreignKey: 'nrp'});

  return this.RequestTim.findAll({
    include: [{
      model: User.User
    }],
    where: {
      id_tim: req.id_tim
    }
  });
}

RequestTim.prototype.newRequest = function(req){
  var new_request = this.RequestTim.build({
    nrp: req.nrp,
    id_tim: req.id_tim
  });
  return new_request.save()
}

RequestTim.prototype.deleteRequest = function(req){
  return this.RequestTim.destroy({
    where: {
      nrp: req.nrp,
      id_tim: req.id_tim
    }
  });
}

module.exports = RequestTim;
