function _renderResults(data) {
  var results = $("#results");
  results.html(data);
};

function loadResults($form) {
  $.ajax({
    url: 'search',
    data: $form.serializeArray()
  }).success(function(data) {
    _renderResults(data);
  });
};

function bindSubmit($form) {
  $form.on('submit', function(e) {
    e.preventDefault();
    loadResults($form);
  });
};

$(document).ready(function () {
  bindSubmit($("#search-full-text-form"));
  bindSubmit($("#search-first-name-form"));
  bindSubmit($("#search-last-name-form"));
});
