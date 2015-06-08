function displayAll(){
	  $.ajax({
        url: 'http://localhost:8080/myretail/rest/item/all',
        type: 'get',
        data: {
            format: 'json'
        },
        error: function(xhr, status, text) {
           console.log("xhr :(%o), status :(%o), text :(%o)", xhr, status, text);
        },
        dataType: 'json',
        success: onAjaxSuccess
    })
}
function onAjaxSuccess( data ){
	console.log(data);
   // $("#produts-table").children(listItems).remove();
    var array  = new Array();
    array.push('<tr><th> ID </th>' +
            '<th> Name </th>' +
            '<th> Price </th>' +
            '<th> SKU </th>' +
            '<th> Category </th></tr>');
	$.each(data.list, function(index,item ){
		 console.log(item);
		 array.push( 
                         '<tr><td>' + item.id + '</td>' +
                         '<td>' + item.name + '</td>' +
                        '<td>' + item.price + '</td>' +
                        '<td>' + item.sku + '</td>' +
                        '<td>' + item.category_id + '</td></tr>');
            });
	 //alert(array.join(''));
     $("#produts-table").append(array.join(''));
}
function displayItemByID(){
    var value = document.getElementById("search-item").value;
     $.ajax({
        url: 'http://localhost:8080/myretail/rest/item/'+value,
        type: 'get',
        data: {
            format: 'json'
        },
        error: function(xhr, status, text) {
           console.log("xhr :(%o), status :(%o), text :(%o)", xhr, status, text);
        },
        dataType: 'json',
        success: onAjaxSuccess
    })

}
function displayItemByCatl(){
    var val2 = document.getElementById("search-item2").value;
     $.ajax({
        url: 'http://localhost:8080/myretail/rest/catalog/'+val2,
        type: 'get',
        data: {
            format: 'json'
        },
        error: function(xhr, status, text) {
           console.log("xhr :(%o), status :(%o), text :(%o)", xhr, status, text);
        },
        dataType: 'json',
        success: onAjaxSuccess
    })

}
