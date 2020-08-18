function sumPrice() {
    var price = 0;
    var elements = document.querySelectorAll("#elem1, #elem2")
    for (var i = 0; i<elements.length; i++){
        if (elements[i].checked){
            price += parseFloat(elements[i].className);
        }
    }
    document.getElementById("price").text = price;
    document.getElementById("sum_price").value = price;
}