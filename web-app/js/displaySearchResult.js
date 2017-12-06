/*
Author: Afreen Patel
Date: 05/12/17
*/

$(function(){


	for(var i=0;i<5;i++)
	{
		$("#search_result").append(" <form class='form-horizontal'> <div class='form-group'> <table style='width:100%''> <tr> <th>Source:</th><th>Destination: </th><th>No Of Passenger:</th></tr><tr><td>A</td><td>Z</td><td>1000</td></tr><tr><th>Departure Time:</th><th>Arrival Time: </th><th>Date: </th></tr><tr><td>9:00am</td><td>9:30am</td><td>05/12/17</td></tr><tr><th>Price:</th><th>Total Time: </th><th>Type: </th></tr><tr><td>$3</td><td>11 minute</td><td>SB</td></tr></table></div><div class='form-group'><button type='button' id='book_btn' style='color:red'>Book</button></div></form>");
	}
});