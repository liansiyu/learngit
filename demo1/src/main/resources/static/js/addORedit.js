function $(id){
    return document.getElementById(id);
}

function preCheck(){
    var reg = /^[\u4e00-\u9fa5]{1,}([ ][\u4e00-\u9fa5]{1,})*[ ]{0,1}$/
    var text = $("actors");
    var value = text.value;
    var tipspan = $("tipspan");
    if(!reg.test(value)){
        tipspan.style.visibility = "visible";
        return false
    }else{
        tipspan.style.visibility = "hidden";
    }
    return true;
}

var xmlHttpRequest;

function createXMLHttpRequest(){
    if(window.XMLHttpRequest){
        xmlHttpRequest = new XMLHttpRequest();
    }else if(window.ActiveXObject){
        try {
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e){
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
}

function ckUname(name){
    createXMLHttpRequest();
    var url = "get/check?name="+name;
    xmlHttpRequest.open("GET",url,true);
    xmlHttpRequest.onreadystatechange = ckUnameCB ;
    xmlHttpRequest.send();
}

function ckUnameCB(){
    if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
        var responseText = xmlHttpRequest.responseText;
        var addBtn = $("add");
        if(responseText=="{'name':'1'}"){
            alert("节目已经存在！");
            addBtn.style.visibility = 'hidden';
        }else{
            addBtn.style.visibility = 'visible';
        }

    }

}