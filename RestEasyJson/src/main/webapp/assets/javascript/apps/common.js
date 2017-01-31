var context = "http://localhost:8089/RESTfulExample";
/*send json request*/
function sendPOSTRequest(url, jsonParams, callbackFunc, addlParam)
{
	console.log(jsonParams)
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        headers:{
        	"csrfParam":""//getCookieClosePortal()
        		},
        data: jsonParams,
        error: function(XMLHttpRequest, textStatus, errorThrown) 
        {
            if (isAuthorize(XMLHttpRequest, textStatus, errorThrown, url, callbackFunc)) {
                eval(callbackFunc + "(arguments[0], arguments[1], arguments[2], \"" + addlParam + "\")");
            } else {
                return false;
            }
        },
		success: function(data, status, request) {
            if (isAuthorize(request, status, data, url, callbackFunc)) {
               eval(callbackFunc + "(arguments[2], arguments[0], arguments[1], \"" + addlParam + "\")");
            } else {
                return false;
            }
        }
    });
}


function sendGETRequest(url, callbackFunc, addlParam) {
	$.ajax({
		type: "GET",
		url: url,
		contentType: "application/json; charset=utf-8",
		cache: false,
		headers: {
			"csrfToken": ""//getCookieClosePortal()
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			if (isAuthorize(XMLHttpRequest, textStatus, errorThrown, url, callbackFunc)) {
				eval(callbackFunc + "(arguments[0], arguments[2].responseText, arguments[2], \"" + addlParam + "\")");
			} else {
				return false;
			}
		},
		success: function (data, status, request) {
			if (isAuthorize(request, status, data, url, callbackFunc)) {
				eval(callbackFunc + "(arguments[2], arguments[0], arguments[1], \"" + addlParam + "\")");
			} else {
				return false;
			}
		}
	});
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
/***************************************************************
 * The fallowing method returns date in 2014-08-14 format
 * *******************************************************/
function formatDateAsyyyyMMdd(dt, separator, showYear)
{
    if (hasValue(dt)) {
        if (!hasValue(separator))
            separator = "-";
        var newDate = new Date(dt);
        var date = newDate.getFullYear() + separator;
        var month = parseInt(newDate.getMonth()) + 1;
        if(month < 10 )
			month = "0"+month ;
		var dateNumber = newDate.getDate();
		if(dateNumber < 10 )
			dateNumber = "0"+dateNumber ;
        date += month + separator + dateNumber;
        return date;
    }
    else
        return "-";
}
