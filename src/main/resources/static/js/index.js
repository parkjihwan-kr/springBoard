/*추후 작성 예정*/
$(document).ready(function() {
    $('#updateButton').on('click', updateModal);
    $('#deleteButton').on('click', deleteModal);
});

function submitForm() {
    console.log("submitForm first time!");
    var data = {
        title: $('#title').val(),
        username: $('#username').val(),
        password: $('#password').val(),
        contents: $('#contents').val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/user',
        contentType: 'application/json',
        data: JSON.stringify(data)
    }).done(res => {
        alert("게시글 등록에 성공하셨습니다.");
        window.location.reload();
        $('#postModal').modal('hide');
    }).fail(err=> {
        console.error('Error:', err);
    });
}
function openDetailsModal(userId) {
    console.log("openDetailsModal method start!");

    console.log("userId : ", userId);
    $('#detailsModal').modal('show');
    // AJAX를 사용하여 서버에서 데이터 가져오기
    $.ajax({
        type: 'GET',
        url: `/api/user/${userId}`,
        contentType: 'application/json'
    }).done(function(user) {
        // 성공 시 처리
        $('#modalTitle').text(user.title);
        $('#modalUsername').text(user.username);
        $('#modalContents').text(user.contents);
    }).fail(function(xhr, status, error) {
        // 오류 시 처리
        console.error('Error:', error);
    });
}
function openUpdateModal(userId) {
    updateUserId = userId; // userId를 변수에 저장
    console.log("updateUserId : ",updateUserId);
    $('#updateModal').modal('show');
}
function updateModal() {
    console.log("userId: ", updateUserId);

    let updateTitleValue = $('#updateTitle').val();
    let updateContentsValue = $('#updateContents').val();
    console.log("updateTitle: ", updateTitleValue);
    console.log("updateContents: ", updateContentsValue);

    let data = {
        updateTitle: updateTitleValue,
        updateContents: updateContentsValue
    };
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'PUT',
        url: `/api/user/${updateUserId}`, // 저장한 userId 사용
        contentType: 'application/json',
        data: JSON.stringify(data)
    }).done(res => {
        //console.log(res);
        $('#updateModal').modal('hide');
        window.location.reload();
    }).fail(err => {
        console.log(err);
    });
}
function openDeleteModal(userId){
    deleteUserId = userId; // userId를 변수에 저장
    console.log("deleteUserId : ",deleteUserId);
    $('#deleteModal').modal('show');
}
function deleteModal(){
    console.log("deleteId?: ", deleteUserId);

    let deletePassword = $('#deletePassword').val();

    console.log("deletePassword: ", deletePassword);

    let data = {
        deletePassword: deletePassword
    };
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'DELETE',
        url: `/api/user/${deleteUserId}`, // 저장한 userId 사용
        contentType: 'application/json',
        data: JSON.stringify(data)
    }).done(res => {
        $('#deleteModal').modal('hide');
        console.log(res);
        window.location.reload();
    }).fail(err => {
        alert("요청에 실패했습니다!");
        console.log(err);
    });
}