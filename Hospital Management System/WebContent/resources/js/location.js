
function getLocation() {
		    if (navigator.geolocation) {
		        navigator.geolocation.getCurrentPosition(initMap, showError);
		    }
		    else {
		        console.log("Geolocation is not supported by this browser.");
		    }
	  }
      function initMap(position) {
    	  console.log(position.coords.latitude+" "+position.coords.longitude);
    	  var map = new google.maps.Map(document.getElementById('map'), {        	
          center: {lat: position.coords.latitude, lng: position.coords.longitude},
          zoom: 17
        });
    	  var marker = new google.maps.Marker({
    		  position:{lat: position.coords.latitude, lng: position.coords.longitude},
    		  map:map,
    		  title:"Your location"
    	  });
      }
      function showError(error) {
    	    switch(error.code) {
    	        case error.PERMISSION_DENIED:
    	            console.log("User denied the request for Geolocation.");
    	            break;
    	        case error.POSITION_UNAVAILABLE:
    	            console.log("Location information is unavailable.");
    	            break;
    	        case error.TIMEOUT:
    	        	console.log("The request to get user location timed out.");
    	            break;
    	        case error.UNKNOWN_ERROR:
    	        	console.log("An unknown error occurred.");
    	            break;
    	    }
    	}
