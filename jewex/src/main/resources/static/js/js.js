let count = 1;
function showheaderItem(id) {
  document.querySelectorAll('.header-main-item').forEach((item) => {
    if (count == item.dataset.id) {
      item.style.display = "block";
    } else {
      item.style.display = "none";
    }
  });
}



showheaderItem(count);
document.querySelector('.header-right-btn').addEventListener('click', () => {
  count--;
  if (count < 1) count = 4;
  showheaderItem(count);
});

showheaderItem(count);
document.querySelector('.header-left-btn').addEventListener('click', () => {
  count--;
  if (count < 1) count = 4;
  showheaderItem(count);
});


const allProducts = document.querySelectorAll('.trending-item');
const allFilterBtns = document.querySelectorAll('.filter-btn');

allFilterBtns.forEach((button, index) => {
  button.addEventListener('click', () => {
    resetBtnColor();
    button.classList.add('activeFilterBtn');
    showProducts(index + 1);
  });
});

function showProducts(id) {
  allProducts.forEach((product) => {
    if (id == product.dataset.product) {
      product.style.display = 'block';
    } else {
      product.style.display = "none";
    }
  });
}

function resetBtnColor() {
  allFilterBtns.forEach((button) => {
    button.classList.remove('.activeFilterBtn');
  })
}

showProducts(1);
allFilterBtns[0].classList.add('activeFilterBtn');