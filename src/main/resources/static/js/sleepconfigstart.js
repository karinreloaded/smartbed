const endpointAddress = 'http://localhost:8080';

sleepStorage = window.sessionStorage;
var germanFeatureNames = [];
var featureIds = [];

// this function evaluates the checkboxes and stores the result, if checked,
// as a key/value pair in sessionStorage. Additionally a list with the names of checked features
// is stored in sessionStorage to enable the treatment at second level
function evaluateFirstLevel() {
    var checkboxes = document.getElementsByClassName("levelonecheckboxes");
    var checkedFeatures = [];
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            key = germanFeatureNames[i];
            value = featureIds[i];
            sleepStorage.setItem(key, value);
            checkedFeatures.push(key);
        }
    }
    sleepStorage.setItem("mainFeatures", JSON.stringify(checkedFeatures));
    sleepStorage.setItem("counter",JSON.stringify(0));
    $("#maincontainer").load("sleepfurtherlevel.html"); //loading new content
}

// creates the checkbox-list
function updateListContent(receivedData, elementId) {

    console.trace(`Received data for element id: ${elementId}`);
    console.table(receivedData);
    var newContent = '';
    receivedData.forEach(entry => {
        newContent += `<li class="list-group-item">${entry.question}&emsp;
            <input class="levelonecheckboxes" type="checkbox" style="float: right; margin-top: 5px;"></li>`;
        germanFeatureNames.push(entry.german_name);
        featureIds.push(entry.id);
    });
    $(elementId).html(newContent);
}

function updateAllFeatures() {
    $.ajax({
        url: `${endpointAddress}/feature/by/level?level=1`,
        type: 'get',
        success: (rawData, _, __) => updateListContent(rawData, '#features-list'),
        error: defaultErrorHandling
    });
    return false; // to prevent default event flow.
}

function defaultErrorHandling(jqXHR, exception) {
    var msg = '';
    if (jqXHR.status === 0) {
        msg = 'Not connected.\n Verify Network.';
    } else if (jqXHR.status === 404) {
        msg = 'Requested page not found. [404]';
    } else if (jqXHR.status === 500) {
        msg = 'Internal Server Error [500].';
    } else if (exception === 'parsererror') {
        msg = 'Requested JSON parse failed.';
    } else if (exception === 'timeout') {
        msg = 'Time out error.';
    } else if (exception === 'abort') {
        msg = 'Ajax request aborted.';
    } else {
        msg = 'Uncaught Error.\n' + jqXHR.responseText;
    }
    console.error(msg, exception);
}

function defaultSuccessHandling(data) {
    console.trace(`Succeeded: ${data}`);

    // We may want to do this -> is not ideal as it will reload both
    // technologies and features while we might want only technology or feature updated
    //updateAllTechnologies();
    // updateAllFeatures();
}

// Init load values from db
function init() {
    sleepStorage.clear();
    updateAllFeatures();
}

// The below line will auto-trigger init function when page is fully loaded in browser.

$(document).ready(init);