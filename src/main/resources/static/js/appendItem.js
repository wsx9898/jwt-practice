$(function () {
  //範例有三項，所以從4開始
  var ItemCnt = 4;

  $(".appendItem").click(function (e) {
    appendItem();
  });


  function appendItem(){
        //用這個到時候可以判斷是哪個物品要新增
        console.log(ItemCnt);
        $("#putHere").append(
            `<tr class="classItem${ItemCnt}" id="item${ItemCnt}"><td class="image"><a class="product-image" title="Sample Product"
        href="#/women-s-crepe-printed-black/"><img width="75" alt="Sample Product" src="products-images/product1.jpg"></a></td></tr>`
        );

        //表示id是有加上去成功的
        $(`#item${ItemCnt}`).append(
            '<td><h2 class="product-name"><a href="#/women-s-crepe-printed-black/">測試產品</a></h2></td>'
        );

        $(`#item${ItemCnt}`).append(
            `<td class="a-center"><a title="Edit item parameters" class="edit-bnt"
         href="#configure/id/15945/"></a></td><td class="a-right"><span class="cart-price">
         <span class="price" id="price${ItemCnt}">$70.00</span></span></td>`
        );

        $(`#item${ItemCnt}`).append(
            `<td class="a-center movewishlist qty-middle">
            <form id='myform' method='POST' action='#'>
              <input type='button'value='-' class='qtyminus btn btn-success' field='quantity${ItemCnt}' onclick='minus("quantity${ItemCnt}")' />
              <input type='text' name='quantity${ItemCnt}' value='0' class=' qty' id='quantity${ItemCnt}' />
              <input type='button' value='+' class='qtyplus btn btn-success' field='quantity${ItemCnt}' onclick='plus("quantity${ItemCnt}")' />
            </form>
        </td>`
        );

        //
        $(`#item${ItemCnt}`).append(
            `<td class="a-right movewishlist"><span class="cart-price"><span class="price"id="totalPrice${ItemCnt}">$0</span></span></td>`
        );

        $(`#item${ItemCnt}`).append(
            `<td class="a-center last"><a id="remove1" class="button remove" title="Remove item" href="#">
        <span><span>Remove item</span></span></a></td>`
        );
        ItemCnt++;
  }




  function calculateTotal() {
    //因為在增減按鈕的fieldName是VAR所以還可以傳遞過來
    theNum = `${fieldName}`.match(/\d+/)[0];
    //第一項產品總價會即時更新Start
    //把單價的錢符號拿掉
    getNum = $(`#price${theNum}`).html().replace("$", "");
    //單價轉成數字
    toInt = parseInt(getNum);
    //讀取目前購買的數量
    pro1Qty = $(`#quantity${theNum}`).prop("value");
    //把單價乘以數量
    $(`#totalPrice${theNum}`).text("$" + getNum * pro1Qty);

    //第一項產品總價會即時更新End
  }
});
