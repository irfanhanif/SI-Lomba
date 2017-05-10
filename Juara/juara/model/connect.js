function Connect(){
  var Sequelize = require('sequelize');
  this.sequelize = new Sequelize('si-lomba', 'root', '', {
    host: 'localhost',
    dialect: 'mysql'
  });
}

module.exports = Connect;
