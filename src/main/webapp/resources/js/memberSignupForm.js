//Problem: Hints are shown even when form is valid
//Solution: Hide and show them at appropriate times


var $password = $("#password");
var $confirmPassword = $("#confirm_password");
var id = $("#memberSignform_id");
var nickname = $("#memberSignform_nickname");
var email = $("#memberSignform_email");

//Hide hints
$("form span").hide();



function isPasswordValid() {
  return $password.val().length > 8;
}

function arePasswordsMatching() {
  return $password.val() === $confirmPassword.val();
}

function canSubmit() {
  return isPasswordValid() && arePasswordsMatching() &&
  isNotEmptyid() && isNotEmptyNickname() && isNotEmptyEmail();
}
function isNotEmptyid() {
	  return id.val().length >= 1;
	}

function isNotEmptyNickname() {
  return nickname.val().length >= 1;
}
function isNotEmptyEmail() {
  return email.val().length >= 1;
}

function passwordEvent(){
    //Find out if password is valid  
    if(isPasswordValid()) {
      //Hide hint if valid
      $password.next().hide();
    } else {
      //else show hint
      $password.next().show();
    }
}

function confirmPasswordEvent() {
  //Find out if password and confirmation match
  if(arePasswordsMatching()) {
    //Hide hint if match
    $confirmPassword.next().hide();
  } else {
    //else show hint 
    $confirmPassword.next().show();
  }
}

function enableSubmitEvent() {
  $("#submit").prop("disabled", !canSubmit());
}


id.keyup(function(event) {
  enableSubmitEvent();

});

nickname.keyup(function(event) {
  enableSubmitEvent();
});

email.keyup(function(event) {
  enableSubmitEvent();
});

//When event happens on password input
$password.focus(passwordEvent).keyup(passwordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);

//When event happens on confirmation input
$confirmPassword.focus(confirmPasswordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);

enableSubmitEvent();