function User(data){
  var connect = require('../Model/connect');
  var Sequelize = require('sequelize');

  var connect_to = new connect();
  this.User = connect_to.sequelize.define('user', {
    id_user: {
      type: Sequelize.INTEGER,
      primaryKey: true
    }
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
  
}

module.exports = User;
