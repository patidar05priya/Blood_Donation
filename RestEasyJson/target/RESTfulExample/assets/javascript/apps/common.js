var context = "http://localhost:8089/RESTfulExample";
function sendGETRequest(url, callbackFunc, addlParam) {
	alert(url+"  "+callbackFunc+"  "+addlParam)
	//setCsrfCookie();
	$.ajax({
		type: "GET",
		url: url,
		cache: false,
		//  headers:{"csrfToken":getCookieClosePortal()},
		headers: {
			"csrfToken": ""//getCookieClosePortal()
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			alert("in error")
			if (isAuthorize(XMLHttpRequest, textStatus, errorThrown, url, callbackFunc)) {
				alert("in if error")
				eval(callbackFunc + "(arguments[0], arguments[2].responseText, arguments[2], \"" + addlParam + "\")");
			} else {
				alert("in else error")
				return false;
			}
		},
		success: function (data, status, request) {
			alert("in success")
			if (isAuthorize(request, status, data, url, callbackFunc)) {
				alert("in if success")
				eval(callbackFunc + "(arguments[2], arguments[0], arguments[1], \"" + addlParam + "\")");
			} else {
				alert("in if success")
				return false;
			}
		}
	});
	//hasSession("#logoutform");
	//delete_cookie('csrfToken');
}

/*generic function to check the ajax response*/
function isAuthorize(rpcResponse, data, rpcRequest, callbackFunc) {
	try {
		if (hasValue(rpcResponse.responseText)) {
			var sessionString = rpcResponse.responseText.substring(0, 70);
			sessionString = sessionString.toLowerCase();
			if (sessionString.indexOf("<!doctype html>") != -1) {
				window.location.href = context + "/assets/home.html";
				return false;
			}
		}
		if (rpcResponse.status == 0) {
			//showFailureMessage ("Logout");
			return false;
		}
		if (rpcResponse.status == 500) {
			//showFailureMessage(gettingDetails_Error);
			return false;
		} else if (rpcResponse.status == 404) {
			//showFailureMessage(gettingDetails_Error);
			return false;
		} else if (rpcResponse.status == 401) {
			/*Session expire message*/
			//showFailureMessage(gettingDetails_Error);
			return false;
		}
	} catch (e) {}
	return true;
}

function hasValue(val) {
	return (val != null && val != undefined && val != "null" && val != "NULL" && val != "undefined" && (val != "" || String(val) == "0"));
}
