function UserController(){
  this.user = require('../Model/user');
}

UserController.prototype.loginCheck = function(res, req){
  var user = new this.user();
  user.checkUserAccount(req.nrp, req.password).then(function(result){
    if(result.length > 0){
      var ret = {
        "status": "success",
        "status_user": result[0].dataValues.status_user
      };
      res.send(ret);
    }
    else{
      var ret = {"status": "failed"};
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

UserController.prototype.getUserProfile = function(res, req){
  var user = new this.user();
  user.getAllData(req).then(function(result){
    res.send(result);
  });
}

UserController.prototype.changeProfile = function(res, req){
  var user = new this.user();
  var multer = require('multer');

  var storage = multer.diskStorage({
    destination: function(req, file, callback){
      callback(null, 'unggahan')
    },
    filename: function(req, file, callback){
      console.log(file);
      callback(null, file.originalname)
    }
  });

  var upload = multer({
    storage: storage,
  }).array('file', 1);

  upload(req, res, function(err){
    if(err){
      console.log(err);
      var ret = {"status": "failed"};
      res.send(ret);
    } else {
      user.changeUserProfile(req.body, req.files[0].originalname).then(function(result){
        var ret = {"status": "Perubahan data berhasil!"};
        res.send(ret);
      })
      .catch(function(err){
        var ret = {"status": "Perubahan data gagal. Coba lagi."};
        res.send(ret);
      });
    }
  });
}

module.exports = UserController;
