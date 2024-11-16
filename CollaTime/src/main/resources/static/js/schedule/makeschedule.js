const colorButton = document.getElementById('color-button');
const colorDropdown = document.getElementById('color-dropdown');
const colorOptions = document.querySelectorAll('.color-option');
const selectedColor = document.getElementById('selectedColor');

document.getElementById("makeScheduleXButton").addEventListener("click", function () {
    document.getElementById("makeScheduleModal").style.display = "none";
});

// 버튼을 클릭하면 색상 선택 드롭다운이 표시됨
colorButton.addEventListener('click', () => {
    colorDropdown.style.display = colorDropdown.style.display === 'none' ? 'block' : 'none';
});

// 색상 옵션을 클릭하면 버튼 색상이 변경되고 드롭다운이 닫힘
colorOptions.forEach(option => {
    option.addEventListener('click', () => {
        const optionSelectedColor = option.getAttribute('data-color');
        colorButton.style.backgroundColor = optionSelectedColor;
        colorDropdown.style.display = 'none';
        selectedColor.value = optionSelectedColor; // hidden input의 값 업데이트
    });
});

var now_date = Date.now();
var timeOff = new Date().getTimezoneOffset() * 60000;
var today = new Date(now_date - timeOff).toISOString().split("T")[0];
let startDate = document.getElementById("scheduleStartDate");
startDate.setAttribute("min", today);
startDate.onchange = function () {
    let endDate = document.getElementById("scheduleEndDate");
    endDate.setAttribute("min", this.value);
}

// 드롭다운 외부 클릭 시 닫힘
document.addEventListener('click', (event) => {
    if (!colorButton.contains(event.target) && !colorDropdown.contains(event.target)) {
        colorDropdown.style.display = 'none';
    }
});

const inviteIcon = document.getElementById('invite-icon');
const participantList = document.getElementById('participant-list');
const placeholderText = document.getElementById('placeholder-text');

inviteIcon.addEventListener('click', () => {
    // 팀원을 초대하는 모달을 여는 코드 (여기서는 임시로 구현)
    // const newParticipant = document.createElement('span');
    //     // newParticipant.textContent = "팀원 1"; // 실제로는 초대한 팀원의 이름으로 변경
    //     // participantList.appendChild(newParticipant);
    //     //
    //     // // 기존의 "참여자(닉네임)" 텍스트를 제거
    //     // if (placeholderText) {
    //     //     placeholderText.remove();
    //     // }
    $("#participant-select").show();

});

function setTextColor() {
    const textColorPicker = document.getElementById("textColorPicker");
    const selectedTextColor = document.getElementById("selectedTextColor");
    selectedTextColor.value = textColorPicker.value;
}


// JavaScript로 폼 데이터 전송
function submitSchedule(event) {
    // 이벤트 객체가 전달되지 않았다면, window.event로 대체 (일부 구형 브라우저용)
    if (!event) event = window.event;
    // 기본 폼 제출 동작 방지
    event.preventDefault();



    const colorCodeInput = document.getElementById("selectedColor");
    const textColorCodeInput = document.getElementById("selectedTextColor");

    // 선택된 값 확인 및 기본값 설정
    if (!colorCodeInput.value) colorCodeInput.value = "#ff0000"; // 기본 배경색
    if (!textColorCodeInput.value) textColorCodeInput.value = "#000000"; // 기본 글자색

    const urlParam = new URLSearchParams(window.location.search);
    const projectNo = urlParam.get('projectNo');

    const participantList = document.querySelectorAll("#participant-list .participant-item");


    if (participantList.length === 0) {
        console.log(" 등록 처리 : 팀원이 없습니다. 팀원를 추가해 주세요.",participantList);
        document.getElementById("alert-confirmAlarm").innerText = "팀원이 없습니다. 팀원를 추가해 주세요.";
        document.getElementById("alertModal").style.display = "flex";
        return;
    }

    // data-memberNo 값을 배열로 수집
    const participantNos = Array.from(participantList).map(item => item.getAttribute('data-memberNo'));




    const scheduleData = {
        projectNo: projectNo,
        scheduleTitle: document.getElementById("scheduleInputTitle").value,
        colorCode: document.getElementById("selectedColor").value,
        textColorCode: document.getElementById("selectedTextColor").value,

        scheduleStartDate: document.getElementById("scheduleStartDate").value,
        scheduleEndDate: document.getElementById("scheduleEndDate").value,
        scheduleContent: document.getElementById("memo").value,
        scheduleCreator: document.getElementById("creator-info").textContent.trim().split('|')[1].trim(),
        participantNos: participantNos
    };


    fetch('/api/schedules', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(scheduleData)
    })
        .then(response => {


            if (response.ok) {
                console.log(" 성공 모달: ");

                $("#confirmAlarm").html("생성 완료되었습니다.");


                $("#smallModal").show();
                // alert("일정이 저장되었습니다!");
                // window.location.reload();  // 새로고침하여 업데이트 반영
                //document.dispatchEvent(new CustomEvent("scheduleCreated")); // 이벤트 전송

                $("#confirmButton").on("click", function () {
                    window.location.reload();
                });

            } else {
                alert("저장에 실패했습니다. 다시 시도해주세요.");
            }
        })
        .catch(error => console.error("Error:", error));
}




async function scheduleParticipantList() {
    const urlParams = new URLSearchParams(window.location.search); // URLSearchParams 라이브러리를 사용
    const projectNo = urlParams.get('projectNo');
    console.log("projectNo : ", projectNo);

    const response = await fetch(`/api/schedule/participant/${projectNo}`);
    if (!response.ok) {
        console.log("Error: " + response.statusText);
        return;
    }
    const participants = await response.json();

    let htmlTemplate = "<select id='participantSelect' multiple>"; // multiple 속성으로 여러 선택 가능
    participants.forEach(participant => {
        htmlTemplate += `
             <option value="${participant.memberNo}" data-nickname="${participant.memberNickname}">${participant.memberNickname}</option>   
        `;
    });
    htmlTemplate += "</select>";
    document.getElementById("participant-select").innerHTML = htmlTemplate;

    // 선택된 항목을 추적하기 위한 Set
    const selectedMembers = new Set();

    // select 요소에서 변화 감지
    document.getElementById('participantSelect').addEventListener('change', function () {
        const selectedOptions = Array.from(this.selectedOptions);
        const selectedItemsContainer = document.getElementById('participant-list');

        selectedOptions.forEach(option => {
            const memberNo = option.value;
            const memberNickname = option.getAttribute('data-nickname');

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
        document.querySelector("#participant-select").style.display = 'none';
    });
}

scheduleParticipantList();




$(function(){
    $("#makeSchedule-CancelButton, #makeScheduleXButton").on("click", function(){
        $("#scheduleInputTitle").val("");
        $("#scheduleStartDate").val("");
        $("#scheduleEndDate").val("");
        $("#memo").val("");
        $("#participant-list").html("");
        $("#makeScheduleModal").hide();
    });

    $("#alertModalConfirmButton , #alert-createXButton").on("click", function(){
        $("#alertModal").hide();
    });
})