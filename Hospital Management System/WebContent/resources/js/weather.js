$(document).ready(function(){
    	$("#weatherInfo").hide(); 
    	$("#refreshButton").hide();
    });

function reloadWeather(){
	$("#temp").empty();
	$("#location").empty();
	$("#city").empty();
	$("#state").empty();
	$("#country").empty();
	$("#weather").empty();
	$("#humidity").empty();
	$("#pressure").empty();
	$("#lastUpdate").empty();
	getLocationWeatherInfo();
	
}
function getLocationWeatherInfo(){
	var myIp="";var myCity="";var myLatVal="";var myLongVal="";var myregion="";var myWeatherDesc="";var myLocation="";var temp=0;var pressure="";
	var humidity="";var lastUpdate="";var myCountry="";
    $.get("https://ipinfo.io", function(response) {
    	//console.log(JSON.stringify(response, null, 2));
    	myIp=response.ip;myCity=response.city;myLatVal=response.loc.split(",")[0];myLongVal=response.loc.split(",")[1];
    	myregion=response.region;myCountry=response.country;
    	//console.log(myIp); 
    $.getJSON('//api.openweathermap.org/data/2.5/weather?lat='+myLatVal+'&lon='+myLongVal+'&appid=e8160c1f4029da765cceffc163a54495',function(data){
      		//console.log(JSON.stringify(data, null, 2));
      		myWeatherDesc=data.weather[0].description;
      		myLocation=data.name;
      		temp=Math.round(data.main.temp-273.15);
      		pressure=data.main.pressure+"mm of Hg.";
      		humidity=data.main.humidity+"%";
      		//console.log(myWeatherDesc+","+myLocation+","+temp+","+pressure+","+humidity);
      		var timestamp = new Date();
      		lastUpdate=timestamp.toLocaleString();
      		//console.log(lastUpdate);
      	       	
    
    //console.log(myIp);    
    Map={"ip":myIp,
    		"city":myCity,
    		"latitude":myLatVal,
    		"country":myCountry,
    		"longitude":myLongVal,
    		"state":myregion,
    		"weather":myWeatherDesc,
    		"location":myLocation,
    		"temp":temp,
    		"pressure":pressure,
    		"humidity":humidity,
    		"lastUpdate":lastUpdate};
    //console.log(Map);
    //console.log("temp" +Map.temp);
    $("#temp").append(Map.temp).append("&deg;C");
	$("#location").append(Map.location);
	$("#city").append(Map.city);
	$("#state").append(", ").append(Map.state);
	$("#country").append(", ").append(Map.country);
	$("#weather").append("with ").append(Map.weather);
	$("#humidity").append("Humidity: ").append(Map.humidity);
	$("#pressure").append("Pressure: ").append(Map.pressure);
	$("#lastUpdate").append("last updated @ ").append(Map.lastUpdate);
	$("#weatherInfo").show();
	$("#refreshButton").show();
    /*printWeatherInfo(Map);*/
    }); }, "jsonp");
    $("#getWeatherButton").hide();
    
};