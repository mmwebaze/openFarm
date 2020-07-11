/**
 * Validates the account sign-up form before it is submitted.
 */
 $().ready(function(){

console.log("Validation script loaded");
   var OPENFARM = OPENFARM || {};

   OPENFARM.validateAccount = function(){
     console.log("Validation script applied q");
     var allowsubmit = false;

     //on keypress
     $('#confirm_password').keyup(function(e){

       //get values
       var pass = $('#password').val();
       var confpass = $(this).val();

       //check the strings
       if(pass == confpass){
         console.log("Password is matching");
         //if both are same remove the error and allow to submit
         $('.error').text('');
         allowsubmit = true;
       }else{
         //if not matching show error and not allow to submit
         console.log("Password not matching");
         $('.error').text('Password and confirm password not matching');
         allowsubmit = false;
       }
     });

     //jquery form submit
     $('#accountForm').submit(function(){

       var pass = $('#password').val();
       var confpass = $('#confirm_password').val();

       //just to make sure once again during submit
       //if both are true then only allow submit
       if(pass == confpass){
         console.log("vvvvvv");
         allowsubmit = true;
       }
       if(allowsubmit){
         console.log("vvvvvvYes");
         return true;
       }else{
         console.log("xxxxxxx");
         return false;
       }
     });
   };

   OPENFARM.validateAccount();
 });
