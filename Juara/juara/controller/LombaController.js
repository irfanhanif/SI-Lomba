function LombaController(){
  this.lomba = require('../Model/lomba');
}

LombaController.prototype.getLomba = function(res, req){
  var lomba = new this.lomba();
  lomba.getAllLomba(req).then(function(result){
    res.send(result);
  });
}

LombaController.prototype.getDosbing = function(res, req){
  this.dosbing = require('../Model/dosbing.js');
  var dosbing = new this.dosbing();
  dosbing.getAll(req).then(function(result){
    res.send(result);
  });
}

LombaController.prototype.insertLomba = function(res, req){
  var lomba = new this.lomba();
  lomba.insertNew(req).then(function(result){
    var ret = {"status": "success"};
    res.send(ret);
  })
  .catch(function(err){
    var ret = {"status": "failed"};
    res.send(ret);
  });
}

module.exports = LombaController;
