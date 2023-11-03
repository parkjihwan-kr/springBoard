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
        console.log(err);
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

        // createdDate를 형식화하여 표시
        var createdDate = new Date(user.createdDate);
        var formattedDate = createdDate.toLocaleString(); // 더 적절한 형식으로 변환할 수 있습니다.
        $('#modalCreateDate').text(formattedDate);
    }).fail(function(xhr, status, error) {
        // 오류 시 처리
        console.log(err);
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
    let updateUsernameValue = $('#updateUsername').val();
    let updatePasswordValue = $('#updatePassword').val();
    let updateContentsValue = $('#updateContents').val();

    console.log("updateTitle: ", updateTitleValue);
    console.log("updateUsername: ", updateUsernameValue);
    console.log("updatePassword: ",updatePasswordValue);
    console.log("updateContents: ", updateContentsValue);

    // 현재 시간을 생성
    let currentTime = new Date();
    // test
    let data = {
        updateUsername: updateUsernameValue,
        updatePassword : updatePasswordValue,
        updateTitle: updateTitleValue,
        updateContents: updateContentsValue,
        createdDate: currentTime.toISOString() // 현재 시간을 ISO 문자열로 변환
    };

    console.log(JSON.stringify(data));
    $.ajax({
        type: 'PUT',
        url: `/api/user/${updateUserId}`,
        contentType: 'application/json',
        data: JSON.stringify(data)
    }).done(res => {
        $('#updateModal').modal('hide');
        window.location.reload();
    }).fail(err => {
        alert("회원 수정에 실패하셨습니다. 비밀번호를 확인해주세요!");
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
        alert("게시글 삭제에 실패하셨습니다. 비밀번호를 확인해주세요!");
    });
}