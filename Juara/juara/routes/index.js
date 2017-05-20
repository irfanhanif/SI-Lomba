var express = require('express');
var router = express.Router();

var UserController = require('../controller/UserController');
var TimController = require('../controller/TimController');
var LombaController = require('../controller/LombaController');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

/* Routing User */
router.post('/login', function(req, res, next){
  var login = new UserController();
  result = login.loginCheck(res, req.body);
});
router.post('/register', function(req, res, next){
  var register = new UserController();
  result = register.registerUser(res, req.body);
});
router.post('/editprofile', function(req, res, next){
  var get_profile = new UserController();
  get_profile.getUserProfile(res, req.body);
});
router.post('/changeprofile', function(req, res, next){
  var user = new UserController();
  user.changeProfile(res, req.body);
});

/* Routing Tim */
router.post('/detailtim', function(req, res, next){
  var tim = new TimController();
  tim.getTimData(res, req.body);
});
router.post('/listtim', function(req, res, next){
  var tim = new TimController();
  tim.listTim(res, req.body);
});
router.post('/ikutlomba', function(req, res, next){
  var tim = new TimController();
  tim.ikutLomba(res, req.body);
});
router.post('/listtimikutserta', function(req, res, nect){
  var tim = new TimController();
  tim.certainTeam(res, req.body);
});
router.post('/requesttim', function(req, res, nect){
  var tim = new TimController();
  tim.requestToJoinTeam(res, req.body);
});
router.post('/acceptrequest', function(req, res, nect){
  var tim = new TimController();
  tim.acceptRequest(res, req.body);
});
router.post('/listrequest', function(req, res, nect){
  var tim = new TimController();
  tim.listRequest(res, req.body);
});
router.post('/createteam', function(req, res, nect){
  var tim = new TimController();
  tim.createTeam(res, req.body);
});
router.post('/editteam', function(req, res, nect){
  var tim = new TimController();
  tim.editTeam(res, req.body);
});

/* Routing Lomba */
router.post('/listlomba', function(req, res, next){
  var lomba = new LombaController();
  lomba.getLomba(res, req.body);
});

/* Routing Dosbing */
router.post('/getdosbing', function(req, res, next){
  var lomba = new LombaController();
  lomba.getDosbing(res, req.body);
});

module.exports = router;
