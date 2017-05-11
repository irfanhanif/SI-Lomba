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

module.exports = TimController;
