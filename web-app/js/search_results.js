/**
 * Created by bhaktishah on 12/3/17.
 */
var stations=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
var express_trains = ['A','F','K','P','U','Z'];
var departure_times = {A:[900,915,930]};
var train_type_selected="any";
var train_con_num = "any";
var exact_time = false;
var dep_station_ind=-1;
var arr_station_ind=-1;

$(function(){
    $('.radio_train').change(function()
    {
        train_type_selected = $(this).val();
        if($("#arrival_station").val()=="" || $("#departure_station").val()=="" || Math.abs(arr_station_ind-dep_station_ind)>=5)
        {
            console.log("pr");
        }
        else
        {
            alert("No available express trains between this stations please select valid stations");
                $("#departure_station").val("");
                $("#arrival_station").val("");

        }

            
    });

    $('.radio_conn_num').change(function () {
        train_con_num = $(this).val();
    });
for(var i=0;i<stations.length;i++)
    {
        var option="<option value='"+stations[i]+"'>"+stations[i]+"</option>";
        $("#departure_station").append(option);
    }


 

//Depending on what departure station is chosen, populate arrival station and departure time.
    $("#departure_station").change(function () {
        $("#arrival_station").find('option').remove();
         var option="<option value=''>--Please Select Arrival Station--</option>";
         $("#arrival_station").append(option);

        var departure_station_selected = $(this).val();
        for(var i=0;i<stations.length;i++)
        {
            if(stations[i]!=departure_station_selected)
            {
               
                var option="<option value='"+stations[i]+"'>"+stations[i]+"</option>";
                $("#arrival_station").append(option);
            }
            else
            {
                 dep_station_ind = i;
            }
             $("#arrival_station").val("");

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
    //Check if exact time is changed or not
    $("#checkbox_exact_time").on("click",function(){
        exact_time = $(this).is(":checked");

    });
    //Check if express option is available or not
    $("#arrival_station").change(function()
    {
        for(var i=0;i<stations.length;i++)
        {
            if(stations[i]==$(this).val())
            {
                arr_station_ind = i;
            }
        }

        if(train_type_selected == "express")
        {
            if(Math.abs(arr_station_ind-dep_station_ind)<5)
            {
                alert("No available express trains between this stations please select valid stations");
                $("#departure_station").val("");
                $("#arrival_station").val("");
            }
        }

    });


//Search for the ticket options with given criteria.
    $("#search_btn").on("click",function(){
        var departure_station_1 = $("#departure_station").val();
        var arrival_station = $("#arrival_station").val();
        var departure_time = $("#departure_time").val();
        var departure_date = $("#departure_date").val();
        var ticket_type = train_type_selected;
        var no_of_pass = $("#no_of_passangers").val();
        var round_trip=$("#checkbox_round_trip").is(":checked");

        if(departure_station == "" || departure_date == "" || departure_time == "" || arrival_station == "")
        {
            alert ( " please enter required fields");

        }
        else
        {
            var obj = new Object();
     obj.departure_station = departure_station;
     obj.arrival_station = arrival_station;
     obj.dep_time = departure_time;
     obj.ticket_type = ticket_type;
     obj.conn_num = train_con_num;
     obj.round_tr =  round_trip;
     obj.pass_num = no_of_pass;
     obj.exact_time = exact_time;
     obj.dep_date = departure_date;

// { departure_station: departure_station_1
//     arrival_station : arrival_station,
//     dep_time : departure_time,
//     ticket_type : ticket_type,
//     conn_num : train_con_num,
//     round_tr :  round_trip,
//     pass_num : no_of_pass }
    var jsonVal = JSON.stringify(obj);

    $.ajax({
       url:  'http://localhost:8080/search',
         contentType: "application/json",
         method: "POST",
         data: JSON.stringify(jsonVal),
         dataType: 'json',
       error: function(xhr, status, error) {
          console.log(xhr.responseText);
                alert(error);
       },
       success: function(data) {
             alert("successfully");
       },
    });
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

