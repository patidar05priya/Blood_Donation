var context = "http://localhost:8089/RESTfulExample";
function getDetails(){
alert("Hello")
sendGETRequest(context+"/rest/json/product/get","callback","addlParam");
$();
}

function getUserName(){
alert("User Name  "+$("#UserName").val())
}

function callback(XMLHttpRequest, data, rpcRequest) {
alert("in callback")
	
		if (XMLHttpRequest.status == 200) {
			if (hasValue(data)) {
				alert("data  "+JSON.stringify(data))
				$("#productName").val(data.name);
				$("#quantity").val(data.qty);
			}
		
	}
}
