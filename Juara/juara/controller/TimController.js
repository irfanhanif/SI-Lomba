function TimController(){
  this.tim = require('../Model/tim');
}

TimController.prototype.createTeam = function(res, req){
  var tim = new this.tim();
  tim.insertTeam(req).then(function(result){
    var ret = {"status": "success"};
    res.send(ret);
  })
  .catch(function(err){
    var ret = {"status": "failed"};
    res.send(ret);
  });
}

TimController.prototype.editTeam = function(res, req){
  var tim = new this.tim();
  tim.editTeam(req).then(function(result){
    var ret = {"status": "success"};
    res.send(ret);
  })
  .catch(function(err){
    var ret = {"status": "failed"};
    res.send(ret);
  });
}

TimController.prototype.listRequest = function(res, req){
  this.request_tim = require('../Model/request_tim');
  var request = new this.request_tim();
  request.getListRequest(req).then(function(result){
    res.send(result);
  });
}

TimController.prototype.certainTeam = function(res, req){
  this.tim_ikut_lomba = require('../Model/tim_ikut_lomba');
  var tim_ikut_lomba = new this.tim_ikut_lomba();
  tim_ikut_lomba.certainCompetition(req).then(function(result){
    res.send(result);
  });
}

TimController.prototype.acceptRequest = function(res, req){
  var insert_status = -1;
  var delete_status = -1;

  function result(){
    if(insert_status == 0 || delete_status == 0){
      var ret = {"status": "failed"};
      res.send(ret);
      return;
    }
    else if(insert_status == 1 && delete_status == 1){
      var ret = {"status": "success"};
      res.send(ret);
      return;
    }
    else{
      setTimeout(result, 100);
      return;
    }
  }

  this.anggota_tim = require('../Model/anggota_tim');
  var anggota_tim = new this.anggota_tim();
  anggota_tim.insertNewMember(req).then(function(result){
    insert_status = 1;
  })
  .catch(function(err){
    insert_status = 0;
  });

  this.request_tim = require('../Model/request_tim');
  var request_tim = new this.request_tim();
  request_tim.deleteRequest(req).then(function(result){
    delete_status = 1;
  })
  .catch(function(err){
    delete_status = 0;
  });

  setTimeout(result, 100);
}

TimController.prototype.requestToJoinTeam = function(res, req){
  this.request_tim = require('../Model/request_tim');
  var request = new this.request_tim();
  request.newRequest(req).then(function(result){
    var ret = {"status": "Request berhasil!"};
    res.send(ret);
  })
  .catch(function(err){
    var ret = {"status": "failed"};
    res.send(ret);
  });
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
