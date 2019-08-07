sleepStorage = window.sessionStorage;
var checkedFeatures = [];
var newContent = '';
var resultString1 = "Empfehlung für Störfaktor";
var resultString2 = "ist";
var result = [];
var key;

function createResultList() {
    for (var counter = 0; counter < checkedFeatures.length; counter++) {
        key = checkedFeatures[counter];
        console.log(" 3 createResultList checkedFeature[i]; i, key: " + counter + " " + key);
        var id = JSON.parse(sleepStorage.getItem(key));
        console.log(" 3 createResultList id: " + id);
        getTechnologyIds(id);
    }
    console.log(" 3 createResultList endstring");
    console.log(" 3 createResultList " + newContent);

    $('#result-list').html(newContent);

}

function createListPart(receivedTechnology) {
    console.log(" 3 createListPart ");
    console.table(receivedTechnology);
    console.log(" 3 createListPart key, german name: " + key + " " + receivedTechnology.german_name);
    newContent += `<li class="list-group-item">${resultString1} "${key}" ${resultString2} ${receivedTechnology.german_name}</li>`
    console.log(" 3 createListPart " + newContent);
}

function getTechnologies(receivedTechnologyIds){
    receivedTechnologyIds.forEach(entry => {
        $.ajax({
            url: `${endpointAddress}/technology/by/id?id=${entry}`,
            type: 'get',
            async:false,
            success: (rawData, _, __) => createListPart(rawData),
            error: defaultErrorHandling
        });
    });
}

function getTechnologyIds(id) {
    $.ajax({
        url: `${endpointAddress}/technology/by/feature?id=${id}`,
        type: 'get',
        async: false,
        success: (rawData, _, __) => getTechnologies(rawData),
        error: defaultErrorHandling
    });
}

function init() {
    console.log(" 3 init");
    var retrievedData = sleepStorage.getItem("checkedFeatures");
    checkedFeatures = JSON.parse(retrievedData);
    createResultList();
}

$(document).ready(init);