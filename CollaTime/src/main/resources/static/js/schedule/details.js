$("#detailsModal .color-button").on("click", function () {
    $("#detailsModal .update-color-dropdown").css("display", "flex");

    const colorButton = document.querySelector('#detailsModal .color-button');
    const colorDropdown = document.querySelector('#detailsModal .update-color-dropdown');
    const colorOptions = document.querySelectorAll('#detailsModal .color-option');
    const colorInput = document.querySelector('#detailsModal input[name="colorCode"]');

    // 색상 옵션 클릭 시 색상 선택
    colorOptions.forEach(option => {
        option.addEventListener('click', function () {
            const selectedColor = option.getAttribute('data-color');

            // 선택된 색상을 색상 버튼 배경색에 반영
            colorButton.style.backgroundColor = selectedColor;

            // 선택된 색상을 hidden input에 저장
            colorInput.value = selectedColor;

            // 드롭다운 숨기기
            $(".update-color-dropdown").css("display", "none");
        });
    });


    // 드롭다운 외부 클릭 시 드롭다운 숨기기
    // document.addEventListener('click', function (event) {
    //     if (!colorButton.contains(event.target) && !colorDropdown.contains(event.target)) {
    //         $(".update-color-dropdown").css("display", "none");
    //     }
    // });
})

// 상세보기 모달을 닫는 함수
function closeDetailsModal() {
    $("#detailsModal").hide();
}

$(function () {
    // 글자색 색상 선택기를 클릭할 때
    $('#detailsModal .textColorPicker').on('input', function () {
        // 선택된 색상을 hidden input에 저장
        var selectedColor = $(this).val();
        $('#detailsModal .selectedTextColor').val(selectedColor);

        // 선택된 색상을 실시간으로 반영
        // 예: 색상 미리보기 텍스트 색상 변경
        $('#detailsModal .grid-box label').css('color', selectedColor);
    });




    // 초기 상태: 읽기 전용 모드
    $('#scheduleForm input, #scheduleForm textarea').addClass('readonly');
    $('.update-schedule-confirmButton, .update-makeSchedule-CancelButton').addClass('hidden');

    // 수정 버튼 클릭 시 수정 모드로 전환
    $('#modifySchedule').on('click', function() {
        // 읽기 전용 모드 해제

        $('#detailsModal input, #detailsModal textarea').removeClass('readonly').prop('readonly', false);
        $('.color-button').removeClass('readonly');

        // 확인 및 취소 버튼 표시
        $('.update-schedule-confirmButton, .update-makeSchedule-CancelButton').removeClass('hidden');

        $('#detailsModal .scheduleTitle').removeClass('readonly').prop('readonly', false);



        const inviteIcon = document.querySelector('.invite-icon');
        const participantListNode = document.querySelector('.participant-list');
        const placeholderText = document.querySelector('.placeholder-text');

        inviteIcon.addEventListener('click', () => {
            // 팀원을 초대하는 모달을 여는 코드 (여기서는 임시로 구현)
            const newParticipant = document.createElement('span');
            newParticipant.textContent = "팀원 1"; // 실제로는 초대한 팀원의 이름으로 변경
            participantListNode.appendChild(newParticipant);

            // 기존의 "참여자(닉네임)" 텍스트를 제거
            if (placeholderText) {
                placeholderText.remove();
            }
        });


        // 수정 모드로 전환 시 포커스 설정
        $('.scheduleInputTitle').focus();
    });

    // 취소 버튼 클릭 시 다시 읽기 전용 모드로 전환
    $('.update-makeSchedule-CancelButton').on('click', function() {
        // 읽기 전용 모드로 되돌리기
        $('#detailsModal input, #detailsModal textarea').addClass('readonly').prop('readonly', true);
        $('#detailsModal .color-button').addClass('readonly');

        $('#detailsModal .scheduleTitle').addClass('readonly');


        $("#detailsModal .participant-list").html("");


        // 확인 및 취소 버튼 숨기기
        $('.update-schedule-confirmButton, .update-makeSchedule-CancelButton').addClass('hidden');

        $("#detailsModal").hide();
    });



    // 삭제 버튼 클릭 시
    $('#deleteSchedule').on('click', function() {
            $("#delete-smallModal").show();
    });



})


function updateSubmitSchedule() {
    $("#update-smallModal").show();
}

function deleteCancelModal(){
    $("#delete-smallModal").hide();
}

async  function deleteConfirmAction(){
   const scheduleNo = $("#detailsModal input[name='scheduleNo']").val();
   const response= await fetch(`/api/schedules/${scheduleNo}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    })

    if(!response.ok){
        alert('삭제 처리 오류');
    }

    $("#confirmAlarm").html("삭제 완료되었습니다.");
    // 성공 모달 표시
    $("#smallModal").show();

    $("#confirmButton").off("click").on("click", function () {
        window.location.reload();
    });
}



function updateCancelModal(){
    $("#update-smallModal").hide();
}

function updateSmallModal() {

    // #detailsModal 내부에서만 요소를 선택하도록 수정
    const detailsModal = document.getElementById("detailsModal");

    // 요소 선택
    const scheduleNoInput = detailsModal.querySelector("input[name='scheduleNo']");
    const scheduleTitleInput = detailsModal.querySelector(".scheduleInputTitle");
    const colorCodeInput = detailsModal.querySelector("input[name='colorCode']");
    const textColorCodeInput = detailsModal.querySelector(".textColorPicker");
    const scheduleStartDateInput = detailsModal.querySelector("input[name='scheduleStart']");
    const scheduleEndDateInput = detailsModal.querySelector("input[name='scheduleEnd']");
    const memoInput = detailsModal.querySelector(".memo-input");
    const creatorInfo = detailsModal.querySelector(".creator-info span");

    // 선택된 값 확인 및 기본값 설정
    const scheduleNo = scheduleNoInput && scheduleNoInput.value ? scheduleNoInput.value : '';
    if (!scheduleNo) {
        alert("일정 번호가 없습니다. 다시 확인해주세요.");
        return;
    }

    const scheduleTitle = scheduleTitleInput ? scheduleTitleInput.value : '';
    const colorCode = colorCodeInput && colorCodeInput.value ? colorCodeInput.value : '#ff0000';
    const textColorCode = textColorCodeInput && textColorCodeInput.value ? textColorCodeInput.value : '#000000';
    const scheduleStartDate = scheduleStartDateInput ? scheduleStartDateInput.value : '';
    const scheduleEndDate = scheduleEndDateInput ? scheduleEndDateInput.value : '';
    const scheduleContent = memoInput ? memoInput.value : '';
    const creatorName = creatorInfo ? creatorInfo.textContent.trim() : '';

    // 폼 데이터 구성
    const scheduleData = {
        projectNo: 1,
        scheduleNo,
        scheduleTitle,
        colorCode,
        textColorCode,
        scheduleStartDate,
        scheduleEndDate,
        scheduleContent,
        scheduleCreator: creatorName
    };

    console.log("업데이트 내용:", scheduleData);

    // 서버로 데이터 전송
    fetch(`/api/schedules/${scheduleNo}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(scheduleData)
    })
        .then(response => {
            if (response.ok) {
                $("#confirmAlarm").html("수정 완료되었습니다.");

                // 성공 모달 표시
                $("#smallModal").show();

                $("#confirmButton").off("click").on("click", function () {
                    window.location.reload();
                });
            } else {
                alert("저장에 실패했습니다. 다시 시도해주세요.");
            }
        })
       .catch(error => console.error("Error:", error));
}
