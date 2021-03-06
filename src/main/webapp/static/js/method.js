$(function() {
    var current_prov;
    var current_city;
    var current_country;
    /* 自动加载省份列表 */
    showPro();
});
function showPro(){
    $(".btn").disabled = true;
    var len = province.length;
    for(var i = 0; i < len; i++) {
        var provOpt = document.createElement("option");
        provOpt.innerText = province[i]['name'];
        provOpt.value = i;
        prov.appendChild(provOpt);
    }
};
/*根据所选的省份来显示城市列表*/
function showCity(obj) {
    var val = obj.options[obj.selectedIndex].value;
    current_prov = val;
    if (val != null) {
        city.length = 1;
        if (province[val]) {
            var cityLen = province[val]["city"].length;
        }
        for (var j = 0; j < cityLen; j++) {
            var cityOpt = document.createElement('option');
            cityOpt.innerText = province[val]["city"][j].name;
            cityOpt.value = j;
            city.appendChild(cityOpt);
        }
    }
};
/*根据所选的城市来显示县区列表*/
function showCountry(obj) {
    var val = obj.options[obj.selectedIndex].value;
    current_city = val;
    if (val != null) {
        country.length = 1;
        if (province[current_prov]["city"][val]) {
            var countryLen = province[current_prov]["city"][val].districtAndCounty.length;
        }
        if(countryLen == 0){
            addrShow.value = province[current_prov].name + '-' + province[current_prov]["city"][val].name;
            return;
        }
        for (var n = 0; n < countryLen; n++) {
            var countryOpt = document.createElement('option');
            countryOpt.innerText = province[current_prov]["city"][val].districtAndCounty[n];
            countryOpt.value = n;
            country.appendChild(countryOpt);
        }
    }
};

function selectCountry(obj) {
    current_country = obj.options[obj.selectedIndex].value;

};
function showAddr() {
    var ss = province[current_prov].name + "|" +
        province[current_prov]['city'][current_city].name + "|" +
        province[current_prov]['city'][current_city]['districtAndCounty'][current_country];
    $("#addr-show").val(ss);
};