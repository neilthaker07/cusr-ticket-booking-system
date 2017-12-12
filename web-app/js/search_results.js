/**
 * Created by bhaktishah on 12/3/17.
 */
var stations=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
var express_trains = ['A','F','K','P','U','Z'];
var departure_times = {A:["9:00 AM","9:15 AM","9:30 AM"]};

$(function(){

  for(var i=0;i<stations.length;i++)
    {
        var option="<option value='"+stations[i]+"'>"+stations[i]+"</option>";
        $("#departure_station").append(option);
    }

//Depending on what departure station is chosen, populate arrival station and departure time.
    $("#departure_station").change(function () {
        var departure_station_selected = $(this).val();

        for(var i=0;i<stations.length;i++)
        {
            if(stations[i]!=departure_station_selected)
            {
                var option="<option value='"+stations[i]+"'>"+stations[i]+"</option>";
                $("#arrival_station").append(option);
            }
        }
        if(departure_times.hasOwnProperty(departure_station_selected))
        {
            var dep_times =  departure_times[departure_station_selected];
            for(var i=0;i<dep_times.length;i++)
            {
                var option="<option value='"+dep_times[i]+"'>"+dep_times[i]+"</option>";
                $("#departure_time").append(option);

            }
            
        }
    });
    //check if round trip is selected, if yes, show arrival date option else keep it hidden.
    $("#checkbox_round_trip").on("click",function(){
        //console.log ("called");
        //console.log($(this).val() + "valueee");
        if($(this).is(":checked"))
        {
            $("#round_trip_div").show();
        }
        else
        {
            $("#round_trip_div").hide();
        }
    });

    //Departure Date select, if rount trip selected, limit the date selection to 7 days
    $("#departure_date").change(function(){
        if($("#checkbox_round_trip").is(":checked"))
        {
            var date_selected =  $(this).val();
            var myDate =  new Date(date_selected);
            myDate.setDate(myDate.getDate()+7);
            my_date = myDate.getFullYear().toString()+"-" + (myDate.getMonth()+1).toString()+"-" + myDate.getDate().toString();
            document.getElementById("arrival_date").max = my_date;
        }

    });









//Search for the ticket options with given criteria.
    $("#search_btn").on("click",function(){
        var departure_station = $("#departure_station").val();
        var arrival_station = $("#arrival_station").val();
        var departure_time = $("#departure_time").val();
        var departure_date = $("#departure_date").val();

        if(departure_station == "" || departure_date == "" || departure_time == "" || arrival_station == "")
        {
            alert ( " please enter required fields");

        }

    });

});


// variable to show on the train page.
var jsonVar = {
	"train_no":"SB0700",
	"from_station":"A",
	"to_station":"G"
};
/*
var xhttp = new XMLHttpRequest();
xhttp.open("GET", "localhost:8080/availableRoutes", false);
xhttp.setRequestHeader("Content-type", "application/json");
xhttp.send();
var response = JSON.parse(xhttp.responseText);

console.log("All availableRoutes : "+ JSON.stringify(response));
*/
var output = document.getElementById('output');
output.innerHTML = jsonVar.train_no + " " +jsonVar.from_station + " "+jsonVar.to_station ;

