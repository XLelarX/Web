var str = document.getElementById("list").textContent;

if (str !== "") {
    var arrayOfStr = str.split(", ");
    var ul = document.createElement("ul");

    fillList();

    document.getElementById("renderList").appendChild(ul);

    window.location.replace("http://localhost:8080/");
    document.location.href = 'http://localhost:8080/';
}

function fillList() {
    for (var i = 0; i < arrayOfStr.length; i++) {
        var li = document.createElement("li");
        li.textContent = arrayOfStr[i];
        ul.appendChild(li);
    }
}

