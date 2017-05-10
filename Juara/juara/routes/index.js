var express = require('express');
var router = express.Router();

var UserController = require('../controller/UserController');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.post('/login', function(req, res, next){
  var login = new UserController();
  result = login.loginCheck(res, req.body);
});

router.post('/register', function(req, res, next){
  var register = new UserController();
  result = register.registerUser(res, req.body);
});

module.exports = router;
