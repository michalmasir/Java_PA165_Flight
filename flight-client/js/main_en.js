function loadEntities(type, success_func) {
    return $.ajax({
        url: 'http://localhost:8080/rest/' + type + '/',
        dataType: 'json',
        contentType: "application/json",
        success: success_func,
        error: handleAjaxError
    })
}

function removeEntity(type, id) {
    $.ajax({
        url: 'http://localhost:8080/rest/' + type + '/' + id,
        type: 'delete',
        contentType: "application/json",
        success: function () {
            $('.data-table').find('tr[data-id="' + id + '"]').remove();
        },
        error: handleAjaxError
    })
}

function handleAjaxError() {
    //todo display user friendly warning that server is down
}

jQuery.fn.cell = function (data) {
    return $(this[0]).append($('<td>').html(data));
};