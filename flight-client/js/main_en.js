function loadEntities(type, success_func){
    return $.ajax({
        url: 'http://localhost:8080/rest/' + type + '/',
        dataType: 'json',
        contentType: "application/json",
        success: success_func,
        error: handleAjaxError
    })
}

function handleAjaxError(){

}

jQuery.fn.cell = function(data) {
    return $(this[0]).append($('<td>').html(data));
};