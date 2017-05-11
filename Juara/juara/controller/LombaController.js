function LombaController(){
  this.lomba = require('../Model/lomba');
}

LombaController.prototype.getLomba = function(res, req){
  var lomba = new this.lomba();
  lomba.getAllLomba(req).then(function(result){
    res.send(result);
  });
}

module.exports = LombaController;
