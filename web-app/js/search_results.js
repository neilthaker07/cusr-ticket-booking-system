/**
 * Created by bhaktishah on 12/3/17.
 */
var stations=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
var express_trains = ['A','F','K','P','U','Z'];
var departure_times = {
A:[615,630,645,715,730,745,815,830,845,915,930,945,1015,1030,1045,1115,1130,1145,1215,1230,1245,1315,1330,1345,1415,1430,1445,1515,1530,1545,1615,1630,1645,1715,1730,1745,1815,1830,1845,1915,1930,1945,2015,2030,2045],
B:[623,638,653,723,738,753,823,838,853,923,938,953,1023,1038,1053,1123,1138,1153,1223,1238,1253,1323,1338,1353,1423,1438,1453,1523,1538,1553,1623,1638,1653,1723,1738,1753,1823,1838,1853,1923,1938,1953,2023,2038,2053],
C:[631,646,701,731,746,801,831,846,901,931,946,1001,1031,1046,1101,1131,1146,1201,1231,1246,1301,1331,1346,1401,1431,1446,1501,1531,1546,1601,1631,1646,1701,1731,1746,1801,1831,1846,1901,1931,1946,2001,2031,2046,2101],
D:[639,654,709,739,754,809,839,854,909,939,954,1009,1039,1054,1109,1139,1154,1209,1239,1254,1309,1339,1354,1409,1439,1454,1509,1539,1554,1609,1639,1654,1709,1739,1754,1809,1839,1854,1909,1939,1954,2009,2039,2054,2109],
E:[647,702,717,747,802,817,847,902,917,947,1002,1017,1047,1102,1117,1147,1202,1217,1247,1302,1317,1347,1402,1417,1447,1502,1517,1547,1602,1617,1647,1702,1717,1747,1802,1817,1847,1902,1917,1947,2002,2017,2047,2102,2117],
F:[655,710,725,755,810,825,855,910,925,955,1010,1025,1055,1110,1125,1155,1210,1225,1255,1310,1325,1355,1410,1425,1455,1510,1525,1555,1610,1625,1655,1710,1725,1755,1810,1825,1855,1910,1925,1955,2010,2025,2055,2110,2125],
G:[703,718,733,803,818,833,903,918,933,1003,1018,1033,1103,1118,1133,1203,1218,1233,1303,1318,1333,1403,1418,1433,1503,1518,1533,1603,1618,1633,1703,1718,1733,1803,1818,1833,1903,1918,1933,2003,2018,2033,2103,2118,2133],
H:[711,726,741,811,826,841,911,926,941,1011,1026,1041,1111,1126,1141,1211,1226,1241,1311,1326,1341,1411,1426,1441,1511,1526,1541,1611,1626,1641,1711,1726,1741,1811,1826,1841,1911,1926,1941,2011,2026,2041,2111,2126,2141],
I:[719,734,749,819,834,849,919,934,949,1019,1034,1049,1119,1134,1149,1219,1234,1249,1319,1334,1349,1419,1434,1449,1519,1534,1549,1619,1634,1649,1719,1734,1749,1819,1834,1849,1919,1934,1949,2019,2034,2049,2119,2134,2149],
J:[727,742,757,827,842,857,927,942,957,1027,1042,1057,1127,1142,1157,1227,1242,1257,1327,1342,1357,1427,1442,1457,1527,1542,1557,1627,1642,1657,1727,1742,1757,1827,1842,1857,1927,1942,1957,2027,2042,2057,2127,2142,2157],
K:[735,750,805,835,850,905,935,950,1005,1035,1050,1105,1135,1150,1205,1235,1250,1305,1335,1350,1405,1435,1450,1505,1535,1550,1605,1635,1650,1705,1735,1750,1805,1835,1850,1905,1935,1950,2005,2035,2050,2105,2135,2150,2205],
L:[743,758,813,843,858,913,943,958,1013,1043,1058,1113,1143,1158,1213,1243,1258,1313,1343,1358,1413,1443,1458,1513,1543,1558,1613,1643,1658,1713,1743,1758,1813,1843,1858,1913,1943,1958,2013,2043,2058,2113,2143,2158,2213],
M:[751,806,821,851,906,921,951,1006,1021,1051,1106,1121,1151,1206,1221,1251,1306,1321,1351,1406,1421,1451,1506,1521,1551,1606,1621,1651,1706,1721,1751,1806,1821,1851,1906,1921,1951,2006,2021,2051,2106,2121,2151,2206,2221],
N:[759,814,829,859,914,929,959,1014,1029,1059,1114,1129,1159,1214,1229,1259,1314,1329,1359,1414,1429,1459,1514,1529,1559,1614,1629,1659,1714,1729,1759,1814,1829,1859,1914,1929,1959,2014,2029,2059,2114,2129,2159,2214,2229],
O:[807,822,837,907,922,937,1007,1022,1037,1107,1122,1137,1207,1222,1237,1307,1322,1337,1407,1422,1437,1507,1522,1537,1607,1622,1637,1707,1722,1737,1807,1822,1837,1907,1922,1937,2007,2022,2037,2107,2122,2137,2207,2222,2237],
P:[815,830,845,915,930,945,1015,1030,1045,1115,1130,1145,1215,1230,1245,1315,1330,1345,1415,1430,1445,1515,1530,1545,1615,1630,1645,1715,1730,1745,1815,1830,1845,1915,1930,1945,2015,2030,2045,2115,2130,2145,2215,2230,2245],
Q:[823,838,853,923,938,953,1023,1038,1053,1123,1138,1153,1223,1238,1253,1323,1338,1353,1423,1438,1453,1523,1538,1553,1623,1638,1653,1723,1738,1753,1823,1838,1853,1923,1938,1953,2023,2038,2053,2123,2138,2153,2223,2238,2253],
P:[831,846,901,931,946,1001,1031,1046,1101,1131,1146,1201,1231,1246,1301,1331,1346,1401,1431,1446,1501,1531,1546,1601,1631,1646,1701,1731,1746,1801,1831,1846,1901,1931,1946,2001,2031,2046,2101,2131,2146,2201,2231,2246,2301],
R:[839,854,909,939,954,1009,1039,1054,1109,1139,1154,1209,1239,1254,1309,1339,1354,1409,1439,1454,1509,1539,1554,1609,1639,1654,1709,1739,1754,1809,1839,1854,1909,1939,1954,2009,2039,2054,2109,2139,2154,2209,2239,2254,2309],
S:[847,902,917,947,1002,1017,1047,1102,1117,1147,1202,1217,1247,1302,1317,1347,1402,1417,1447,1502,1517,1547,1602,1617,1647,1702,1717,1747,1802,1817,1847,1902,1917,1947,2002,2017,2047,2102,2117,2147,2202,2217,2247,2302,2317],
T:[855,910,925,955,1010,1025,1055,1110,1125,1155,1210,1225,1255,1310,1325,1355,1410,1425,1455,1510,1525,1555,1610,1625,1655,1710,1725,1755,1810,1825,1855,1910,1925,1955,2010,2025,2055,2110,2125,2155,2210,2225,2255,2310,2325],
U:[903,918,933,1003,1018,1033,1103,1118,1133,1203,1218,1233,1303,1318,1333,1403,1418,1433,1503,1518,1533,1603,1618,1633,1703,1718,1733,1803,1818,1833,1903,1918,1933,2003,2018,2033,2103,2118,2133,2203,2218,2233,2303,2318,2333],
V:[911,926,941,1011,1026,1041,1111,1126,1141,1211,1226,1241,1311,1326,1341,1411,1426,1441,1511,1526,1541,1611,1626,1641,1711,1726,1741,1811,1826,1841,1911,1926,1941,2011,2026,2041,2111,2126,2141,2211,2226,2241,2311,2326,2341],
X:[919,934,949,1019,1034,1049,1119,1134,1149,1219,1234,1249,1319,1334,1349,1419,1434,1449,1519,1534,1549,1619,1634,1649,1719,1734,1749,1819,1834,1849,1919,1934,1949,2019,2034,2049,2119,2134,2149,2219,2234,2249,2319,2334,2349],
Y:[927,942,957,1027,1042,1057,1127,1142,1157,1227,1242,1257,1327,1342,1357,1427,1442,1457,1527,1542,1557,1627,1642,1657,1727,1742,1757,1827,1842,1857,1927,1942,1957,2027,2042,2057,2127,2142,2157,2227,2242,2257,2327,2342,2357],
Z:[935,950,1005,1035,1050,1105,1135,1150,1205,1235,1250,1305,1335,1350,1405,1435,1450,1505,1535,1550,1605,1635,1650,1705,1735,1750,1805,1835,1850,1905,1935,1950,2005,2035,2050,2105,2135,2150,2205,2235,2250,2305,2335,2350,2405]};
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

