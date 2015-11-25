function getBasePath() {
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPath = curWwwPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPath + projectName);
}

function ajax(url,data){
    var responseObj = $.ajax({
        url: basePath + url,
        data: data,
        type: 'POST',
        async: false
    });
   return $.parseJSON(responseObj.responseText);
}
