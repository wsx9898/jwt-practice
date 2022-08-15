//Scroll Function//

function scrollWin() {
  window.scrollTo(0, 900);
}
//Scroll Function//

// 星星的JS
function IamTrying(evt, starName) {
  // Declare all variables
  var i, star;

  // Get all elements with class="fa fa-star" and hide them
  star = document.getElementsByClassName("fa fa-star");

  for (i = 0; i < star.length; i++) {
    star[i].className = star[i].className.replace(" checked", "");
  }

  // Show the current tab, and add an "checked" class to the button that opened the tab

  ClickedStarId = document.getElementById(starName).id;
  var index = ClickedStarId.match(/[0-9]/);
  for (i = 0; i < index; i++) {
    $(`#star${i + 1}`).attr("class", "fa fa-star checked");
  }
}

// 星星的JS
