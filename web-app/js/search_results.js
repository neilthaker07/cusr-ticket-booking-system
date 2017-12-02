var jsonVar = {
	"train_no":"SB0700",
	"from_station":"A",
	"to_station":"G"
};
var ouput = document.getElementById('output');
output.innerHTML = jsonVar.train_no + " " +jsonVar.from_station + " "+jsonVar.to_station ;