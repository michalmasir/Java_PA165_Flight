var context_path = '/pa165';
var server_url = 'http://localhost:8080' + context_path + '/rest/';

function loadEntities(type, success_func) {
    return $.ajax({
        url: server_url + type + '/',
        dataType: 'json',
        contentType: "application/json",
        success: success_func,
        error: handleAjaxError
    })
}

function removeEntity(type, id) {
    $.ajax({
        url: server_url + type + '/' + id,
        type: 'delete',
        contentType: "application/json",
        success: function () {
            $('.data-table').find('tr[data-id="' + id + '"]').remove();
        },
        error: handleAjaxError
    })
}

function submitForm(type, success_url) {
    var id = getParameterByName('id');
    var data = serializeForm();
    if (id) {
        $.ajax({
            url: server_url + type + '/' + id,
            dataType: 'text',
            contentType: "application/json",
            type: 'PUT',
            data: data,
            success: function () {
                window.location.href = success_url;
            },
            error: handleAjaxError
        });
    }
    else{
        $.ajax({
            url: server_url + type + '/',
            dataType: 'text',
            contentType: "application/json",
            type: 'POST',
            data: data,
            success: function () {
                window.location.href = success_url;
            },
            error: handleAjaxError
        });
    }
}

function handleAjaxError(data) {
    if(data.status == 400){
        $('.validation_error').show();
        return;
    }
    console.log(data);
    console.log("ajax error");
    window.location.href = "crash.html"
}

jQuery.fn.cell = function (data) {
    return $(this[0]).append($('<td>').html(data));
};

function renderTimestamp(timestamp) {
    var date = new Date(timestamp);
    return date.getDate() + "." + ("0" + (date.getMonth() + 1 )).slice(-2) + "." + date.getFullYear() + " " + date.getHours() + ":" + ("0" + date.getMinutes() ).slice(-2);
}


function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function serializeForm() {
    var data = {};
    var $form = $('form');
    $form.find('input[type="text"], input[type="hidden"]').each(function (k, element) {
        var $element = $(element);
        data[$element.attr('id')] = $element.val();
    });

    $form.find('input[type="datetime"]').each(function (k, element) {
        var $element = $(element);
        var date = new Date($element.val());
        data[$element.attr('id')] = date.getTime();
    });

    $form.find('select[multiple]').each(function (k, element) {
        var $element = $(element);
        var array = [];
        var values = $element.val();
        if (values) {
            $.each(values, function (index, value) {
                array.push({ id: value});
            });
        }
        data[$element.attr('id')] = array;
    });

    $form.find('select').not('[multiple]').each(function (k, element) {
        var $element = $(element);
        data[$element.attr('id')] = { id: $element.val()};
    });
    return JSON.stringify(data);
}
