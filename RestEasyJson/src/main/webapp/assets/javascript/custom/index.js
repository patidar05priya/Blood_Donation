var context = "http://localhost:8089/RESTfulExample";
var HAS_USER_LOGGED_IN = false;
var HAS_USER_LOGGED_OUT = false;
var LOGGED_USER_ID;
var LOGGED_USER_EMAILID;
var LOGGED_USER_TYPE;
/*************************   On Ready Code Start *******************************/
$(document).scroll(function() {
	if(!$("#mainNav").hasClass("affix-top")){
		$("#profileSection").hide();
	}
	else{
		if(HAS_USER_LOGGED_IN && !HAS_USER_LOGGED_OUT){
			$("#profileSection").show();
		}
	}
});

function openSlider(){
	$("#wrapper").css("display","block");
	}
jQuery(document).ready(function ($) {
	//$("#profileSection").hide();
    var options = {
        $AutoPlay: true,                                    //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
        $AutoPlayInterval: 4000,                            //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
        $PauseOnHover: 1,                                   //[Optional] Whether to pause when mouse over if a slider is auto playing, 0 no pause, 1 pause for desktop, 2 pause for touch device, 3 pause for desktop and touch device, 4 freeze for desktop, 8 freeze for touch device, 12 freeze for desktop and touch device, default value is 1

        $ArrowKeyNavigation: true,   			            //[Optional] Allows keyboard (arrow key) navigation or not, default value is false
        $SlideDuration: 800,                                //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
        $MinDragOffsetToSlide: 20,                          //[Optional] Minimum drag offset to trigger slide , default value is 20
        //$SlideWidth: 600,                                 //[Optional] Width of every slide in pixels, default value is width of 'slides' container
        //$SlideHeight: 300,                                //[Optional] Height of every slide in pixels, default value is height of 'slides' container
        $SlideSpacing: 0, 					                //[Optional] Space between each slide in pixels, default value is 0
        $DisplayPieces: 1,                                  //[Optional] Number of pieces to display (the slideshow would be disabled if the value is set to greater than 1), the default value is 1
        $ParkingPosition: 0,                                //[Optional] The offset position to park slide (this options applys only when slideshow disabled), default value is 0.
        $UISearchMode: 1,                                   //[Optional] The way (0 parellel, 1 recursive, default value is 1) to search UI components (slides container, loading screen, navigator container, arrow navigator container, thumbnail navigator container etc).
        $PlayOrientation: 1,                                //[Optional] Orientation to play slide (for auto play, navigation), 1 horizental, 2 vertical, 5 horizental reverse, 6 vertical reverse, default value is 1
        $DragOrientation: 1,                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)

        $ArrowNavigatorOptions: {                       //[Optional] Options to specify and enable arrow navigator or not
            $Class: $JssorArrowNavigator$,              //[Requried] Class to create arrow navigator instance
            $ChanceToShow: 1,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
            $AutoCenter: 2,                                 //[Optional] Auto center arrows in parent container, 0 No, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
            $Steps: 1                                       //[Optional] Steps to go for each navigation request, default value is 1
        },

        $ThumbnailNavigatorOptions: {
            $Class: $JssorThumbnailNavigator$,              //[Required] Class to create thumbnail navigator instance
            $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always

            $ActionMode: 1,                                 //[Optional] 0 None, 1 act by click, 2 act by mouse hover, 3 both, default value is 1
            $AutoCenter: 0,                                 //[Optional] Auto center thumbnail items in the thumbnail navigator container, 0 None, 1 Horizontal, 2 Vertical, 3 Both, default value is 3
            $Lanes: 1,                                      //[Optional] Specify lanes to arrange thumbnails, default value is 1
            $SpacingX: 3,                                   //[Optional] Horizontal space between each thumbnail in pixel, default value is 0
            $SpacingY: 3,                                   //[Optional] Vertical space between each thumbnail in pixel, default value is 0
            $DisplayPieces: 9,                              //[Optional] Number of pieces to display, default value is 1
            $ParkingPosition: 260,                          //[Optional] The offset position to park thumbnail
            $Orientation: 1,                                //[Optional] Orientation to arrange thumbnails, 1 horizental, 2 vertical, default value is 1
            $DisableDrag: false                            //[Optional] Disable drag or not, default value is false
        }
    };

    var jssor_slider1 = new $JssorSlider$("slider1_container", options);

    //responsive code begin
    //you can remove responsive code if you don't want the slider scales while window resizes
    function ScaleSlider() {
        var bodyWidth = document.body.clientWidth;
        if (bodyWidth)
            jssor_slider1.$ScaleWidth(Math.min(bodyWidth, 980));
        else
            window.setTimeout(ScaleSlider, 30);
    }
    ScaleSlider();

    $(window).bind("load", ScaleSlider);
    $(window).bind("resize", ScaleSlider);
    $(window).bind("orientationchange", ScaleSlider);
    //responsive code end
});

function enableUserDropDown(){	
	$( '#cd-dropdown' ).dropdown( {
		gutter : 5
	} );
}
/*************************   On Ready Code End *******************************/

function getUserLogin(userSelection){
		var userName = $("#userId").val();
		var userPwd = $("#userPwd").val();
		if(hasValue(userName) && hasValue(userPwd)){
			//alert(userName+"   "+userPwd)
			//sendGETRequest(context+"/rest/json/product/getUserLogin/"+userName+"/"+userPwd,"callback","addlParam");
			LOGGED_USER_EMAILID = userName;
			sendGETRequest(context+"/rest/UserDetails/getUserLogin/"+userName+"/"+userPwd,"getUserLoginCallBack","addlParam");
		}
}

function getUserLoginCallBack(XMLHttpRequest, data, rpcRequest) {
		if (XMLHttpRequest.status == 200) {
			if (hasValue(data)) {
				HAS_USER_LOGGED_IN = true;
				LOGGED_USER_ID = data.userid;
				LOGGED_USER_TYPE = data.usertype;
				LOGGED_USER_BLOOD_GROUP = data.bloodgroup;
				$("#loginMessage").css("display","none");
				$("#loginModal").modal("hide");
				$("#profileSection").css("display","block");
				enableUserDropDown();
				$("#profileSection .main .fleft .cd-dropdown > span").text(data.username);
				$(".hideAfterLogin").hide();
				$("#profileSection").css("margin-top","-37%");
				if(LOGGED_USER_TYPE == "RECEIVER"){
					$("#requestButton").css("display","block");
					$("#profileSection").css("margin-top","-30%");
				}
				if(LOGGED_USER_TYPE == "DONNER"){
					//sendGETRequest(context+"/rest/UserRequest/getUserActiveRequest/"+bloodgroup,"getUserActiveRequestCallBack");					
				}
				}
			else{
				$("#loginMessage").css("display","block");
				$("#loginMessage").text("User Id or Password Not Valid");
				$("#loginMessage").css("color","red");
			}
	}
}


function openForgotPwdModal(){
	$("#forgotPwdModal").modal("show");
	}

	function sendPwd(){
		var emailId = $("#input_emailId").val();
		//if(hasValue(userName) && hasValue(userPwd)){
			sendGETRequest(context+"/rest/UserDetails/getForgotPassword/"+emailId,"sendPwdCallBack");
		//}
	}
	function sendPwdCallBack(XMLHttpRequest, data, rpcRequest) {
		
				if (XMLHttpRequest.status == 200) {
					if (XMLHttpRequest.responseText=="Password Send Successfully To Your Email Id") {
						console.log("Password Send Successfully To Your Email Id");
						$("#forgotPwdModal").modal("hide");
						$("#loginMessage").html("Password Send Successfully To Your Email Id");
						//userRegistration();
					}
					else{
						console.log("User Id Not Found");
						$("#loginMessage").html("User Id Not Found");
					}
					
			}
		}
	
	/************************     For Data Table for List of Hospitals code start   *******************************/
	var jsonObj=[{"Name":"आकांक्षा नर्सिंग होम ","Address":" सुदामा नगर ","PhoneNumber":" 0731-2483754 "},
	             {"Name":"आदित्य नर्सिंग होम","Address":"7,सिंधु नगर,भंवरकुआ रोड ","PhoneNumber":"0731-2483311, 4045239"},
	             {"Name":"आनंद हॉस्पिटल एवं रिसर्च सेंटर ","Address":" सुदामा नगर ","PhoneNumber":"0731-2472121-23-24"},
	             {"Name":"अरिहंत हॉस्पिटल एंड रिसर्च सेंटर ","Address":" सुदामा नगर ","PhoneNumber":" 0731-2483754 "},
	             {"Name":"आरोग्य नर्सिंग होम ","Address":" 156 नयापुरा मेन रोड, घड़ीवाली मस्जिद के सामने ","PhoneNumber":"0731-2538184, 2547590"},
	             {"Name":"अर्पण नर्सिंग होम ","Address":" 151/2 इमली बाजार,राजवाडा  ","PhoneNumber":" 0731-2433911, 93032-71447  "},
	             {"Name":"आकांक्षा नर्सिंग होम ","Address":" सुदामा नगर ","PhoneNumber":" 0731-2483754 "},
	             {"Name":"आदित्य नर्सिंग होम","Address":"7,सिंधु नगर,भंवरकुआ रोड ","PhoneNumber":"0731-2483311, 4045239"},
	             {"Name":"आनंद हॉस्पिटल एवं रिसर्च सेंटर ","Address":" सुदामा नगर ","PhoneNumber":"0731-2472121-23-24"},
	             {"Name":"अरिहंत हॉस्पिटल एंड रिसर्च सेंटर ","Address":" सुदामा नगर ","PhoneNumber":" 0731-2483754 "},
	             {"Name":"आरोग्य नर्सिंग होम ","Address":" 156 नयापुरा मेन रोड, घड़ीवाली मस्जिद के सामने ","PhoneNumber":"0731-2538184, 2547590"},
	             {"Name":"अर्पण नर्सिंग होम ","Address":" 151/2 इमली बाजार,राजवाडा  ","PhoneNumber":" 0731-2433911, 93032-71447  "},
	             {"Name":"आकांक्षा नर्सिंग होम ","Address":" सुदामा नगर ","PhoneNumber":" 0731-2483754 "},
	             {"Name":"आदित्य नर्सिंग होम","Address":"7,सिंधु नगर,भंवरकुआ रोड ","PhoneNumber":"0731-2483311, 4045239"},
	             {"Name":"आनंद हॉस्पिटल एवं रिसर्च सेंटर ","Address":" सुदामा नगर ","PhoneNumber":"0731-2472121-23-24"},
	             {"Name":"अरिहंत हॉस्पिटल एंड रिसर्च सेंटर ","Address":" सुदामा नगर ","PhoneNumber":" 0731-2483754 "},
	             {"Name":"आरोग्य नर्सिंग होम ","Address":" 156 नयापुरा मेन रोड, घड़ीवाली मस्जिद के सामने ","PhoneNumber":"0731-2538184, 2547590"},
	             {"Name":"अर्पण नर्सिंग होम ","Address":" 151/2 इमली बाजार,राजवाडा  ","PhoneNumber":" 0731-2433911, 93032-71447  "}];
	
	 	var dataTableColumns=[{"sTitle":"Name","bWidth":"30%","mData":"Name","mRender":function (jsonObj,type,row){
			return row.Name;
			}},
			{"sTitle":"Address","bWidth":"40%","mData":"Address","mRender":function (jsonObj,type,row){
			return row.Address;
			}},
			{"sTitle":"Phone Number","bWidth":"30%","mData":"PhoneNumber","mRender":function (jsonObj,type,row){
			return row.PhoneNumber;
			}}];
		var divId="hospitalList";
		var tableId="hospitalListDataTableId";
		var tableSize="100";
		
		
	             					 	
    $("#hospitalListButton").click(function(){
/*        $.ajax({url: "Hospital-List.txt", success: function(result){
            $("#hospitalList").html(result);
        }});
*/
    	$('#'+divId).html( '<table  cellpadding="0" cellspacing="0" border="0" class="display table table-bordered tableHeaderColor" id="'+tableId+'" style="width:'+tableSize+'%;"></table>' );
				jQuery('#'+tableId +' thead tr').each( function () 
				{
						this.insertBefore(VIEW_DETAIL_SPAN_TH, this.childNodes[0] );
				});

				jQuery('#'+tableId +' tbody tr').each( function () 
				{
						this.insertBefore(VIEW_DETAIL_SPAN_TD.cloneNode( true ), this.childNodes[0] );
				});
		

    	var	dataTableElement=$('#'+tableId).dataTable(
				{	
					
					//~ "bFilter":false,
					"bInfo": true,			//this will show searcg box when set to true
					"bScrollCollapse": true,
					"bAutoWidth":false,
					"bPaginate": true,
					//~ "sDom":'Rlftrip',
					//"bJQueryUI": true,		
					"aaData": jsonObj,
					//~ "bLengthChange": false, 
					"bSort":true,
					"aoColumns": dataTableColumns			
					} );	
						//$("#"+tableId).removeClass("display table table-bordered tableHeaderColor");
						//$("#dataTableElement").removeClass("table-striped");
				});
    /************************     For Data Table for List of Hospitals code end   *******************************/
    function checkUserIdAvailabilty(){
    	var emailId_register = $("#emailId_register").val();
    	emailId_register = hasValue(emailId_register) ? emailId_register : "-";	
       	sendGETRequest(context+"/rest/UserDetails/checkUserIdAvailabilty/"+emailId_register,"checkUserIdAvailabiltyCallBack");

    }
    function checkUserIdAvailabiltyCallBack(XMLHttpRequest, data, rpcRequest) {
		if (XMLHttpRequest.status == 200) {
			if (XMLHttpRequest.responseText=="Email Id not exist") {
				console.log("Email Id not exist");
				userRegistration();
			}
			else{
				console.log("Email Id already exist");
			}
		}
    }
    function userRegistration(actionType, id){
    	var elementId = "register";
    	if(hasValue(id)){
    		elementId = id;
    	}
    	var first_name_register = $("#first_name_"+elementId).val();
    	  first_name_register = hasValue(first_name_register) ? first_name_register : "-";
    	var last_name_register = $("#last_name_"+elementId).val();
    	last_name_register = hasValue(last_name_register) ? last_name_register : "-";
    	var bloodGroup_register = $("#bloodGroup_"+elementId).val();
    	  bloodGroup_register = hasValue(bloodGroup_register) ? bloodGroup_register : "-";
    	var userType_register = $("#userType_"+elementId).val();
    	  userType_register = hasValue(userType_register) ? userType_register : "-";
    	var address_register = $("#address_"+elementId).val();
    	  address_register = hasValue(address_register) ? address_register : "-";
    	var age_register = $("#age_"+elementId).val();
    	age_register = hasValue(age_register) ? age_register : "-";
    	var weight_register = $("#weight_"+elementId).val();
    	weight_register = hasValue(weight_register) ? weight_register : "-";
    	var areaCode_register = $("#areaCode_"+elementId).val();
    	  areaCode_register = hasValue(areaCode_register) ? areaCode_register : "-";
    	var phoneNumber_register = $("#phoneNumber_"+elementId).val();
    	  phoneNumber_register = hasValue(phoneNumber_register) ? phoneNumber_register : "-";
    	var emailId_register = $("#emailId_"+elementId).val();
    	  emailId_register = hasValue(emailId_register) ? emailId_register : "-";
    	var areaCode_register = $("#areaCode_"+elementId).val();
    	 areaCode_register = hasValue(areaCode_register) ? areaCode_register : "-";
    	 
    	 var donationcount_register = $("#donationcount_"+elementId).val();
    	 donationcount_register = hasValue(donationcount_register) ? donationcount_register : "-";
    	 var createdon_register = $("#createdon_"+elementId).val();
    	 createdon_register = hasValue(createdon_register) ? createdon_register : "-";
    	/* var lastdonation_register = $("#lastdonation_"+elementId).val();
    	 lastdonation_register = hasValue(lastdonation_register) ? lastdonation_register : "-";*/
    	 var password_register = $("#password_"+elementId).val();
    	 password_register = hasValue(password_register) ? password_register : "-";
    	  
    	var json = '{"username":"' + first_name_register+" "+last_name_register
    				+ '","bloodgroup":"' + bloodGroup_register
    				+ '","address":"' + address_register
    				+ '","age":"' + age_register
    				+ '","weight":"' + weight_register
    				+ '","areaCode":"' + areaCode_register
    				+ '","phonenumber":"' + phoneNumber_register
    				+ '","emailId":"' + emailId_register;

    		if(actionType=="Update"){
    			 /*var password = $("#emailId_"+elementId).val();
    	    	  emailId_register = hasValue(emailId_register) ? emailId_register : "-";*/
    			var createdDate;
    	    	 var isAvailable_updateProfile = $("#isAvailable_updateProfile").val();
    	    	 isAvailable_updateProfile = hasValue(isAvailable_updateProfile) ? isAvailable_updateProfile : "YES";
    			json += '","isavailable":"' + isAvailable_updateProfile;
    			json  += '","usertype":"' + LOGGED_USER_TYPE;
    			json  += '","id":"' + LOGGED_USER_ID;
    			json  += '","donationcount":"' + donationcount_register;
    			if(createdon_register!="-"){
    				createdon_register = Number(createdon_register);
    				createdDate = formatDateAsyyyyMMdd(createdon_register,"-",true);
    				json  += '","createdon":"' + createdDate;
    			}
    			else{
    				json  += '","createdon":"' + new Date();
    			}
    			/*if(lastdonation_register=="-"){
    				lastdonation_register = null;
    			}
    			json  += '","lastdonation":"' + lastdonation_register;*/
    			json  += '","password":"' + password_register;
    			
    			json += '"}';
    			
    			console.log("created date : "+createdDate)
    			swal({
				title : "Are you sure?",
				text : "You are going to update your profile!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, update it!",
				cancelButtonText : "No, cancel it!",
				closeOnConfirm : false,
				closeOnCancel : false
			},
					function(isConfirm) {
						if (isConfirm) {
							sendPOSTRequest(context+"/rest/UserDetails/updateUserProfile",json,"updateUserProfileCallBack");
						} else {
							swal("Cancelled", "Your profile is not modified :)",
									"error");
						}
					});
    	       	
    		}
    		else{
    			json += '","usertype":"' + userType_register
    			json += '"}';
    			console.log("created date : "+createdDate)

				sendPOSTRequest(context+"/rest/UserDetails/userRegistration",json,"getUserRegistrationCallBack");

    			/*    			swal({
				title : "Are you sure?",
				text : "All your informations are correct!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, create my profile!",
				cancelButtonText : "No, cancel it!",
				closeOnConfirm : false,
				closeOnCancel : false
			},
					function(isConfirm) {
						if (isConfirm) {
							alert("confirm")
							sendPOSTRequest(context+"/rest/UserDetails/userRegistration",json,"getUserRegistrationCallBack");
						} else {
							swal("Cancelled", "Your profile is not created :(",
									"error");
						}
					});

*/    	       	
    		}
    }
    
    function getUserRegistrationCallBack(XMLHttpRequest, data, rpcRequest) {
    			if (XMLHttpRequest.status == 200) {
    				if (XMLHttpRequest.responseText=="Registration Successfull") {
    					console.log("Registration Successfull");
    					$("#UserRegistrationModal").modal("hide");
    					swal("Created!",
    							"Your profile has been created. :)",
    							"success");
    				}
    				else{
    					console.log("Registration Failed");
    				}
    		}
    	}
    function updateUserProfileCallBack(XMLHttpRequest, data, rpcRequest) {
		if (XMLHttpRequest.status == 200) {
			if (XMLHttpRequest.responseText=="Profile Update Successfull") {
				console.log("Profile Update Successfull");
				swal("Updated!",
						"Your profile has been updated. :)",
						"success");

			}
			else{
				console.log("Profile Update Failed");
				swal("Error!",
						"Your profile has not been updated.",
						"error");
			}
		}
    }
     
    function openMyProfileTab(){
    	var stringvalue = '<li>\
            <i class="fa fa-home"></i>  <a href="#" onclick = "backToHomePage();">Home</a>\
          </li>\
          <li>\
            <i class="fa fa-user"></i> Profile\
          </li>';
		$(".breadcrumb").html(stringvalue);
		$("#profilePageSubHeader").text("Profile");
		$("#myActiveRequestTab").hide();
		$("#changePasswordTab").hide();
		$("#myProfileTab").show();
		$(".disableInputElementsOnPageLoad").css("pointer-events","none");
		$(".disableInputElementsOnPageLoad").css("background-color","#D0D0D0");
		
		//$(".disableSelectPickerOnPageLoad").css("pointer-events","none");
		$(".dropdown-toggle").css("pointer-events","none");
		$("#submit_updateProfile_button").css("pointer-events","none");
    }
    function openMyActiveRequestTab(){
    	var stringvalue = '<li>\
            <i class="fa fa-home"></i>  <a href="#" onclick = "backToHomePage();">Home</a>\
          </li>\
          <li>\
            <i class="fa fa-fw fa-arrows-v"></i> My Activity\
          </li><li class="active">\
          <i class="fa fa-hand-o-right"></i> Request\
			</li>';
		$(".breadcrumb").html(stringvalue);
		$("#profilePageSubHeader").text("Active Request");
		$("#myProfileTab").hide();
		$("#changePasswordTab").hide();
		$("#myActiveRequestTab").show();
		$("#profileSection").show();
    	
    }
    function backToHomePage(){
    	//$(".hideAfterLogin").show();
    	$("#profileSection").show();
    	$("#changePasswordTab").hide();
    	//$("#profileSection").css("margin-top","-17%");
    	$("#profile-wrapper").css("display","none");
    	$("#mainContentFrame").show();
    }
    function openChangePasswordTab(){
    	$("#myProfileTab").hide();
		$("#myActiveRequestTab").hide();
    	$("#changePasswordTab").show();
    }
    function changePassword(){
    	console.log("Change Password");
    	var newPassword = $("#newPassword").val();
    	var confirmPassword = $("#confirmPassword").val();
    	var oldPassword = $("#oldPassword").val();
    	if(hasValue(newPassword) && hasValue(confirmPassword) && hasValue(oldPassword)){
    		if(confirmPassword == newPassword){
    			sendGETRequest(context+"/rest/UserDetails/changeUserPassword/"+LOGGED_USER_EMAILID+"/"+oldPassword+"/"+newPassword,"changeUserPasswordCallBack");
    		}
    		else{
    			swal({   title: "New Password and Confirm Passwords are not same!",   text: "Please check once.",   timer: 3000 });
    			return false;
    		}
    	}
    	else{
    		swal({   title: "Please fill all fields!",   text: "",   timer: 3000 });
    		return false;
    	}
    }
    function changeUserPasswordCallBack(XMLHttpRequest, data, rpcRequest) {
		if (XMLHttpRequest.status == 200) {
			if (XMLHttpRequest.responseText=="Password Changed Successfully") {
				console.log("Password Changed Successfully");
				swal("Done!",
						"Your password has been changed. :)",
						"success");
			}
			else{
				console.log("Password Change Failed");
				swal("Fail!",
						"Please Enter Valid Credentials :)",
						"error");
			}
	}
}
    function editProfile(){
    	//$(".disableSelectPickerOnPageLoad").css("pointer-events","auto");
    	$(".dropdown-toggle").css("pointer-events","auto");
    	$(".disableInputElementsOnPageLoad").css("background-color","#fff");
    	$(".disableInputElementsOnPageLoad").css("pointer-events","auto");
    	$("#submit_updateProfile_button").css("pointer-events","auto");
    }
    function showUserProfile(){
    	$("#mainContentFrame").hide();
		$("#profileSection").hide();
		$("#mainNav").css("z-index","10000");
		$("#profile-wrapper").show();
		
		$("#myActiveRequestTab").hide();
		$("#myProfileTab").show();
		var stringvalue = '<li>\
              <i class="fa fa-home"></i>  <a href="#" onclick = "backToHomePage();">Home</a>\
            </li>\
            <li>\
              <i class="fa fa-user"></i> Profile\
            </li>';
		$(".breadcrumb").html(stringvalue);
		$("#profilePageSubHeader").text("Profile");
		$(".disableInputElementsOnPageLoad").css("pointer-events","none");
		//$(".disableSelectPickerOnPageLoad").css("pointer-events","none");
		$(".dropdown-toggle").css("pointer-events","none");
		$("#submit_updateProfile_button").css("pointer-events","none");
		
		if(hasValue(LOGGED_USER_EMAILID)){
			sendGETRequest(context+"/rest/UserDetails/getUserDetails/"+LOGGED_USER_EMAILID,"getUserDetailsCallBack");
		}
    }
    function getUserDetailsCallBack(XMLHttpRequest, data, rpcRequest) {
    	if (XMLHttpRequest.status == 200) {
			if (hasValue(data)) {
				console.log("Success");
				console.log(data)
				LOGGED_USER_TYPE = data.usertype;
				LOGGED_USER_ID = data.id;
				var first_name_register = "-";
				var last_name_register = "-";
				var userName = hasValue(data.username) ? data.username : "-";
			if(userName!="-"){
				userName = userName.split(" ");
				first_name_register = userName[0];
				last_name_register = userName[1];
			}
    	var bloodGroup_register = hasValue(data.bloodgroup) ? data.bloodgroup : "-";
    	var isavailable = hasValue(data.isavailable) ? String(data.isavailable) : "-";
    	var address_register = hasValue(data.address) ? data.address : "-";
    	var age_register = hasValue(data.age) ? data.age : "-";
    	var weight_register = hasValue(data.weight) ? data.weight : "-";
    	var areaCode_register= hasValue(data.areaCode) ? data.areaCode : "-";
    	var phoneNumber_register= hasValue(data.phonenumber) ? data.phonenumber : "-";
    	var emailId_register = hasValue(data.emailId) ? data.emailId : "-";
    	
    	var donationcount = hasValue(data.donationcount) ? data.donationcount : "-";
    	var createdon= hasValue(data.createdon) ? data.createdon : "-";
    	var lastdonation= hasValue(data.lastdonation) ? data.lastdonation : "-";
    	var password = hasValue(data.password) ? data.password : "-";

    	$("#first_name_updateProfile").val(first_name_register);
    	$("#last_name_updateProfile").val(last_name_register);
    	$("#bloodGroup_updateProfile").selectpicker('val',bloodGroup_register).selectpicker("refresh");
    	$("#isAvailable_updateProfile").selectpicker('val',isavailable).selectpicker("refresh");
    	$("#address_updateProfile").val(address_register);
    	$("#age_updateProfile").val(age_register);
    	$("#weight_updateProfile").val(weight_register);
    	$("#areaCode_updateProfile").val(areaCode_register);
    	$("#phoneNumber_updateProfile").val(phoneNumber_register);
    	$("#emailId_updateProfile").val(emailId_register);
    	
	     $("#donationcount_updateProfile").val(donationcount);
	   	 $("#createdon_updateProfile").val(createdon);
	   	 $("#lastdonation_updateProfile").val(lastdonation);
	   	 $("#password_updateProfile").val(password);
			}
			else{
				console.log("Fail");
			}
		}
    }
    function updateUserProfile(){
    	
    	
    }
    function showActiveRequests(){
		$("#mainContentFrame").hide();
		$("#profileSection").hide();
		$("#mainNav").css("z-index","10000");
		$("#profileMyActivityMenu").siblings().removeClass("active");
		$("#profileMyActivityMenu").addClass("active");
		$("#profileMyActivityMenu").children("a").click();
		var stringvalue = '<li>\
              <i class="fa fa-home"></i>  <a href="#" onclick = "backToHomePage();">Home</a>\
            </li>\
            <li>\
              <i class="fa fa-fw fa-arrows-v"></i> My Activity\
            </li><li class="active">\
            <i class="fa fa-hand-o-right"></i> Request\
			</li>';
		$(".breadcrumb").html(stringvalue);
		$("#profile-wrapper").show();
		$("#myProfileTab").hide();
		$("#myActiveRequestTab").show();
		$("#profilePageSubHeader").text("Active Request");
		if(LOGGED_USER_TYPE == "DONNER"){
			sendGETRequest(context+"/rest/UserRequest/getUserActiveRequest/"+LOGGED_USER_BLOOD_GROUP,"getUserActiveRequestCallBack");	
		}
		else{
			sendGETRequest(context+"/rest/UserRequest/getUserSubmittedRequest/"+LOGGED_USER_ID,"getUserSubmittedRequestCallBack");
		}
    }
    function getUserActiveRequestCallBack(XMLHttpRequest, data, rpcRequest) {
    	if (XMLHttpRequest.status == 200) {
    		if (hasValue(data)) {
    			console.log("Request Found Successfully");
    		 	var dataTableColumns=[{"sTitle":"Name","bWidth":"20%","mData":"Name","mRender":function (data,type,row){
    				return row.username;
    				}},
    				{"sTitle":"Address","bWidth":"30%","mData":"Address","mRender":function (data,type,row){
    				return row.address;
    				}},
    				{"sTitle":"Phone Number","bWidth":"20%","mData":"PhoneNumber","mRender":function (data,type,row){
    				return row.phonenumber;
    				}},
    				{"sTitle":"Request Date","bWidth":"20%","mData":"Request Date","mRender":function (data,type,row){
        			return appenDateInIMEITable(row.creationtime);
        			}},
    				{"sTitle":"Blood Group","bWidth":"10%","mData":"Blood Group","mRender":function (data,type,row){
            		return row.bloodgroup;
            		}},
    				{"sTitle":"Status","bWidth":"10%","mData":"Status","mRender":function (data,type,row){
                		return row.status;
                	}},
        			{"sTitle":"Description","bWidth":"20%","mData":"Description","mRender":function (data,type,row){
                    return row.description;
                    }}
    				];
    		 	
    		 	
    			var divId="myActiveRequestTab";
    			var tableId="myActiveRequestDataTableId";
    			var tableSize="100";
    			
    			$('#'+divId).html( '<table  cellpadding="0" cellspacing="0" border="0" class="display table table-bordered tableHeaderColor" id="'+tableId+'" style="width:'+tableSize+'%;"></table>' );
				jQuery('#'+tableId +' thead tr').each( function () 
				{
						this.insertBefore(VIEW_DETAIL_SPAN_TH, this.childNodes[0] );
				});

				jQuery('#'+tableId +' tbody tr').each( function () 
				{
						this.insertBefore(VIEW_DETAIL_SPAN_TD.cloneNode( true ), this.childNodes[0] );
				});
		

    	var	dataTableElement=$('#'+tableId).dataTable(
				{	
					"bInfo": true,			//this will show searcg box when set to true
					"bScrollCollapse": true,
					"bAutoWidth":false,
					"bPaginate": true,
					"aaData": data,
					"bSort":true,
					"aoColumns": dataTableColumns			
					});	

    		}
    		else{
    			console.log("No Request Found");
    			$("#myActiveRequestTab").html("No Request Found");
    		}
    		
    }
    }
    function getUserSubmittedRequestCallBack(XMLHttpRequest, data, rpcRequest) {
    	
    	if (XMLHttpRequest.status == 200) {
    		if (hasValue(data)) {
    			console.log("Request Found Successfully");
    		 	var dataTableColumns=[{"sTitle":"Name","bWidth":"20%","mData":"Name","mRender":function (data,type,row){
    				return row.username;
    				}},
    				{"sTitle":"Address","bWidth":"30%","mData":"Address","mRender":function (data,type,row){
    				return row.address;
    				}},
    				{"sTitle":"Phone Number","bWidth":"20%","mData":"PhoneNumber","mRender":function (data,type,row){
    				return row.phonenumber;
    				}},
    				{"sTitle":"Request Date","bWidth":"20%","mData":"Request Date","mRender":function (data,type,row){
        			return appenDateInIMEITable(row.creationtime);
        			}},
    				{"sTitle":"Blood Group","bWidth":"10%","mData":"Blood Group","mRender":function (data,type,row){
            		return row.bloodgroup;
            		}},
    				{"sTitle":"Status","bWidth":"10%","mData":"Status","mRender":function (data,type,row){
                		return row.status;
                	}},
        			{"sTitle":"Description","bWidth":"20%","mData":"Description","mRender":function (data,type,row){
                    return row.description;
                    }}
    				];
    		 	
    		 	
    			var divId="myActiveRequestTab";
    			var tableId="myActiveRequestDataTableId";
    			var tableSize="100";
    			
    			$('#'+divId).html( '<table  cellpadding="0" cellspacing="0" border="0" class="display table table-bordered tableHeaderColor" id="'+tableId+'" style="width:'+tableSize+'%;"></table>' );
				jQuery('#'+tableId +' thead tr').each( function () 
				{
						this.insertBefore(VIEW_DETAIL_SPAN_TH, this.childNodes[0] );
				});

				jQuery('#'+tableId +' tbody tr').each( function () 
				{
						this.insertBefore(VIEW_DETAIL_SPAN_TD.cloneNode( true ), this.childNodes[0] );
				});
		

    	var	dataTableElement=$('#'+tableId).dataTable(
				{	
					"bInfo": true,			//this will show searcg box when set to true
					"bScrollCollapse": true,
					"bAutoWidth":false,
					"bPaginate": true,
					"aaData": data,
					"bSort":true,
					"aoColumns": dataTableColumns			
					});	

    		}
    		else{
    			console.log("No Request Found");
    			$("#myActiveRequestTab").html("No Request Found");
    		}
    		
    }
    }
    function getUserLogOut(){
    	swal({
			title : "Are you sure?",
			text : "You are going to log out!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Yes, do it!",
			cancelButtonText : "No, cancel it!",
			closeOnConfirm : false,
			closeOnCancel : false
		},
				function(isConfirm) {
					if (isConfirm) {
						swal("Logged Out!",
								"Your are successfully logged out. :)",
								"success");
						$("#profileSection").hide();
						$(".hideAfterLogin").show();
						HAS_USER_LOGGED_OUT = true;
						HAS_USER_LOGGED_IN = false;
				    	$("#profile-wrapper").css("display","none");
				    	$("#mainContentFrame").show();

					} else {
						swal("Cancelled", "Your are not log out :)",
								"error");
					}
				});
    	
    }
    
    function submitRequest(){
		    var first_name_request = $("#first_name_request").val();
		  	first_name_request = hasValue(first_name_request) ? first_name_request : "-";
		  	var last_name_request = $("#last_name_request").val();
		  	last_name_request = hasValue(last_name_request) ? last_name_request : "-";
		  	var bloodGroup_request = $("#blood_group_request").val();
		  	  bloodGroup_request = hasValue(bloodGroup_request) ? bloodGroup_request : "-";
		  	var phoneNumber_request = $("#phoneNumber_request").val();
		  	phoneNumber_request = Number(hasValue(phoneNumber_request)) ? phoneNumber_request : "-";
		  	var address_request = $("#address_request").val();
		  	  address_request = hasValue(address_request) ? address_request : "-";
		  	var additional_request = $("#additional_request").val();
		  	additional_request = hasValue(additional_request) ? additional_request : "-";
		  	var loggedUserId = hasValue(LOGGED_USER_ID) ? LOGGED_USER_ID : "null";
		  	var json = '{"username":"' + first_name_request+" "+last_name_request
		  				+ '","bloodgroup":"' + bloodGroup_request
		  				+ '","address":"' + address_request
		  				+ '","phonenumber":"' + phoneNumber_request
		  				+ '","description":"' + additional_request
		  				//+ '","user":{ "id":' + loggedUserId+"}}";
		  				+  '","userid":' + loggedUserId +"}";
		  				
		  	if(hasValue(bloodGroup_request) && bloodGroup_request!= "-"){
		  		sendPOSTRequest(context+"/rest/UserRequest/getUserRequest",json,"getUserRequestCallBack");
		  	}
    	
    }
    function getUserRequestCallBack(XMLHttpRequest, data, rpcRequest) {
		if (XMLHttpRequest.status == 200) {
			if (XMLHttpRequest.responseText=="Request Submitted Successfully") {
				console.log("Request Submitted Successfully");
				swal("Done!",
						"Your request has been submitted. :)",
						"success");
				$("#BloodSickerModal").modal("hide");
			}
			else{
				console.log("Request Submission Failed");
				swal("Fail!",
						"Request Already Registered:(",
						"error");
			}
	}
}
    function changeUserPasswordCallBack(XMLHttpRequest, data, rpcRequest) {
		if (XMLHttpRequest.status == 200) {
			if (XMLHttpRequest.responseText=="Password Changed Successfully") {
				console.log("Password Changed Successfully");
				swal("Done!",
						"Your password has been changed. :)",
						"success");
			}
			else{
				console.log("Password Change Failed");
				swal("Fail!",
						"Please Enter Valid Credentials :)",
						"error");
			}
	}
}
    
    function appenDateInIMEITable(dt) {
        var separator = "/";
        var newDate = new Date(dt);
        var date = newDate.getFullYear();
        var month = parseInt(newDate.getMonth()) + 1;
        if (month < 10)
            month = "0" + month;
        var dateNumber = newDate.getDate();
        if (dateNumber < 10) {
            dateNumber = "0" + dateNumber;
        }
        var hour = newDate.getHours();
        var minutes = newDate.getMinutes();
        var seconds = newDate.getSeconds();
        var ampm = hour >= 12 ? 'PM' : 'AM';
        hour = hour % 12;
        hour = hour ? hour : 12;
        var time = getTimeFormat(hour) + ":" + getTimeFormat(minutes) + ' ' + ampm;
        var ddyymmhhmm = dateNumber + separator + month + separator + date + ", " + time;
        return ddyymmhhmm;
    }

    function getTimeFormat(val) {
    	val = val + "";
    	if (val.length == 1)
    		return "0" + val;
    	return val;
    }
