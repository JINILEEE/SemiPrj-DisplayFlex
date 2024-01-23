/**
 *  메인
*/
const sideMenu = document.querySelector('.sideMenu');
const currentRight = parseInt(getComputedStyle(sideMenu).right);

function toggleMenu() {
    if (currentRight < 0) {
      sideMenu.style.right = '0';
    } else {
      sideMenu.style.right = '-1000px';
    }
  }

  function toggleMenuEnd() {
    if (currentRight > 0) {
      sideMenu.style.right = '0';
    } else {
      sideMenu.style.right = '-1000px';
    }
  }


