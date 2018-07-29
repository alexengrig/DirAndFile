var modals = document.getElementsByClassName("modal");

function showModel(id) {
    for (i = 0; i < modals.length; i++) {
        hideModel(modals[i].id);
    }
    var modal = document.getElementById(id);
    modal.style.display = "block";
}

function hideModel(id) {
    var modal = document.getElementById(id);
    modal.style.display = "none";
}