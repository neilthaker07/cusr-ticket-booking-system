/*
Author: Afreen Patel
Date: 05/12/17
*/

$(function(){


	for(var i=0;i<5;i++)
	{
		if(i==0)
		{
			$("#search_result").append("<form class='form-horizontal'> <div class='form-group' style='border :1px solid black'> <table style='width:100%''> <tr> <th>Source:</th><th>Destination: </th><th>No Of Passenger:</th> <th>Departure Time:</th><th>Arrival Time: </th></tr><tr><td>A</td><td>Z</td><td>1000</td><td>9:00am</td><td>9:30am</td></tr><tr><th>Date: </th> <th>Price:</th><th>Total Time: </th><th>Type: </th></tr><tr><td>05/12/17</td><td>$3</td><td>11 minute</td><td>SB</td><td><button type='button' id='book_btn' style='color:red'>Book</button></td></tr></table></div></form>");

		}
		else
		{
			$("#search_result").append("<form class='form-horizontal'><div class='form-group' style='border :1px solid black'><table style='width:100%'' border ='0'><tr><th>Source:</th><th>Destination: </th><th>No Of Passenger:</th><th>Departure Time:</th><th>Arrival Time: </th></tr><tr><td>A</td><td>Z</td><td>1000</td><td>9:00am</td><td>9:30am</td></tr><tr><th>Date: </th><th>Price:</th><th>Total Time: </th><th>Type: </th><th></th><th></th></tr><tr><td>05/12/17</td><td>$3</td><td>11 minute</td><td>SB</td></tr></table><div style='border-top-style: dashed'><table style='width:100%' border ='0'><tr><th>Source:</th><th>Destination: </th><th>No Of Passenger:</th><th>Departure Time:</th><th>Arrival Time: </th></tr><tr><td>A</td><td>Z</td><td>1000</td><td>9:00am</td><td>9:30am</td></tr><tr><th>Date: </th><th>Price:</th><th>Total Time: </th><th>Type: </th><th></th><th></th></tr><tr><td>05/12/17</td><td>$3</td><td>11 minute</td><td>SB</td><td><button type='button' id='book_btn' style='color:red'>Book</button></td></tr></table> </div></div></form>");

		}
	}
});