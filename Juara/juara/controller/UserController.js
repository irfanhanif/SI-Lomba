function UserController(){
  this.user = require('../Model/user');
}

UserController.prototype.loginCheck = function(res, req){
  var user = new this.user();
  user.checkUserAccount(req.nrp, req.password).then(function(result){
    if(result.length > 0){
      var ret = {"status": "success"};
      res.send(ret);
    }
    else{
      var ret = {"status": "success"};
      res.send(ret);
    }
  });
}

UserController.prototype.registerUser = function(res, req){
  var user = new this.user();
  user.registerNewAccount(req).then(function(result){
    console.log("Insert berhasil!");
    var ret = {"status": "success"};
    res.send(ret);
  })
  .catch(function(err){
    console.log("Insert gagal!");
    console.log(err);
    var ret = {"status": "success"};
    res.send(ret);
  });
}

module.exports = UserController;
