function TimController(){
  this.tim = require('../Model/tim');
}

TimController.prototype.getTimData = function(res, req){
  var tim = new this.tim();
  var my_team = null;
  var joined_team = null;
  var counter = 2;

  function result(){
    console.log("result called");
    if(counter==0){
      var final = {
        "my_team": my_team,
        "joined_team": joined_team
      }
      res.send(final);
      return;
    }
    else{
      setTimeout(result, 100);
      return;
    }
  }

  tim.getMyTim(req).then(function(result){
    my_team = result;
    counter--;
  });

  this.anggota_tim = require('../Model/anggota_tim');
  var anggota_tim = new this.anggota_tim();
  anggota_tim.getJoinedTim(req).then(function(result){
    joined_team = result;
    counter--;
  });

  setTimeout(result, 100);
}

TimController.prototype.listTim = function(res, req){
  var list_my_team = new this.tim();
  list_my_team.getMyTim(req).then(function(result){
    res.send(result);
  });
}

TimController.prototype.ikutLomba = function(res, req){
  this.tim_ikut_lomba = require('../Model/tim_ikut_lomba');
  var tim_ikut_lomba = new this.tim_ikut_lomba();
  tim_ikut_lomba.registerTeam(req).then(function(result){
    var ret = {"status": "Input berhasil!"};
    res.send(ret);
  })
  .catch(function(err){
    var ret = {"status": "failed"};
    res.send(ret);
  });
}

module.exports = TimController;
