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
    $('#modifySchedule').on('click', async function() {
        // 읽기 전용 모드 해제

        $('#detailsModal input, #detailsModal textarea').removeClass('readonly').prop('readonly', false);
        $('.color-button').removeClass('readonly');

        // 확인 및 취소 버튼 표시
        $('.update-schedule-confirmButton, .update-makeSchedule-CancelButton').removeClass('hidden');

        $('#detailsModal .scheduleTitle').removeClass('readonly').prop('readonly', false);

        $('#detailsModal .participant-list').removeClass('readonly');

        $(".update-remove-btn").css("display","inline");
        $(".update-participant-item-span").hide();


        await updateSscheduleParticipantList();

        const inviteIcon = document.querySelector('#update-invite-icon');
        const participantListNode = document.querySelector('#pdate-participant-list');
        const placeholderText = document.querySelector('#update-placeholder-text');

        inviteIcon.addEventListener('click', () => {
            $("#update-participant-select").show();
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

        const inviteIcon = document.querySelector('#update-invite-icon');
        inviteIcon.addEventListener('click', () => {
            $("#update-participant-select").hide();
        });

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
    const colorCode = colorCodeInput && colorCodeInput.value ? colorCodeInput.value : '#FEC4C4';
    const textColorCode = textColorCodeInput && textColorCodeInput.value ? textColorCodeInput.value : '#000000';
    const scheduleStartDate = scheduleStartDateInput ? scheduleStartDateInput.value : '';
    const scheduleEndDate = scheduleEndDateInput ? scheduleEndDateInput.value : '';
    const scheduleContent = memoInput ? memoInput.value : '';
    const creatorName = creatorInfo ? creatorInfo.textContent.trim() : '';


    const participantList = document.querySelectorAll("#update-participant-list .participant-item");
    if (participantList.length === 0) {
        document.getElementById("alert-confirmAlarm").innerText = "팀원이 없습니다. 팀원를 추가해 주세요.";
        document.getElementById("alertModal").style.display = "flex";
        return;
    }
    // data-memberNo 값을 배열로 수집
    const participantNos = Array.from(participantList).map(item => item.getAttribute('data-memberNo'));


    const urlParam=new URLSearchParams(window.location.search);
    const projectNo=urlParam.get('projectNo');

    // 폼 데이터 구성
    const scheduleData = {
        projectNo: projectNo,
        scheduleNo,
        scheduleTitle,
        colorCode,
        textColorCode,
        scheduleStartDate,
        scheduleEndDate,
        scheduleContent,
        scheduleCreator: creatorName,
        participantNos: participantNos
    };



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




async function updateSscheduleParticipantList() {
    const urlParams = new URLSearchParams(window.location.search); // URLSearchParams 라이브러리를 사용
    const projectNo = urlParams.get('projectNo');


    const response = await fetch(`/api/schedule/participant/${projectNo}`);
    if (!response.ok) {
        return;
    }
    const participants = await response.json();

    let htmlTemplate = "<select id='updateParticipantSelect' multiple>"; // multiple 속성으로 여러 선택 가능
    participants.forEach(participant => {
        htmlTemplate += `
             <option value="${participant.memberNo}" data-nickname="${participant.memberNickname}">${participant.memberNickname}</option>   
        `;
    });
    htmlTemplate += "</select>";
    document.getElementById("update-participant-select").innerHTML = htmlTemplate;



    // select 요소에서 변화 감지
    document.getElementById('updateParticipantSelect').addEventListener('change', function () {
        const selectedOptions = Array.from(this.selectedOptions);
        const selectedItemsContainer = document.getElementById('update-participant-list');

        // 선택된 항목을 추적하기 위한 Set
        const selectedMembers = new Set();

        selectedOptions.forEach(option => {
            const memberNo = option.value;
            const memberNickname = option.getAttribute('data-nickname');

            const participantItems = selectedItemsContainer.querySelectorAll('.participant-item');

            participantItems.forEach(item => {
                const memberNo = item.getAttribute('data-memberno');
                selectedMembers.add(memberNo);
            });


            // 이미 선택된 항목인지 확인
            if (selectedMembers.has(memberNo)) {
                return; // 이미 추가된 항목은 건너뜁니다
            }

            // 선택된 항목을 Set에 추가
            selectedMembers.add(memberNo);

            // span 요소로 추가
            const span = document.createElement('span');
            span.classList.add('participant-item');
            span.setAttribute('data-memberNo', memberNo); // data-memberNo 속성 추가
            span.innerHTML = `${memberNickname} <button type="button" class="remove-btn" data-memberNo="${memberNo}">x</button>`;

            // 삭제 버튼 클릭 시 해당 항목 제거
            span.querySelector('.remove-btn').addEventListener('click', function () {
                span.remove(); // span 삭제
                selectedMembers.delete(memberNo); // Set에서 해당 항목 제거
                const optionToDeselect = document.querySelector(`#participantSelect option[value="${memberNo}"]`);
                optionToDeselect.selected = false; // select에서 해당 옵션 해제
            });

            // participant-list에 추가
            selectedItemsContainer.appendChild(span);
        });

        // select 박스 숨기기
        document.querySelector("#update-participant-select").style.display = 'none';
    });
}



