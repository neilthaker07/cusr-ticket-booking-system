var FirebaseConfiguration = (function () {

    var defaultApp;

    // Initialize Firebase
  	var config = {
    	apiKey: "AIzaSyDp23RJ2lDCGLHOE0YRmPlz82Wpu0nuaNs",
    	authDomain: "california-ultra-speed-rail.firebaseapp.com",
    	databaseURL: "https://california-ultra-speed-rail.firebaseio.com",
    	projectId: "california-ultra-speed-rail",
    	storageBucket: "",
    	messagingSenderId: "447670123887"
  	};

    function setup() {
        var object = firebase.initializeApp(config);
        return object;
    }

    return {
        getInstance: function () {
            if (!defaultApp) {
                defaultApp = setup();
            }
            return defaultApp;
        }
    };
})();

FirebaseConfiguration.getInstance();

$(document).ready(function(){
  $("#google-form").click(function(){
    var provider = new firebase.auth.GoogleAuthProvider();
    signUpWithSocialPlatform(provider);
  });

  $("#facebook-form").click(function(){
    var provider = new firebase.auth.FacebookAuthProvider();
    signUpWithSocialPlatform(provider);
  });
});

function signUpWithSocialPlatform(provider){

  firebase.auth().signInWithPopup(provider).then(function(result) {
    var firebaseUser = result.user;
    saveUserInfoToDatabase(firebaseUser.uid, firebaseUser.email);
  })
  .catch(function(error) {
    var errorCode = error.code;
    var errorMessage = error.message;
    alert("Error Code: " + errorCode + " " + errorMessage);
  });
}

function saveUserInfoToDatabase(userId, emailAddress){

  $.ajax({
     url:  'http://localhost:8080/user',
     method: "POST",
     data: jQuery.param({ userId: userId, emailAddress: emailAddress}),
     error: function(xhr, status, error) {
        alert(error);
     },
     success: function(data) {
        localStorage.setItem("isUserLoggedIn",true);
        localStorage.setItem("emailAddress",emailAddress);
        window.location = "search_results.html";
     },
  });
} // end of saveUserInfoToDatabase()
