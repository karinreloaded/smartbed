//const endpointAddress = 'http://localhost:8080';
sleepStorage = window.sessionStorage;
var mainFeatures = []; //array with names (=key) of chosen features in first level
var checkedFeatures = [];    // array with names (=key) of chosen features in second level
var germanFeatureNames = []; // all names of features belonging to upper feature
var featureIds = [];         // id of checked feature to store key/value pair
var i;

// evaluate the checkboxes by pressing button "Weiter"
function evaluateSecondLevel() {
    console.log(" 2 evaluate second level ");
    var checkboxes = document.getElementsByClassName("leveltwocheckboxes");
    checkedFeatures = getCheckedFeatures();
    for (var j = 0; j < checkboxes.length; j++) {
        if (checkboxes[j].checked) {
            key = germanFeatureNames[j];
            value = featureIds[j];
            sleepStorage.setItem(key, value);
            checkedFeatures.push(key);
        }
    }
    setCheckedFeatures();
}

function getCheckedFeatures() {
    if (sleepStorage.getItem("checkedFeatures") != null){
        receivedData = sleepStorage.getItem("checkedFeatures");
        checkedFeatures = JSON.parse(receivedData);
    }
    return checkedFeatures;
}

function setCheckedFeatures(){
    i++;
    sleepStorage.setItem("counter", JSON.stringify(i));
    sleepStorage.setItem("checkedFeatures", JSON.stringify(checkedFeatures));
    console.log(" 2 i mainFeatures.length " + i + " " + mainFeatures.length);
    if (i < mainFeatures.length) {
        handleCheckedFeatures();
 //       $("#maincontainer").load("sleepfurtherlevel.html"); //loading new content
    } else {
        console.log(" 2 Evaluation beendet " + checkedFeatures);
        // ToDo check ob weitere level; falls nein --> ok sonst firstlevelFeatures löschen und mit neuen Features füllen
        // firstlevelFeature in sessionstorage löschen
        // for each checkedfeature -->
        // falls weitere level -->
        // checked feature in firstlevelfeature -->
        // checked feature in checkedFeatures löschen
        // sleepresult.htl in maincontainer laden
        $("#maincontainer").load("sleepresult.html"); //loading new content
    }
}

// get features, which belong to the feature from the upper level
function getFeatures(upperlevel, headingContent, key) {
    $.ajax({
        url: `${endpointAddress}/feature/by/upperlevel?upperlevel=${upperlevel}`,
        type: 'get',
        success: (rawData, _, __) =>  updateListContent(rawData, '#detailed-features-list', headingContent, key),
        error: defaultErrorHandling
    });
    return false;
}

// creates the checkbox-list
function updateListContent(receivedData, elementId, headingContent, key) {

    console.log(`Received data for element id: ${elementId}`);
    console.table(receivedData);
    var newContent = '';
    if (receivedData.length > 0) {
        $('#heading').html(headingContent); // shows the main feature chosen on first level
        receivedData.forEach(entry => {
            newContent += `<li class="list-group-item">${entry.question}<input class="leveltwocheckboxes" type="checkbox" style="float: right; margin-top: 5px;"></li>`;
            germanFeatureNames.push(entry.german_name);
            featureIds.push(entry.id);
        });
        console.log(" 2 ##########  load new Content");
        $(elementId).html(newContent);

    } else {
        console.log("2 no further features ");
        checkedFeatures = getCheckedFeatures();
        checkedFeatures.push(key);
        retrievedData = sleepStorage.getItem("mainFeatures"); // otherwise firstlevelFeatures.length = 0
        mainFeatures = JSON.parse(retrievedData);             // when loading the page again
        setCheckedFeatures();
    }
    console.log(" 2 end updateListContent");
}

// creates the heading and gets appropriate features from database
function handleCheckedFeatures() {

    var headingContent = '';
    var resultString1 = "Sie empfinden ";
    var resultString2 = " als störend";
    i = JSON.parse(sleepStorage.getItem("counter"));
    console.log(" 2 getCounter: test " + i);
    console.log( " 2 handleCheckedFeatures " + mainFeatures.length);
    var key = mainFeatures[i];
    var upperlevel = sleepStorage.getItem(key);
    console.log(" 2 key: " + key + " upperlevel: " + upperlevel);
    headingContent += `${resultString1} ${key} ${resultString2}`;
//    $('#heading').html(headingContent); // shows the main feature chosen on first level
    getFeatures(upperlevel, headingContent, key);
}

function init() {
    retrievedData = sleepStorage.getItem("mainFeatures");
    mainFeatures = JSON.parse(retrievedData);
    if (!((mainFeatures === null) || (mainFeatures.length === 0))) {
        console.log( " 2 init " + mainFeatures.length);
        console.table(mainFeatures);
        handleCheckedFeatures();
    }
}

$(document).ready(init);